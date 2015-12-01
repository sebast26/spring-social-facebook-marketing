package pl.sgorecki.facebook.marketing.ads.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.sgorecki.facebook.marketing.ads.Ad;
import pl.sgorecki.facebook.marketing.ads.AdInsight;
import pl.sgorecki.facebook.marketing.ads.AdOperations;
import pl.sgorecki.facebook.marketing.ads.MarketingApi;

/**
 * @author Sebastian Górecki
 */
public class AdTemplate extends AbstractFacebookAdsOperations implements AdOperations {

	private final MarketingApi marketingApi;
	private final RestTemplate restTemplate;
	private ObjectMapper mapper;

	public AdTemplate(MarketingApi marketingApi, RestTemplate restTemplate, ObjectMapper mapper, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.marketingApi = marketingApi;
		this.restTemplate = restTemplate;
		this.mapper = mapper;
	}

	public PagedList<Ad> getAccountAds(String accountId) {
		requireAuthorization();
		return marketingApi.fetchConnections(getAdAccountId(accountId), "adgroups", Ad.class, AdOperations.AD_FIELDS);
	}

	public PagedList<Ad> getCampaignAds(String campaignId) {
		requireAuthorization();
		return marketingApi.fetchConnections(campaignId, "adgroups", Ad.class, AdOperations.AD_FIELDS);
	}

	public PagedList<Ad> getAdSetAds(String adSetId) {
		requireAuthorization();
		return marketingApi.fetchConnections(adSetId, "adgroups", Ad.class, AdOperations.AD_FIELDS);
	}

	public Ad getAd(String adId) {
		requireAuthorization();
		return marketingApi.fetchObject(adId, Ad.class, AdOperations.AD_FIELDS);
	}

	public AdInsight getAdInsight(String adId) {
		requireAuthorization();
		PagedList<AdInsight> insights = marketingApi.fetchConnections(adId, "insights", AdInsight.class, AdOperations.AD_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public String createAd(String accountId, Ad ad) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(ad);
		data.add("campaign_id", ad.getAdSetId());
		return marketingApi.publish(getAdAccountId(accountId), "adgroups", data);
	}

	public boolean updateAd(String adId, Ad ad) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(ad);
		return marketingApi.update(adId, data);
	}

	public void deleteAd(String adId) {
		requireAuthorization();
		restTemplate.delete(MarketingApi.GRAPH_API_URL + adId);
	}

	private MultiValueMap<String, Object> mapCommonFields(Ad ad) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		if (ad.getName() != null) {
			data.add("name", ad.getName());
		}
		if (ad.getStatus() != null) {
			data.add("adgroup_status", ad.getStatus().name());
		}
		if (ad.getBidInfo() != null) {
			try {
				data.add("bid_info", mapper.writeValueAsString(ad.getBidInfo()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if (ad.getCreativeId() != null) {
			data.add("creative", "{\"creative_id\": \"" + ad.getCreativeId() + "\"}");
		}
		return data;
	}
}
