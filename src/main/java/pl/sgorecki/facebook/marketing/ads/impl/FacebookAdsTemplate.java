package pl.sgorecki.facebook.marketing.ads.impl;

import org.springframework.social.facebook.api.impl.FacebookTemplate;
import pl.sgorecki.facebook.marketing.ads.*;

/**
 * This is the central class for interacting with Facebook Marketing API.
 *
 * @author Sebastian Górecki
 */
public class FacebookAdsTemplate extends FacebookTemplate implements FacebookAds {

	private AccountOperations accountOperations;
	private CampaignOperations campaignOperations;
	private AdSetOperations adSetOperations;
	private CreativeOperations creativeOperations;
	private AdOperations adOperations;

	public FacebookAdsTemplate() {
		super(null);
		initSubApis();
	}

	public FacebookAdsTemplate(String accessToken) {
		super(accessToken);
		initSubApis();
	}

	public AccountOperations accountOperations() {
		return accountOperations;
	}

	public CampaignOperations campaignOperations() {
		return campaignOperations;
	}

	public AdSetOperations adSetOperations() {
		return adSetOperations;
	}

	public CreativeOperations creativeOperations() {
		return creativeOperations;
	}

	public AdOperations adOperations() {
		return adOperations;
	}

	private void initSubApis() {
		accountOperations = new AccountTemplate(this, isAuthorized());
		campaignOperations = new CampaignTemplate(this, isAuthorized());
		adSetOperations = new AdSetTemplate(this, getJsonMessageConverter().getObjectMapper(), isAuthorized());
		creativeOperations = new CreativeTemplate(this, getRestTemplate(), isAuthorized());
		adOperations = new AdTemplate(this, getRestTemplate(), getJsonMessageConverter().getObjectMapper(), isAuthorized());
	}
}
