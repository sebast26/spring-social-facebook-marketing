package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.facebook.api.FacebookObject;
import org.springframework.social.facebook.api.impl.json.FacebookModule;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Model class representing an ad account.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdAccount extends FacebookObject {
	@JsonProperty("id")
	private String id;

	@JsonProperty("account_groups")
	private List<AdAccountGroup> accountGroups;

	@JsonProperty("account_id")
	private long accountId;

	@JsonProperty("account_status")
	private AdAccount.AccountStatus status;

	@JsonProperty("age")
	private int age;

	@JsonProperty("agency_client_declaration")
	private AgencyClientDeclaration agencyClientDeclaration;

	@JsonProperty("amount_spent")
	private String amountSpent;

	@JsonProperty("balance")
	private String balance;

	@JsonProperty("business_city")
	private String businessCity;

	@JsonProperty("business_country_code")
	private String businessCountryCode;

	@JsonProperty("business_name")
	private String businessName;

	@JsonProperty("business_state")
	private String businessState;

	@JsonProperty("business_street")
	private String businessStreet;

	@JsonProperty("business_street2")
	private String businessStreet2;

	@JsonProperty("business_zip")
	private String businessZip;

	@JsonProperty("capabilities")
	private List<AdAccount.Capability> capabilities;

	@JsonProperty("created_time")
	private Date createdTime;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("daily_spend_limit")
	private String dailySpendLimit;

	@JsonProperty("end_advertiser")
	private long endAdvertiser;

	@JsonProperty("funding_source")
	private String fundingSource;

	@JsonProperty("funding_source_details")
	private Map<String, Object> fundingSourceDetails;

	@JsonProperty("is_personal")
	private int isPersonal;

	@JsonProperty("media_agency")
	private long mediaAgency;

	@JsonProperty("name")
	private String name;

	@JsonProperty("offsite_pixels_tos_accepted")
	private boolean offsitePixelsTOSAccepted;

	@JsonProperty("partner")
	private long partner;

	@JsonProperty("spend_cap")
	private String spendCap;

	@JsonProperty("timezone_id")
	private int timezoneId;

	@JsonProperty("timezone_name")
	private String timezoneName;

	@JsonProperty("timezone_offset_hours_utc")
	private int timezoneOffsetHoursUTC;

	@JsonProperty("tos_accepted")
	private Map<String, Integer> tosAccepted;

	@JsonProperty("users")
	@JsonDeserialize(using = AdUserListDeserializer.class)
	private List<AdUser> users;

	@JsonProperty("tax_id_status")
	private AdAccount.TaxStatus taxStatus;

	public String getId() {
		return id;
	}

	public List<AdAccountGroup> getAccountGroups() {
		return accountGroups;
	}

	public long getAccountId() {
		return accountId;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public int getAge() {
		return age;
	}

	public AgencyClientDeclaration getAgencyClientDeclaration() {
		return agencyClientDeclaration;
	}

	public String getAmountSpent() {
		return amountSpent;
	}

	public String getBalance() {
		return balance;
	}

	public String getBusinessCity() {
		return businessCity;
	}

	public String getBusinessCountryCode() {
		return businessCountryCode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public String getBusinessState() {
		return businessState;
	}

	public String getBusinessStreet() {
		return businessStreet;
	}

	public String getBusinessStreet2() {
		return businessStreet2;
	}

	public String getBusinessZip() {
		return businessZip;
	}

	public List<Capability> getCapabilities() {
		return capabilities;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDailySpendLimit() {
		return dailySpendLimit;
	}

	public long getEndAdvertiser() {
		return endAdvertiser;
	}

	public String getFundingSource() {
		return fundingSource;
	}

	public Map<String, Object> getFundingSourceDetails() {
		return fundingSourceDetails;
	}

	public int getIsPersonal() {
		return isPersonal;
	}

	public long getMediaAgency() {
		return mediaAgency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOffsitePixelsTOSAccepted() {
		return offsitePixelsTOSAccepted;
	}

	public long getPartner() {
		return partner;
	}

	public String getSpendCap() {
		return spendCap;
	}

	public void setSpendCap(String spendCap) {
		this.spendCap = spendCap;
	}

	public int getTimezoneId() {
		return timezoneId;
	}

	public String getTimezoneName() {
		return timezoneName;
	}

	public int getTimezoneOffsetHoursUTC() {
		return timezoneOffsetHoursUTC;
	}

	public Map<String, Integer> getTosAccepted() {
		return tosAccepted;
	}

	public List<AdUser> getUsers() {
		return users;
	}

	public TaxStatus getTaxStatus() {
		return taxStatus;
	}

	public enum AccountStatus {
		ACTIVE(1), DISABLED(2), UNSETTLED(3), PENDING_REVIEW(7), IN_GRACE_PERIOD(9), TEMPORARILY_UNAVAILABLE(101),
		PENDING_CLOSURE(100), UNKNOWN(0);

		private final int value;

		AccountStatus(int value) {
			this.value = value;
		}

		@JsonCreator
		public static AccountStatus fromValue(int value) {
			for (AccountStatus status : AccountStatus.values()) {
				if (status.getValue() == value) {
					return status;
				}
			}
			return UNKNOWN;
		}

		@JsonGetter
		public int getValue() {
			return value;
		}
	}

	public enum Capability {
		BULK_ACCOUNT, CAN_CREATE_LOOKALIKES_WITH_CUSTOM_RATIO, CAN_USE_CONVERSION_LOOKALIKES, CAN_USE_MOBILE_EXTERNAL_PAGE_TYPE_FOR_LPP,
		CAN_USE_REACH_AND_FREQUENCY, CUSTOM_CLUSTER_SHARING, DIRECT_SALES, HAS_AD_SET_TARGETING, HAS_AVAILABLE_PAYMENT_METHODS,
		HOLDOUT_VIEW_TAGS, MOBILE_ADVERTISER_ID_UPLOAD, MOBILE_APP_REENGAGEMENT_ADS, MOBILE_APP_VIDEO_ADS,
		NEKO_DESKTOP_CANVAS_APP_ADS, NEW_CAMPAIGN_STRUCTURE, PREMIUM, VIEW_TAGS, PRORATED_BUDGET, OFFSITE_CONVERSION_HIGH_BID,
		CAN_USE_MOBILE_EXTERNAL_PAGE_TYPE, CAN_USE_OLD_AD_TYPES, CAN_USE_VIDEO_METRICS_BREAKDOWN, ADS_CF_INSTORE_DAILY_BUDGET,
		AD_SET_PROMOTED_OBJECT_APP, AD_SET_PROMOTED_OBJECT_OFFER, AD_SET_PROMOTED_OBJECT_PAGE, AD_SET_PROMOTED_OBJECT_PIXEL,
		CONNECTIONS_UI_V2, LOOKALIKE_AUDIENCE, CUSTOM_AUDIENCES_OPT_OUT_LINK, CUSTOM_AUDIENCES_FOLDERS, UNKNOWN;

		@JsonCreator
		public static Capability fromValue(String value) {
			for (Capability capability : Capability.values()) {
				if (capability.name().equals(value)) {
					return capability;
				}
			}
			return UNKNOWN;
		}
	}

	public enum TaxStatus {
		VAT_NOT_REQUIRED_US_CA(1), VAT_INFORMATION_REQUIRED(2), VAT_INFORMATION_SUBMITTED(3), OFFLINE_VAT_VALIDATION_FAILED(4),
		ACCOUNT_IS_PERSONAL_ACCOUNT(5), UNKNOWN(0);

		private final int value;

		TaxStatus(int value) {
			this.value = value;
		}

		@JsonCreator
		public static TaxStatus fromValue(int value) {
			for (TaxStatus status : TaxStatus.values()) {
				if (status.getValue() == value) {
					return status;
				}
			}
			return UNKNOWN;
		}

		@JsonGetter
		public int getValue() {
			return value;
		}
	}

	private static class AdUserListDeserializer extends JsonDeserializer<List<AdUser>> {
		@SuppressWarnings("unchecked")
		@Override
		public List<AdUser> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new FacebookModule());
			jp.setCodec(mapper);
			if (jp.hasCurrentToken()) {
				try {
					JsonNode dataNode = jp.readValueAs(JsonNode.class).get("data");
					if (dataNode != null) {
						return (List<AdUser>) mapper.reader(new TypeReference<List<AdUser>>() {
						}).readValue(dataNode);
					}
				} catch (IOException e) {
					return Collections.emptyList();
				}
			}

			return Collections.emptyList();
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class AgencyClientDeclaration {
		@JsonProperty("agency_representing_client")
		private int agencyRepresentingClient;

		@JsonProperty("client_based_in_france")
		private int clientBasedInFrance;

		@JsonProperty("client_city")
		private String clientCity;

		@JsonProperty("client_country_code")
		private String clientCountryCode;

		@JsonProperty("client_email_address")
		private String clientEmailAddress;

		@JsonProperty("client_name")
		private String clientName;

		@JsonProperty("client_postal_code")
		private String clientPostalCode;

		@JsonProperty("client_province")
		private String clientProvince;

		@JsonProperty("client_street")
		private String clientStreet;

		@JsonProperty("client_street2")
		private String clientStreet2;

		@JsonProperty("has_written_mandate_from_advertiser")
		private int hasWrittenMandateFromAdvertiser;

		@JsonProperty("is_client_paying_invoices")
		private int isClientPayingInvoices;

		public int getAgencyRepresentingClient() {
			return agencyRepresentingClient;
		}

		public int getClientBasedInFrance() {
			return clientBasedInFrance;
		}

		public String getClientCity() {
			return clientCity;
		}

		public String getClientCountryCode() {
			return clientCountryCode;
		}

		public String getClientEmailAddress() {
			return clientEmailAddress;
		}

		public String getClientName() {
			return clientName;
		}

		public String getClientPostalCode() {
			return clientPostalCode;
		}

		public String getClientProvince() {
			return clientProvince;
		}

		public String getClientStreet() {
			return clientStreet;
		}

		public String getClientStreet2() {
			return clientStreet2;
		}

		public int getHasWrittenMandateFromAdvertiser() {
			return hasWrittenMandateFromAdvertiser;
		}

		public int getIsClientPayingInvoices() {
			return isClientPayingInvoices;
		}
	}
}
