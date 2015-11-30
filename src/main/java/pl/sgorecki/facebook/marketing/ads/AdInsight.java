package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Class representing response object given by Ad Insights API for request
 * about AdAccount, AdCampaign, AdSet or Ad.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdInsight {
	// id fields
	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("adgroup_id")
	private String adGroupId;

	@JsonProperty("campaign_id")
	private String campaignId;

	@JsonProperty("campaign_group_id")
	private String campaignGroupId;

	// name fields
	@JsonProperty("account_name")
	private String accountName;

	@JsonProperty("adgroup_name")
	private String adGroupName;

	@JsonProperty("campaign_group_name")
	private String campaignGroupName;

	@JsonProperty("campaign_name")
	private String camapignName;

	// date fields
	@JsonProperty("date_start")
	private Date dateStart;

	@JsonProperty("date_stop")
	private Date dateStop;

	@JsonProperty("campaign_start")
	private Date campaignStart;

	@JsonProperty("campaign_end")
	private Date campaignEnd;

	@JsonProperty("campaign_group_end")
	private Date campaignGroupEnd;

	// general fields
	@JsonProperty("actions_per_impression")
	private double actionsPerImpression;

	@JsonProperty("clicks")
	private int clicks;

	@JsonProperty("unique_clicks")
	private int uniqueClicks;

	@JsonProperty("cost_per_result")
	private double costPerResult;

	@JsonProperty("cost_per_total_action")
	private double costPerTotalAction;

	@JsonProperty("cpc")
	private double costPerClick;

	@JsonProperty("cost_per_unique_click")
	private double costPerUniqueClick;

	@JsonProperty("cpm")
	private double cpm;

	@JsonProperty("cpp")
	private double cpp;

	@JsonProperty("ctr")
	private double ctr;

	@JsonProperty("unique_ctr")
	private double uniqueCtr;

	@JsonProperty("frequency")
	private double frequency;

	@JsonProperty("impressions")
	private int impressions;

	@JsonProperty("unique_impressions")
	private int uniqueImpressions;

	@JsonProperty("objective")
	private String objective;

	@JsonProperty("reach")
	private int reach;

	@JsonProperty("result_rate")
	private double resultRate;

	@JsonProperty("results")
	private int results;

	@JsonProperty("roas")
	private int roas;

	@JsonProperty("social_clicks")
	private int socialClicks;

	@JsonProperty("unique_social_clicks")
	private int uniqueSocialClicks;

	@JsonProperty("social_impressions")
	private int socialImpressions;

	@JsonProperty("unique_social_impressions")
	private int uniqueSocialImpressions;

	@JsonProperty("social_reach")
	private int socialReach;

	@JsonProperty("spend")
	private int spend;

	@JsonProperty("today_spend")
	private int todaySpend;

	@JsonProperty("total_action_value")
	private int totalActionValue;

	@JsonProperty("total_actions")
	private int totalActions;

	@JsonProperty("total_unique_actions")
	private int totalUniqueActions;

	// action and video fields
	@JsonProperty("actions")
	private List<AdInsightAction> actions;

	@JsonProperty("unique_actions")
	private List<AdInsightAction> uniqueActions;

	@JsonProperty("cost_per_action_type")
	private List<AdInsightAction> costPerActionType;

	@JsonProperty("video_start_actions")
	private List<AdInsightAction> videoStartActions;

	public String getAccountId() {
		return accountId;
	}

	public String getAdGroupId() {
		return adGroupId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public String getCampaignGroupId() {
		return campaignGroupId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAdGroupName() {
		return adGroupName;
	}

	public String getCampaignGroupName() {
		return campaignGroupName;
	}

	public String getCamapignName() {
		return camapignName;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateStop() {
		return dateStop;
	}

	public Date getCampaignStart() {
		return campaignStart;
	}

	public Date getCampaignEnd() {
		return campaignEnd;
	}

	public Date getCampaignGroupEnd() {
		return campaignGroupEnd;
	}

	public double getActionsPerImpression() {
		return actionsPerImpression;
	}

	public int getClicks() {
		return clicks;
	}

	public int getUniqueClicks() {
		return uniqueClicks;
	}

	public double getCostPerResult() {
		return costPerResult;
	}

	public double getCostPerTotalAction() {
		return costPerTotalAction;
	}

	public double getCostPerClick() {
		return costPerClick;
	}

	public double getCostPerUniqueClick() {
		return costPerUniqueClick;
	}

	public double getCpm() {
		return cpm;
	}

	public double getCpp() {
		return cpp;
	}

	public double getCtr() {
		return ctr;
	}

	public double getUniqueCtr() {
		return uniqueCtr;
	}

	public double getFrequency() {
		return frequency;
	}

	public int getImpressions() {
		return impressions;
	}

	public int getUniqueImpressions() {
		return uniqueImpressions;
	}

	public String getObjective() {
		return objective;
	}

	public int getReach() {
		return reach;
	}

	public double getResultRate() {
		return resultRate;
	}

	public int getResults() {
		return results;
	}

	public int getRoas() {
		return roas;
	}

	public int getSocialClicks() {
		return socialClicks;
	}

	public int getUniqueSocialClicks() {
		return uniqueSocialClicks;
	}

	public int getSocialImpressions() {
		return socialImpressions;
	}

	public int getUniqueSocialImpressions() {
		return uniqueSocialImpressions;
	}

	public int getSocialReach() {
		return socialReach;
	}

	public int getSpend() {
		return spend;
	}

	public int getTodaySpend() {
		return todaySpend;
	}

	public int getTotalActionValue() {
		return totalActionValue;
	}

	public int getTotalActions() {
		return totalActions;
	}

	public int getTotalUniqueActions() {
		return totalUniqueActions;
	}

	public List<AdInsightAction> getActions() {
		return actions;
	}

	public List<AdInsightAction> getUniqueActions() {
		return uniqueActions;
	}

	public List<AdInsightAction> getCostPerActionType() {
		return costPerActionType;
	}

	public List<AdInsightAction> getVideoStartActions() {
		return videoStartActions;
	}
}
