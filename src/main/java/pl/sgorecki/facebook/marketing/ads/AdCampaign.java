package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.FacebookObject;

/**
 * Model class representing an ad campaign.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdCampaign extends FacebookObject {
	@JsonProperty("id")
	private String id;

	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("buying_type")
	private AdCampaign.BuyingType buyingType;

	@JsonProperty("campaign_group_status")
	private AdCampaign.CampaignStatus status;

	@JsonProperty("name")
	private String name;

	@JsonProperty("objective")
	private AdCampaign.CampaignObjective objective;

	@JsonProperty("spend_cap")
	private int spendCap;

	public CampaignStatus getStatus() {
		return status;
	}

	public void setStatus(CampaignStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public BuyingType getBuyingType() {
		return buyingType;
	}

	public void setBuyingType(BuyingType buyingType) {
		this.buyingType = buyingType;
	}

	public CampaignObjective getObjective() {
		return objective;
	}

	public void setObjective(CampaignObjective objective) {
		this.objective = objective;
	}

	public int getSpendCap() {
		return spendCap;
	}

	public void setSpendCap(int spendCap) {
		this.spendCap = spendCap;
	}

	public enum BuyingType {
		AUCTION, FIXED_CPM, RESERVED, MIXED, UNKNOWN;

		@JsonCreator
		public static BuyingType fromValue(String value) {
			for (BuyingType type : BuyingType.values()) {
				if (type.name().equals(value)) {
					return type;
				}
			}
			return UNKNOWN;
		}
	}

	public enum CampaignStatus {
		ACTIVE, PAUSED, ARCHIVED, DELETED, UNKNOWN;

		@JsonCreator
		public static CampaignStatus fromValue(String value) {
			for (CampaignStatus status : CampaignStatus.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return UNKNOWN;
		}
	}

	public enum CampaignObjective {
		CANVAS_APP_ENGAGEMENT, CANVAS_APP_INSTALLS, EVENT_RESPONSES, LOCAL_AWARENESS, MOBILE_APP_ENGAGEMENT,
		MOBILE_APP_INSTALLS, NONE, OFFER_CLAIMS, PAGE_LIKES, POST_ENGAGEMENT, VIDEO_VIEWS, WEBSITE_CLICKS,
		WEBSITE_CONVERSIONS, UNKNOWN;

		@JsonCreator
		public static CampaignObjective fromValue(String value) {
			for (CampaignObjective objective : CampaignObjective.values()) {
				if (objective.name().equals(value)) {
					return objective;
				}
			}
			return UNKNOWN;
		}
	}
}
