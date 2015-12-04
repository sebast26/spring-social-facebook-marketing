package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sgorecki.facebook.marketing.ads.AdCampaign;

/**
 * Annotated mixin to add Jackson annotations to AdCampaign.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class AdCampaignMixin {

	@JsonProperty("id")
	String id;

	@JsonProperty("account_id")
	String accountId;

	@JsonProperty("buying_type")
	AdCampaign.BuyingType buyingType;

	@JsonProperty("campaign_group_status")
	AdCampaign.CampaignStatus status;

	@JsonProperty("name")
	String name;

	@JsonProperty("objective")
	AdCampaign.CampaignObjective objective;

	@JsonProperty("spend_cap")
	String spendCap;
}
