package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.sgorecki.facebook.marketing.ads.*;

/**
 * @author Sebastian Górecki
 */
public class FacebookAdsModule extends SimpleModule {
	public FacebookAdsModule() {
		super("facebook-marketing");
	}

	@Override
	public void setupModule(SetupContext context) {
		super.setupModule(context);

		context.setMixInAnnotations(AdAccountGroup.class, AdAccountGroupMixin.class);
		context.setMixInAnnotations(AdAccount.AgencyClientDeclaration.class, AdAccountMixin.AgencyClientDeclarationMixin.class);
		context.setMixInAnnotations(AdUser.class, AdUserMixin.class);

		context.setMixInAnnotations(AdInsightAction.class, AdInsightActionMixin.class);
		context.setMixInAnnotations(AdInsight.class, AdInsightMixin.class);

		context.setMixInAnnotations(AdAccount.class, AdAccountMixin.class);
		context.setMixInAnnotations(AdCampaign.class, AdCampaignMixin.class);

		context.setMixInAnnotations(Targeting.class, TargetingMixin.class);
		context.setMixInAnnotations(TargetingCityEntry.class, TargetingCityEntryMixin.class);
		context.setMixInAnnotations(TargetingEntry.class, TargetingEntryMixin.class);
		context.setMixInAnnotations(TargetingLocation.class, TargetingLocationMixin.class);
		context.setMixInAnnotations(AdSet.class, AdSetMixin.class);

		context.setMixInAnnotations(AdCreative.class, AdCreativeMixin.class);
		context.setMixInAnnotations(Ad.class, AdMixin.class);
	}
}
