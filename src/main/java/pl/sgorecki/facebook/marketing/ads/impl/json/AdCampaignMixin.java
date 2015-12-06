package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sgorecki.facebook.marketing.ads.AdCampaign;
import pl.sgorecki.facebook.marketing.ads.ConfiguredStatus;
import pl.sgorecki.facebook.marketing.ads.EffectiveStatus;
import pl.sgorecki.facebook.marketing.ads.PromotedObject;

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

	@JsonProperty("can_use_spend_cap")
	boolean canUseSpendCap;

	@JsonProperty("status")
	AdCampaign.CampaignStatus status;

	@JsonProperty("configured_status")
	ConfiguredStatus configuredStatus;

	@JsonProperty("effective_status")
	EffectiveStatus effectiveStatus;

	@JsonProperty("name")
	String name;

	@JsonProperty("objective")
	AdCampaign.CampaignObjective objective;

	@JsonProperty("spend_cap")
	String spendCap;
}
