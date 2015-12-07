package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.social.facebook.api.FacebookObject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Model class representing an ad set.
 *
 * @author Sebastian Górecki
 */
public class AdSet extends FacebookObject {
	private String id;
	private String accountId;
	private String campaignId;
	private String name;
	private AdSetStatus status;
	private ConfiguredStatus configuredStatus;
	private EffectiveStatus effectiveStatus;

	private boolean autobid;
	private BidInfo bidInfo;
	private BillingEvent billingEvent;
	private OptimizationGoal optimizationGoal;
	private int bidAmount;
	private boolean rtbFlag;

	private String budgetRemaining;
	private String dailyBudget;
	private String lifetimeBudget;

	private List<String> creativeSequence;
	private PromotedObject promotedObject;
	private Targeting targeting;

	private Date startTime;
	private Date endTime;
	private Date createdTime;
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

	public ConfiguredStatus getConfiguredStatus() {
		return configuredStatus;
	}

	public EffectiveStatus getEffectiveStatus() {
		return effectiveStatus;
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

	public OptimizationGoal getOptimizationGoal() {
		return optimizationGoal;
	}

	public void setOptimizationGoal(OptimizationGoal optimizationGoal) {
		this.optimizationGoal = optimizationGoal;
	}

	public BillingEvent getBillingEvent() {
		return billingEvent;
	}

	public void setBillingEvent(BillingEvent billingEvent) {
		this.billingEvent = billingEvent;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	public boolean isRtbFlag() {
		return rtbFlag;
	}

	public void setRtbFlag(boolean rtbFlag) {
		this.rtbFlag = rtbFlag;
	}

	public String getBudgetRemaining() {
		return budgetRemaining;
	}

	public String getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(String dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public String getLifetimeBudget() {
		return lifetimeBudget;
	}

	public void setLifetimeBudget(String lifetimeBudget) {
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
			return Arrays.stream(AdSetStatus.values())
					.filter(status -> status.name().equals(value))
					.findFirst()
					.orElse(UNKNOWN);
		}
	}

	public enum BillingEvent {
		UNKNOWN, APP_INSTALLS, CLICKS, IMPRESSIONS, LINK_CLICKS, OFFER_CLAIMS, PAGE_LIKES, POST_ENGAGEMENT, VIDEO_VIEWS;

		@JsonCreator
		public static BillingEvent fromValue(String value) {
			return Arrays.stream(BillingEvent.values())
					.filter(event -> event.name().equals(value))
					.findFirst()
					.orElse(UNKNOWN);
		}
	}

	public enum OptimizationGoal {
		UNKNOWN, NONE, APP_INSTALLS, BRAND_AWARENESS, CLICKS, ENGAGED_USERS, EXTERNAL, EVENT_RESPONSES, IMPRESSIONS,
		LEAD_GENERATION, LINK_CLICKS, OFFER_CLAIMS, OFFSITE_CONVERSIONS, PAGE_ENGAGEMENT, PAGE_LIKES, POST_ENGAGEMENT,
		REACH, SOCIAL_IMPRESSIONS, VIDEO_VIEWS;

		@JsonCreator
		public static OptimizationGoal fromValue(String value) {
			return Arrays.stream(OptimizationGoal.values())
					.filter(goal -> goal.name().equals(value))
					.findFirst()
					.orElse(UNKNOWN);
		}
	}
}
