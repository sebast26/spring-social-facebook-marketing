package pl.sgorecki.facebook.marketing.ads.impl;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.sgorecki.facebook.marketing.ads.AdCreative;
import pl.sgorecki.facebook.marketing.ads.CreativeOperations;

/**
 * @author Sebastian Górecki
 */
public class CreativeTemplate extends AbstractFacebookAdsOperations implements CreativeOperations {

	private final GraphApi graphApi;
	private final RestTemplate restTemplate;


	public CreativeTemplate(GraphApi graphApi, RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
		this.restTemplate = restTemplate;
	}

	public PagedList<AdCreative> getAccountCreatives(String accountId) {
		requireAuthorization();
		return graphApi.fetchConnections(getAdAccountId(accountId), "adcreatives", AdCreative.class, CreativeOperations.AD_CREATIVE_FIELDS);
	}

	public PagedList<AdCreative> getAdSetCreatives(String adSetId) {
		requireAuthorization();
		return graphApi.fetchConnections(adSetId, "adcreatives", AdCreative.class, CreativeOperations.AD_CREATIVE_FIELDS);
	}

	public AdCreative getAdCreative(String creativeId) {
		requireAuthorization();
		return graphApi.fetchObject(creativeId, AdCreative.class, CreativeOperations.AD_CREATIVE_FIELDS);
	}

	public String createAdCreative(String accountId, AdCreative creative) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(creative);
		return graphApi.publish(getAdAccountId(accountId), "adcreatives", data);
	}

	public boolean renameAdCreative(String creativeId, String name) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("name", name);
		graphApi.post(creativeId, data);
		return true;
	}

	public void deleteAdCreative(String creativeId) {
		requireAuthorization();
		restTemplate.delete(GraphApi.GRAPH_API_URL + creativeId);
	}

	private MultiValueMap<String, Object> mapCommonFields(AdCreative creative) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		if (creative.getType() != null) {
			data.add("object_type", creative.getType().name());
		}
		if (creative.getName() != null) {
			data.add("name", creative.getName());
		}
		if (creative.getTitle() != null) {
			data.add("title", creative.getTitle());
		}
		if (creative.getStatus() != null) {
			data.add("run_status", creative.getStatus().name());
		}
		if (creative.getBody() != null) {
			data.add("body", creative.getBody());
		}
		if (creative.getObjectId() != null) {
			data.add("object_id", creative.getObjectId());
		}
		if (creative.getImageHash() != null) {
			data.add("image_hash", creative.getImageHash());
		}
		if (creative.getImageUrl() != null) {
			data.add("image_url", creative.getImageUrl());
		}
		if (creative.getLinkUrl() != null) {
			data.add("link_url", creative.getLinkUrl());
		}
		if (creative.getObjectStoryId() != null) {
			data.add("object_story_id", creative.getObjectStoryId());
		}
		if (creative.getObjectUrl() != null) {
			data.add("object_url", creative.getObjectUrl());
		}
		if (creative.getUrlTags() != null) {
			data.add("url_tags", creative.getUrlTags());
		}
		if (creative.getThumbnailUrl() != null) {
			data.add("thumbnail_url", creative.getThumbnailUrl());
		}
		return data;
	}
}
