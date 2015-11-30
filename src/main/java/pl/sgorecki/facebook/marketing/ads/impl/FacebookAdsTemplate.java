package pl.sgorecki.facebook.marketing.ads.impl;

import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.sgorecki.facebook.marketing.ads.*;

import java.net.URI;
import java.util.Map;

/**
 * This is the central class for interacting with Facebook Marketing API.
 *
 * @author Sebastian Górecki
 */
public class FacebookAdsTemplate extends FacebookTemplate implements FacebookAds {

	private AccountOperations accountOperations;
	private CampaignOperations campaignOperations;
	private AdSetOperations adSetOperations;
	private CreativeOperations creativeOperations;
	private AdOperations adOperations;

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

	public boolean update(String objectId, MultiValueMap<String, Object> data) {
		MultiValueMap<String, Object> requestData = new LinkedMultiValueMap<String, Object>(data);
		URI uri = URIBuilder.fromUri(GRAPH_API_URL + objectId).build();
		Map<String, Object> response = getRestTemplate().postForObject(uri, requestData, Map.class);
		return (Boolean) response.get("success");
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new FacebookAdsErrorHandler());
	}
}
