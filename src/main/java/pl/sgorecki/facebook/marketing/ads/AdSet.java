package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.FacebookObject;

import java.util.Date;
import java.util.List;

/**
 * Model class representing an ad set.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdSet extends FacebookObject {
	@JsonProperty("id")
	private String id;

	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("campaign_group_id")
	private String campaignId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("campaign_status")
	private AdSet.AdSetStatus status;

	@JsonProperty("is_autobid")
	private boolean autobid;

	@JsonProperty("bid_info")
	private BidInfo bidInfo;

	@JsonProperty("bid_type")
	private BidType bidType;

	@JsonProperty("budget_remaining")
	private int budgetRemaining;

	@JsonProperty("daily_budget")
	private int dailyBudget;

	@JsonProperty("lifetime_budget")
	private int lifetimeBudget;

	@JsonProperty("creative_sequence")
	private List<String> creativeSequence;

	@JsonProperty("promoted_object")
	private PromotedObject promotedObject;

	@JsonProperty("targeting")
	private Targeting targeting;

	@JsonProperty("start_time")
	private Date startTime;

	@JsonProperty("end_time")
	private Date endTime;

	@JsonProperty("created_time")
	private Date createdTime;

	@JsonProperty("updated_time")
	private Date updatedTime;

	public String getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdSetStatus getStatus() {
		return status;
	}

	public void setStatus(AdSetStatus status) {
		this.status = status;
	}

	public boolean isAutobid() {
		return autobid;
	}

	public void setAutobid(boolean autobid) {
		this.autobid = autobid;
	}

	public BidInfo getBidInfo() {
		return bidInfo;
	}

	public void setBidInfo(BidInfo bidInfo) {
		this.bidInfo = bidInfo;
	}

	public BidType getBidType() {
		return bidType;
	}

	public void setBidType(BidType bidType) {
		this.bidType = bidType;
	}

	public int getBudgetRemaining() {
		return budgetRemaining;
	}

	public int getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(int dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public int getLifetimeBudget() {
		return lifetimeBudget;
	}

	public void setLifetimeBudget(int lifetimeBudget) {
		this.lifetimeBudget = lifetimeBudget;
	}

	public List<String> getCreativeSequence() {
		return creativeSequence;
	}

	public PromotedObject getPromotedObject() {
		return promotedObject;
	}

	public void setPromotedObject(PromotedObject promotedObject) {
		this.promotedObject = promotedObject;
	}

	public Targeting getTargeting() {
		return targeting;
	}

	public void setTargeting(Targeting targeting) {
		this.targeting = targeting;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public enum AdSetStatus {
		ACTIVE, PAUSED, ARCHIVED, DELETED, CAMPAIGN_GROUP_PAUSED, UNKNOWN;

		@JsonCreator
		public static AdSetStatus fromValue(String value) {
			for (AdSetStatus status : AdSetStatus.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return UNKNOWN;
		}
	}
}
