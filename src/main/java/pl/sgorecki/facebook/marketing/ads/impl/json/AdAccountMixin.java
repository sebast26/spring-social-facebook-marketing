package pl.sgorecki.facebook.marketing.ads.impl.json;

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
import org.springframework.social.facebook.api.impl.json.FacebookModule;
import pl.sgorecki.facebook.marketing.ads.AdAccount;
import pl.sgorecki.facebook.marketing.ads.AdAccountGroup;
import pl.sgorecki.facebook.marketing.ads.AdUser;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Annotated mixin to add Jackson annotations to AdAccount.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AdAccountMixin {

	@JsonProperty("id")
	String id;

	@JsonProperty("account_groups")
	List<AdAccountGroup> accountGroups;

	@JsonProperty("account_id")
	long accountId;

	@JsonProperty("account_status")
	AdAccount.AccountStatus status;

	@JsonProperty("age")
	int age;

	@JsonProperty("agency_client_declaration")
	AdAccount.AgencyClientDeclaration agencyClientDeclaration;

	@JsonProperty("amount_spent")
	String amountSpent;

	@JsonProperty("balance")
	String balance;

	@JsonProperty("business_city")
	String businessCity;

	@JsonProperty("business_country_code")
	String businessCountryCode;

	@JsonProperty("business_name")
	String businessName;

	@JsonProperty("business_state")
	String businessState;

	@JsonProperty("business_street")
	String businessStreet;

	@JsonProperty("business_street2")
	String businessStreet2;

	@JsonProperty("business_zip")
	String businessZip;

	@JsonProperty("capabilities")
	List<AdAccount.Capability> capabilities;

	@JsonProperty("created_time")
	Date createdTime;

	@JsonProperty("currency")
	String currency;

	@JsonProperty("disable_reason")
	AdAccount.DisableReason disableReason;

	@JsonProperty("end_advertiser")
	long endAdvertiser;

	@JsonProperty("funding_source")
	String fundingSource;

	@JsonProperty("funding_source_details")
	Map<String, Object> fundingSourceDetails;

	@JsonProperty("is_personal")
	int isPersonal;

	@JsonProperty("min_campaign_group_spend_cap")
	String minCampaignGroupSpendCap;

	@JsonProperty("min_daily_budget")
	int minDailyBudget;

	@JsonProperty("media_agency")
	long mediaAgency;

	@JsonProperty("name")
	String name;

	@JsonProperty("offsite_pixels_tos_accepted")
	boolean offsitePixelsTOSAccepted;

	@JsonProperty("partner")
	long partner;

	@JsonProperty("spend_cap")
	String spendCap;

	@JsonProperty("timezone_id")
	int timezoneId;

	@JsonProperty("timezone_name")
	String timezoneName;

	@JsonProperty("timezone_offset_hours_utc")
	int timezoneOffsetHoursUTC;

	@JsonProperty("tos_accepted")
	Map<String, Integer> tosAccepted;

	@JsonProperty("users")
	@JsonDeserialize(using = AdUserListDeserializer.class)
	List<AdUser> users;

	@JsonProperty("tax_id")
	String taxId;

	@JsonProperty("tax_id_status")
	AdAccount.TaxStatus taxStatus;

	@JsonProperty("tax_id_type")
	String taxIdType;

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
	public abstract class AgencyClientDeclarationMixin {

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
	}
}
