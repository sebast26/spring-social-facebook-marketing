package pl.sgorecki.facebook.marketing.ads.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.sgorecki.facebook.marketing.ads.AdInsight;
import pl.sgorecki.facebook.marketing.ads.AdSet;
import pl.sgorecki.facebook.marketing.ads.AdSetOperations;

/**
 * @author Sebastian Górecki
 */
public class AdSetTemplate extends AbstractFacebookAdsOperations implements AdSetOperations {
	private GraphApi graphApi;
	private ObjectMapper mapper;

	public AdSetTemplate(GraphApi graphApi, ObjectMapper mapper, boolean authorized) {
		super(authorized);
		this.graphApi = graphApi;
		this.mapper = mapper;
	}

	public PagedList<AdSet> getAccountAdSets(String accountId) {
		requireAuthorization();
		return graphApi.fetchConnections(getAdAccountId(accountId), "adcampaigns", AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public PagedList<AdSet> getCampaignAdSets(String campaignId) {
		requireAuthorization();
		return graphApi.fetchConnections(campaignId, "adcampaigns", AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public AdSet getAdSet(String id) {
		requireAuthorization();
		return graphApi.fetchObject(id, AdSet.class, AdSetOperations.AD_SET_FIELDS);
	}

	public AdInsight getAdSetInsight(String adSetId) {
		requireAuthorization();
		PagedList<AdInsight> insights = graphApi.fetchConnections(adSetId, "insights", AdInsight.class, AdSetOperations.AD_SET_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public String createAdSet(String accountId, AdSet adSet) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(adSet);
		data.set("campaign_group_id", adSet.getCampaignId());
		return graphApi.publish(getAdAccountId(accountId), "adcampaigns", data);
	}

	public boolean updateAdSet(String adSetId, AdSet adSet) {
		requireAuthorization();
		MultiValueMap<String, Object> data = mapCommonFields(adSet);
//		return graphApi.update(adSetId, data);
		// TODO
		return false;
	}

	public void deleteAdSet(String adSetId) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("campaign_status", "DELETED");
		graphApi.post(adSetId, data);
	}

	private MultiValueMap<String, Object> mapCommonFields(AdSet adSet) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("date_format", "U");
		if (adSet.getName() != null) {
			data.set("name", adSet.getName());
		}
		if (adSet.getStatus() != null) {
			data.set("campaign_status", adSet.getStatus().name());
		}
		data.set("is_autobid", String.valueOf(adSet.isAutobid()));
		if (adSet.getBidInfo() != null) {
			try {
				data.set("bid_info", mapper.writeValueAsString(adSet.getBidInfo()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		if (adSet.getBidType() != null) {
			data.set("bid_type", adSet.getBidType().name());
		}
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