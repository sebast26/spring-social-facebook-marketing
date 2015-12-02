package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sgorecki.facebook.marketing.ads.*;

import java.util.Date;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to AdSet.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AdSetMixin {
	@JsonProperty("id")
	String id;

	@JsonProperty("account_id")
	String accountId;

	@JsonProperty("campaign_group_id")
	String campaignId;

	@JsonProperty("name")
	String name;

	@JsonProperty("campaign_status")
	AdSet.AdSetStatus status;

	@JsonProperty("is_autobid")
	boolean autobid;

	@JsonProperty("bid_info")
	BidInfo bidInfo;

	@JsonProperty("billing_event")
	AdSet.BillingEvent billingEvent;

	@JsonProperty("optimization_goal")
	AdSet.OptimizationGoal optimizationGoal;

	@JsonProperty("bid_amount")
	int bidAmount;

	@JsonProperty("rtb_flag")
	boolean rtbFlag;

	@JsonProperty("budget_remaining")
	String budgetRemaining;

	@JsonProperty("daily_budget")
	String dailyBudget;

	@JsonProperty("lifetime_budget")
	String lifetimeBudget;

	@JsonProperty("creative_sequence")
	List<String> creativeSequence;

	@JsonProperty("promoted_object")
	PromotedObject promotedObject;

	@JsonProperty("targeting")
	Targeting targeting;

	@JsonProperty("start_time")
	Date startTime;

	@JsonProperty("end_time")
	Date endTime;

	@JsonProperty("created_time")
	Date createdTime;

	@JsonProperty("updated_time")
	Date updatedTime;
}
