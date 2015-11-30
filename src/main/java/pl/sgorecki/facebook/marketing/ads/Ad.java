package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ad {
	@JsonProperty("id")
	private String id;

	@JsonProperty("adgroup_status")
	private Ad.AdStatus status;

	@JsonProperty("name")
	private String name;

	@JsonProperty("bid_type")
	private BidType bidType;

	@JsonProperty("bid_info")
	private BidInfo bidInfo;

	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("campaign_id")
	private String adSetId;

	@JsonProperty("campaign_group_id")
	private String campaignId;

	@JsonProperty("creative")
	@JsonDeserialize(using = CreativeIdDeserializer.class)
	private String creativeId;

	@JsonProperty("targeting")
	private Targeting targeting;

	@JsonProperty("created_time")
	private Date createdTime;

	@JsonProperty("updated_time")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BidType getBidType() {
		return bidType;
	}

	public BidInfo getBidInfo() {
		return bidInfo;
	}

	public void setBidInfo(BidInfo bidInfo) {
		this.bidInfo = bidInfo;
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
			for (AdStatus status : AdStatus.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return UNKNOWN;
		}
	}

	private static class CreativeIdDeserializer extends JsonDeserializer<String> {
		@SuppressWarnings("unchecked")
		@Override
		public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
				try {
					Map<String, String> map = jp.readValueAs(HashMap.class);
					return map.get("id");
				} catch (IOException e) {
					return null;
				}
			}
			return null;
		}
	}
}
