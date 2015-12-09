package pl.sgorecki.facebook.marketing.ads.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.sgorecki.facebook.marketing.ads.AdInsight;
import pl.sgorecki.facebook.marketing.ads.AdSet;
import pl.sgorecki.facebook.marketing.ads.AdSetOperations;
import pl.sgorecki.facebook.marketing.ads.MarketingApi;

/**
 * @author Sebastian Górecki
 */
public class AdSetTemplate extends AbstractFacebookAdsOperations implements AdSetOperations {
	private MarketingApi marketingApi;
	private ObjectMapper mapper;

	public AdSetTemplate(MarketingApi marketingApi, ObjectMapper mapper, boolean authorized) {
		super(authorized);
		this.marketingApi = marketingApi;
		this.mapper = mapper;
	}

	public PagedList<AdSet> getAccountAdSets(String accountId) {
		requireAuthorization();
		return marketingApi.fetchConnections(getAdAccountId(accountId), "adsets", AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public PagedList<AdSet> getCampaignAdSets(String campaignId) {
		requireAuthorization();
		return marketingApi.fetchConnections(campaignId, "adsets", AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public AdSet getAdSet(String id) {
		requireAuthorization();
		return marketingApi.fetchObject(id, AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public AdInsight getAdSetInsight(String adSetId) {
		requireAuthorization();
		PagedList<AdInsight> insights = marketingApi.fetchConnections(adSetId, "insights", AdInsight.class, AdSetOperations.AD_SET_INSIGHT_FIELDS);
		return insights.isEmpty() ? new AdInsight() : insights.get(0);
	}

	public String createAdSet(String accountId, AdSet adSet) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(adSet);
		data.set("campaign_id", adSet.getCampaignId());
		return marketingApi.publish(getAdAccountId(accountId), "adsets", data);
	}

	public boolean updateAdSet(String adSetId, AdSet adSet) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(adSet);
		return marketingApi.update(adSetId, data);
	}

	public void deleteAdSet(String adSetId) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("status", "DELETED");
		marketingApi.post(adSetId, data);
	}

	private MultiValueMap<String, Object> mapCommonFields(AdSet adSet) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("date_format", "U");
		if (adSet.getName() != null) {
			data.set("name", adSet.getName());
		}
		if (adSet.getStatus() != null) {
			data.set("status", adSet.getStatus().name());
		}
		data.set("is_autobid", String.valueOf(adSet.isAutobid()));
		if (adSet.getBidInfo() != null) {
			try {
				data.set("bid_info", mapper.writeValueAsString(adSet.getBidInfo()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if (adSet.getBillingEvent() != null) {
			data.set("billing_event", adSet.getBillingEvent().name());
		}
		if (adSet.getOptimizationGoal() != null) {
			data.set("optimization_goal", adSet.getOptimizationGoal().name());
		}
		if (adSet.getBidAmount() != 0) {
			data.set("bid_amount", String.valueOf(adSet.getBidAmount()));
		}
		data.set("rtb_flag", String.valueOf(adSet.isRtbFlag()));
		data.set("daily_budget", String.valueOf(adSet.getDailyBudget()));
		data.set("lifetime_budget", String.valueOf(adSet.getLifetimeBudget()));
		if (adSet.getPromotedObject() != null) {
			try {
				data.set("promoted_object", mapper.writeValueAsString(adSet.getPromotedObject()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if (adSet.getTargeting() != null) {
			try {
				data.set("targeting", mapper.writeValueAsString(adSet.getTargeting()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if (adSet.getStartTime() != null) {
			data.set("start_time", getUnixTime(adSet.getStartTime()));
		}
		if (adSet.getEndTime() != null) {
			data.set("end_time", getUnixTime(adSet.getEndTime()));
		}
		return data;
	}
}