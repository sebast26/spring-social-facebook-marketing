package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Sebastian Górecki
 */
public class Ad {
	private String id;
	private AdStatus status;
	private ConfiguredStatus configuredStatus;
	private EffectiveStatus effectiveStatus;
	private String name;

	private int bidAmount;
	private BidType bidType;
	private BidInfo bidInfo;

	private String accountId;
	private String adSetId;
	private String campaignId;

	private String creativeId;
	private Targeting targeting;

	private Date createdTime;
	private Date updatedTime;

	public String getId() {
		return id;
	}

	public AdStatus getStatus() {
		return status;
	}

	public void setStatus(AdStatus status) {
		this.status = status;
	}

	public ConfiguredStatus getConfiguredStatus() {
		return configuredStatus;
	}

	public EffectiveStatus getEffectiveStatus() {
		return effectiveStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	public BidType getBidType() {
		return bidType;
	}

	public BidInfo getBidInfo() {
		return bidInfo;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getAdSetId() {
		return adSetId;
	}

	public void setAdSetId(String adSetId) {
		this.adSetId = adSetId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public String getCreativeId() {
		return creativeId;
	}

	public void setCreativeId(String creativeId) {
		this.creativeId = creativeId;
	}

	public Targeting getTargeting() {
		return targeting;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public enum AdStatus {
		ACTIVE, PAUSED, CAMPAIGN_PAUSED, CAMPAIGN_GROUP_PAUSED, CREDIT_CARD_NEEDED, DISABLED, DISAPPROVED, PENDING_REVIEW,
		PREAPPROVED, PENDING_BILLING_INFO, ARCHIVED, DELETED, UNKNOWN;

		@JsonCreator
		public static AdStatus fromValue(String value) {
			return Arrays.stream(AdStatus.values())
					.filter(status -> status.name().equals(value))
					.findFirst()
					.orElse(UNKNOWN);
		}
	}
}
