package pl.sgorecki.facebook.marketing.ads.impl;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.sgorecki.facebook.marketing.ads.*;

/**
 * @author Sebastian Górecki
 */
public class CampaignTemplate extends AbstractFacebookAdsOperations implements CampaignOperations {

	private final MarketingApi marketingApi;

	public CampaignTemplate(MarketingApi marketingApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.marketingApi = marketingApi;
	}

	public PagedList<AdCampaign> getAdCampaigns(String accountId) {
		requireAuthorization();
		return marketingApi.fetchConnections(getAdAccountId(accountId), "adcampaign_groups", AdCampaign.class, CampaignOperations.AD_CAMPAIGN_FIELDS);
	}

	public AdCampaign getAdCampaign(String id) {
		requireAuthorization();
		return marketingApi.fetchObject(id, AdCampaign.class, CampaignOperations.AD_CAMPAIGN_FIELDS);
	}

	public PagedList<AdSet> getAdCampaignSets(String campaignId) {
		requireAuthorization();
		return marketingApi.fetchConnections(campaignId, "adcampaigns", AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public AdInsight getAdCampaignInsight(String campaignId) {
		requireAuthorization();
		PagedList<AdInsight> insights = marketingApi.fetchConnections(campaignId, "insights", AdInsight.class, CampaignOperations.AD_CAMPAIGN_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public String createAdCampaign(String accountId, AdCampaign adCampaign) {
		requireAuthorization();
		MultiValueMap<String, Object> map = mapCommonFields(adCampaign);
		if (adCampaign.getBuyingType() != null) {
			map.add("buying_type", adCampaign.getBuyingType().name());
		}
		return marketingApi.publish(getAdAccountId(accountId), "adcampaign_groups", map);
	}

	public boolean updateAdCampaign(String campaignId, AdCampaign adCampaign) {
		requireAuthorization();
		MultiValueMap<String, Object> map = mapCommonFields(adCampaign);
		return marketingApi.update(campaignId, map);
	}

	public void deleteAdCampaign(String campaignId) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("campaign_group_status", AdCampaign.CampaignStatus.DELETED.name());
		marketingApi.post(campaignId, map);
	}

	private MultiValueMap<String, Object> mapCommonFields(AdCampaign adCampaign) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		if (adCampaign.getName() != null) {
			map.add("name", adCampaign.getName());
		}
		if (adCampaign.getStatus() != null) {
			map.add("campaign_group_status", adCampaign.getStatus().name());
		}
		if (adCampaign.getObjective() != null) {
			map.add("objective", adCampaign.getObjective().name());
		}
		if (adCampaign.getSpendCap() != 0) {
			map.add("spend_cap", String.valueOf(adCampaign.getSpendCap()));
		}
		return map;
	}
}