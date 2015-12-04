package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.social.facebook.api.FacebookObject;

import java.util.Arrays;

/**
 * Model class representing an ad campaign.
 *
 * @author Sebastian Górecki
 */
public class AdCampaign extends FacebookObject {
	private String id;
	private String accountId;
	private BuyingType buyingType;
	private CampaignStatus status;
	private ConfiguredStatus configuredStatus;
	private EffectiveStatus effectiveStatus;
	private String name;
	private CampaignObjective objective;
	private String spendCap;

	public CampaignStatus getStatus() {
		return status;
	}

	public void setStatus(CampaignStatus status) {
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

	public String getSpendCap() {
		return spendCap;
	}

	public void setSpendCap(String spendCap) {
		this.spendCap = spendCap;
	}

	public enum BuyingType {
		AUCTION, RESERVED, MIXED, UNKNOWN;

		@JsonCreator
		public static BuyingType fromValue(String value) {
			return Arrays.stream(BuyingType.values())
					.filter(type -> type.name().equals(value))
					.findFirst()
					.orElse(UNKNOWN);
		}
	}

	public enum CampaignStatus {
		ACTIVE, PAUSED, ARCHIVED, DELETED, UNKNOWN;

		@JsonCreator
		public static CampaignStatus fromValue(String value) {
			return Arrays.stream(CampaignStatus.values())
					.filter(status -> status.name().equals(value))
					.findFirst()
					.orElse(UNKNOWN);
		}
	}

	public enum CampaignObjective {
		CANVAS_APP_ENGAGEMENT, CANVAS_APP_INSTALLS, EVENT_RESPONSES, LOCAL_AWARENESS, MOBILE_APP_ENGAGEMENT,
		MOBILE_APP_INSTALLS, NONE, OFFER_CLAIMS, PAGE_LIKES, POST_ENGAGEMENT, VIDEO_VIEWS, WEBSITE_CLICKS,
		WEBSITE_CONVERSIONS, UNKNOWN;

		@JsonCreator
		public static CampaignObjective fromValue(String value) {
			return Arrays.stream(CampaignObjective.values())
					.filter(objective -> objective.name().equals(value))
					.findFirst()
					.orElse(UNKNOWN);
		}
	}
}
