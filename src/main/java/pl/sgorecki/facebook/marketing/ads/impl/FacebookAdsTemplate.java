package pl.sgorecki.facebook.marketing.ads.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.sgorecki.facebook.marketing.ads.*;
import pl.sgorecki.facebook.marketing.ads.impl.json.FacebookAdsModule;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static org.springframework.social.facebook.api.impl.PagedListUtils.getPagedListParameters;

/**
 * This is the central class for interacting with Facebook Marketing API.
 * <p>
 * The implementation of MarketingApi interface methods (one-to-one from GraphApi) are
 * from Craig Walls from spring-social-facebook project.
 *
 * @author Sebastian Górecki
 */
public class FacebookAdsTemplate extends AbstractOAuth2ApiBinding implements FacebookAds {

	private AccountOperations accountOperations;
	private CampaignOperations campaignOperations;
	private AdSetOperations adSetOperations;
	private CreativeOperations creativeOperations;
	private AdOperations adOperations;

	private ObjectMapper objectMapper;

	public FacebookAdsTemplate() {
		super(null);
		initSubApis();
	}

	public FacebookAdsTemplate(String accessToken) {
		super(accessToken);
		initSubApis();
	}

	public AccountOperations accountOperations() {
		return accountOperations;
	}

	public CampaignOperations campaignOperations() {
		return campaignOperations;
	}

	public AdSetOperations adSetOperations() {
		return adSetOperations;
	}

	public CreativeOperations creativeOperations() {
		return creativeOperations;
	}

	public AdOperations adOperations() {
		return adOperations;
	}

	private void initSubApis() {
		accountOperations = new AccountTemplate(this, isAuthorized());
		campaignOperations = new CampaignTemplate(this, isAuthorized());
		adSetOperations = new AdSetTemplate(this, getJsonMessageConverter().getObjectMapper(), isAuthorized());
		creativeOperations = new CreativeTemplate(this, getRestTemplate(), isAuthorized());
		adOperations = new AdTemplate(this, getRestTemplate(), getJsonMessageConverter().getObjectMapper(), isAuthorized());
	}

	@Override
	public <T> T fetchObject(String objectId, Class<T> type, String... fields) {
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
		if (fields.length > 0) {
			String joinedFields = join(fields);
			queryParameters.set("fields", joinedFields);
		}
		return fetchObject(objectId, type, queryParameters);
	}

	public <T> T fetchObject(String objectId, Class<T> type, MultiValueMap<String, String> queryParameters) {
		URI uri = URIBuilder.fromUri(GRAPH_API_URL + objectId).queryParams(queryParameters).build();
		return getRestTemplate().getForObject(uri, type);
	}

	@Override
	public <T> PagedList<T> fetchConnections(String objectId, String connectionName, Class<T> type, String... fields) {
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<String, String>();
		if (fields.length > 0) {
			String joinedFields = join(fields);
			queryParameters.set("fields", joinedFields);
		}
		return fetchConnections(objectId, connectionName, type, queryParameters);
	}

	public <T> PagedList<T> fetchConnections(String objectId, String connectionType, Class<T> type, MultiValueMap<String, String> queryParameters) {
		String connectionPath = connectionType != null && connectionType.length() > 0 ? "/" + connectionType : "";
		URIBuilder uriBuilder = URIBuilder.fromUri(GRAPH_API_URL + objectId + connectionPath).queryParams(queryParameters);
		JsonNode jsonNode = getRestTemplate().getForObject(uriBuilder.build(), JsonNode.class);
		return pagify(type, jsonNode);
	}

	private <T> PagedList<T> pagify(Class<T> type, JsonNode jsonNode) {
		List<T> data = deserializeDataList(jsonNode.get("data"), type);
		if (!jsonNode.has("paging")) {
			return new PagedList<T>(data, null, null);
		}

		JsonNode pagingNode = jsonNode.get("paging");
		PagingParameters previousPage = getPagedListParameters(pagingNode, "previous");
		PagingParameters nextPage = getPagedListParameters(pagingNode, "next");

		Integer totalCount = null;
		if (jsonNode.has("summary")) {
			JsonNode summaryNode = jsonNode.get("summary");
			if (summaryNode.has("total_count")) {
				totalCount = summaryNode.get("total_count").intValue();
			}
		}

		return new PagedList<T>(data, previousPage, nextPage, totalCount);
	}

	private <T> List<T> deserializeDataList(JsonNode jsonNode, final Class<T> elementType) {
		try {
			CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, elementType);
			return (List<T>) objectMapper.reader(listType).readValue(jsonNode.toString()); // TODO: EXTREMELY HACKY--TEMPORARY UNTIL I FIGURE OUT HOW JACKSON 2 DOES THIS
		} catch (IOException e) {
			throw new UncategorizedApiException("facebook", "Error deserializing data from Facebook: " + e.getMessage(), e);
		}
	}

	@Override
	public String publish(String objectId, String connectionName, MultiValueMap<String, Object> data) {
		MultiValueMap<String, Object> requestData = new LinkedMultiValueMap<String, Object>(data);
		URI uri = URIBuilder.fromUri(GRAPH_API_URL + objectId + "/" + connectionName).build();
		Map<String, Object> response = getRestTemplate().postForObject(uri, requestData, Map.class);
		return (String) response.get("id");
	}

	@Override
	public void post(String objectId, MultiValueMap<String, Object> data) {
		post(objectId, null, data);
	}

	@Override
	public void post(String objectId, String connectionName, MultiValueMap<String, Object> data) {
		String connectionPath = connectionName != null ? "/" + connectionName : "";
		URI uri = URIBuilder.fromUri(GRAPH_API_URL + objectId + connectionPath).build();
		getRestTemplate().postForObject(uri, new LinkedMultiValueMap<>(data), String.class);
	}

	public boolean update(String objectId, MultiValueMap<String, Object> data) {
		MultiValueMap<String, Object> requestData = new LinkedMultiValueMap<String, Object>(data);
		URI uri = URIBuilder.fromUri(GRAPH_API_URL + objectId).build();
		Map<String, Object> response = getRestTemplate().postForObject(uri, requestData, Map.class);
		return (Boolean) response.get("success");
	}

	@Override
	public void delete(String objectId) {
		LinkedMultiValueMap<String, String> deleteRequest = new LinkedMultiValueMap<String, String>();
		deleteRequest.set("method", "delete");
		URI uri = URIBuilder.fromUri(GRAPH_API_URL + objectId).build();
		getRestTemplate().postForObject(uri, deleteRequest, String.class);
	}

	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.DRAFT_10;
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new FacebookAdsErrorHandler());
	}

	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new FacebookAdsModule());
		converter.setObjectMapper(objectMapper);
		return converter;
	}

	private String join(String[] strings) {
		return Arrays.asList(strings).stream().collect(joining(","));
	}
}
