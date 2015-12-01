package pl.sgorecki.facebook.marketing.ads.impl;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.sgorecki.facebook.marketing.ads.AdCreative;
import pl.sgorecki.facebook.marketing.ads.CreativeOperations;
import pl.sgorecki.facebook.marketing.ads.MarketingApi;

/**
 * @author Sebastian Górecki
 */
public class CreativeTemplate extends AbstractFacebookAdsOperations implements CreativeOperations {

	private final MarketingApi marketingApi;
	private final RestTemplate restTemplate;


	public CreativeTemplate(MarketingApi marketingApi, RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.marketingApi = marketingApi;
		this.restTemplate = restTemplate;
	}

	public PagedList<AdCreative> getAccountCreatives(String accountId) {
		requireAuthorization();
		return marketingApi.fetchConnections(getAdAccountId(accountId), "adcreatives", AdCreative.class, CreativeOperations.AD_CREATIVE_FIELDS);
	}

	public PagedList<AdCreative> getAdSetCreatives(String adSetId) {
		requireAuthorization();
		return marketingApi.fetchConnections(adSetId, "adcreatives", AdCreative.class, CreativeOperations.AD_CREATIVE_FIELDS);
	}

	public AdCreative getAdCreative(String creativeId) {
		requireAuthorization();
		return marketingApi.fetchObject(creativeId, AdCreative.class, CreativeOperations.AD_CREATIVE_FIELDS);
	}

	public String createAdCreative(String accountId, AdCreative creative) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(creative);
		return marketingApi.publish(getAdAccountId(accountId), "adcreatives", data);
	}

	public boolean renameAdCreative(String creativeId, String name) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("name", name);
		return marketingApi.update(creativeId, data);
	}

	public void deleteAdCreative(String creativeId) {
		requireAuthorization();
		restTemplate.delete(MarketingApi.GRAPH_API_URL + creativeId);
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
