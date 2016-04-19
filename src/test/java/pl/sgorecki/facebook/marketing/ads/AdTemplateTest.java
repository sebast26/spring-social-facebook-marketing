package pl.sgorecki.facebook.marketing.ads;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.PagedList;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Sebastian Górecki
 */
public class AdTemplateTest extends AbstractFacebookAdsApiTest {

	@Test
	public void getAccountAds() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/act_123456789/ads?fields=id%2Caccount_id%2Cbid_amount%2Cbid_type%2Cbid_info%2Cadset_id%2Ccampaign_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time%2Cconfigured_status%2Ceffective_status"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-same-account"), MediaType.APPLICATION_JSON));

		PagedList<Ad> accountAds = facebookAds.adOperations().getAccountAds("123456789");
		assertEquals(3, accountAds.size());
		assertEquals("101123456789", accountAds.get(0).getId());
		assertEquals("801123456789", accountAds.get(0).getAdSetId());
		assertEquals("701123456789", accountAds.get(0).getCampaignId());
		assertEquals("123456789", accountAds.get(0).getAccountId());
		assertEquals(ConfiguredStatus.ACTIVE, accountAds.get(0).getConfiguredStatus());
		assertEquals(EffectiveStatus.ADSET_PAUSED, accountAds.get(0).getEffectiveStatus());
		assertEquals("102123456789", accountAds.get(1).getId());
		assertEquals("802123456789", accountAds.get(1).getAdSetId());
		assertEquals("702123456789", accountAds.get(1).getCampaignId());
		assertEquals("123456789", accountAds.get(1).getAccountId());
		assertEquals(ConfiguredStatus.PAUSED, accountAds.get(1).getConfiguredStatus());
		assertEquals(EffectiveStatus.PENDING_REVIEW, accountAds.get(1).getEffectiveStatus());
		assertEquals("103123456789", accountAds.get(2).getId());
		assertEquals("803123456789", accountAds.get(2).getAdSetId());
		assertEquals("703123456789", accountAds.get(2).getCampaignId());
		assertEquals("123456789", accountAds.get(2).getAccountId());
		assertEquals(ConfiguredStatus.PAUSED, accountAds.get(2).getConfiguredStatus());
		assertEquals(EffectiveStatus.PENDING_BILLING_INFO, accountAds.get(2).getEffectiveStatus());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAccountAds_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getAccountAds("123456789");
	}

	@Test
	public void getCampaignAds() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/701123456789/ads?fields=id%2Caccount_id%2Cbid_amount%2Cbid_type%2Cbid_info%2Cadset_id%2Ccampaign_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time%2Cconfigured_status%2Ceffective_status"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-same-campaign"), MediaType.APPLICATION_JSON));

		PagedList<Ad> campaignAds = facebookAds.adOperations().getCampaignAds("701123456789");
		assertEquals(3, campaignAds.size());
		assertEquals("101123456789", campaignAds.get(0).getId());
		assertEquals("801123456789", campaignAds.get(0).getAdSetId());
		assertEquals("701123456789", campaignAds.get(0).getCampaignId());
		assertEquals("102123456789", campaignAds.get(1).getId());
		assertEquals("802123456789", campaignAds.get(1).getAdSetId());
		assertEquals("701123456789", campaignAds.get(1).getCampaignId());
		assertEquals("103123456789", campaignAds.get(2).getId());
		assertEquals("803123456789", campaignAds.get(2).getAdSetId());
		assertEquals("701123456789", campaignAds.get(2).getCampaignId());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getCampaignAds_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getCampaignAds("701123456789");
	}

	@Test
	public void getAdSetAds() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/800123456789/ads?fields=id%2Caccount_id%2Cbid_amount%2Cbid_type%2Cbid_info%2Cadset_id%2Ccampaign_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time%2Cconfigured_status%2Ceffective_status"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-same-ad-set"), MediaType.APPLICATION_JSON));

		PagedList<Ad> adSetAds = facebookAds.adOperations().getAdSetAds("800123456789");
		assertEquals(2, adSetAds.size());
		assertEquals("101123456789", adSetAds.get(0).getId());
		assertEquals("800123456789", adSetAds.get(0).getAdSetId());
		assertEquals("700123456789", adSetAds.get(0).getCampaignId());

		assertEquals("102123456789", adSetAds.get(1).getId());
		assertEquals("800123456789", adSetAds.get(1).getAdSetId());
		assertEquals("701123456789", adSetAds.get(1).getCampaignId());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdSetAds_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getAdSetAds("800123456789");
	}

	@Test
	public void getAd() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789?fields=id%2Caccount_id%2Cbid_amount%2Cbid_type%2Cbid_info%2Cadset_id%2Ccampaign_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time%2Cconfigured_status%2Ceffective_status"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad"), MediaType.APPLICATION_JSON));

		Ad ad = facebookAds.adOperations().getAd("100123456789");
		verifyAd(ad);
		assertEquals(ConfiguredStatus.PAUSED, ad.getConfiguredStatus());
		assertEquals(EffectiveStatus.PAUSED, ad.getEffectiveStatus());
		assertEquals(BidType.ABSOLUTE_OCPM, ad.getBidType());
		mockServer.verify();
	}

	@Test
	public void getAd_withWrongStatus() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789?fields=id%2Caccount_id%2Cbid_amount%2Cbid_type%2Cbid_info%2Cadset_id%2Ccampaign_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time%2Cconfigured_status%2Ceffective_status"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-wrong-status"), MediaType.APPLICATION_JSON));

		Ad ad = facebookAds.adOperations().getAd("100123456789");
		verifyAd(ad);
		assertEquals(ConfiguredStatus.UNKNOWN, ad.getConfiguredStatus());
		assertEquals(EffectiveStatus.UNKNOWN, ad.getEffectiveStatus());
		mockServer.verify();
	}

	@Test
	public void getAd_withWrongBidType() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789?fields=id%2Caccount_id%2Cbid_amount%2Cbid_type%2Cbid_info%2Cadset_id%2Ccampaign_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time%2Cconfigured_status%2Ceffective_status"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-wrong-bid-type"), MediaType.APPLICATION_JSON));

		Ad ad = facebookAds.adOperations().getAd("100123456789");
		verifyAd(ad);
		assertEquals(BidType.UNKNOWN, ad.getBidType());
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAd_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getAd("100123456789");
	}

	@Test
	public void getAdInsight_emptyResults() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789/insights?fields=account_id%2Caccount_name%2Cdate_start%2Cdate_stop%2Cunique_clicks%2Ccost_per_total_action%2Ccost_per_unique_click%2Ccpc%2Ccpm%2Ccpp%2Cctr%2Cunique_ctr%2Cfrequency%2Cimpressions%2Cunique_impressions%2Creach%2Cclicks%2Csocial_clicks%2Cunique_social_clicks%2Csocial_impressions%2Cunique_social_impressions%2Csocial_reach%2Cspend%2Ctotal_action_value%2Ctotal_actions%2Ctotal_unique_actions%2Cactions%2Cunique_actions%2Ccost_per_action_type%2Cinline_link_clicks%2Ccost_per_inline_link_click%2Cinline_post_engagement%2Ccost_per_inline_post_engagement"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("empty-insights"), MediaType.APPLICATION_JSON));

		try {
			facebookAds.adOperations().getAdInsight("100123456789");
		} catch (Exception e) {
			fail("Should not throw an exception");
		}
		mockServer.verify();
	}

	@Test
	public void getAdInsight() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789/insights?fields=account_id%2Caccount_name%2Cdate_start%2Cdate_stop%2Cunique_clicks%2Ccost_per_total_action%2Ccost_per_unique_click%2Ccpc%2Ccpm%2Ccpp%2Cctr%2Cunique_ctr%2Cfrequency%2Cimpressions%2Cunique_impressions%2Creach%2Cclicks%2Csocial_clicks%2Cunique_social_clicks%2Csocial_impressions%2Cunique_social_impressions%2Csocial_reach%2Cspend%2Ctotal_action_value%2Ctotal_actions%2Ctotal_unique_actions%2Cactions%2Cunique_actions%2Ccost_per_action_type%2Cinline_link_clicks%2Ccost_per_inline_link_click%2Cinline_post_engagement%2Ccost_per_inline_post_engagement"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad-insights"), MediaType.APPLICATION_JSON));

		AdInsight insight = facebookAds.adOperations().getAdInsight("100123456789");
		assertEquals("123456789", insight.getAccountId());
		assertEquals("Test account name", insight.getAccountName());
		assertEquals(5, insight.getUniqueClicks());
		assertEquals(0.66666666666667, insight.getCostPerTotalAction(), EPSILON);
		assertEquals(0.4, insight.getCostPerUniqueClick(), EPSILON);
		assertEquals(0.777, insight.getCpc(), EPSILON);
		assertEquals(10.695187165775, insight.getCpm(), EPSILON);
		assertEquals(10.869565217391, insight.getCpp(), EPSILON);
		assertEquals(4.2780748663102, insight.getCtr(), EPSILON);
		assertEquals(2.7173913043478, insight.getUniqueCtr(), EPSILON);
		assertEquals(1.0163043478261, insight.getFrequency(), EPSILON);
		assertEquals(187, insight.getImpressions());
		assertEquals(184, insight.getUniqueImpressions());
		assertEquals(184, insight.getReach());
		assertEquals(2, insight.getClicks());
		assertEquals(0, insight.getSocialClicks());
		assertEquals(0, insight.getUniqueSocialClicks());
		assertEquals(0, insight.getSocialImpressions());
		assertEquals(0, insight.getUniqueSocialImpressions());
		assertEquals(0, insight.getSocialReach());
		assertEquals(2.01, insight.getSpend(), EPSILON);
		assertEquals(0, insight.getTotalActionValue());
		assertEquals(3, insight.getTotalActions());
		assertEquals(2, insight.getTotalUniqueActions());
		assertEquals(4, insight.getActions().size());
		assertEquals("comment", insight.getActions().get(0).getActionType());
		assertEquals(2, insight.getActions().get(0).getValue(), EPSILON);
		assertEquals("post_like", insight.getActions().get(1).getActionType());
		assertEquals(1, insight.getActions().get(1).getValue(), EPSILON);
		assertEquals("page_engagement", insight.getActions().get(2).getActionType());
		assertEquals(3, insight.getActions().get(2).getValue(), EPSILON);
		assertEquals("post_engagement", insight.getActions().get(3).getActionType());
		assertEquals(3, insight.getActions().get(3).getValue(), EPSILON);
		assertEquals(4, insight.getUniqueActions().size());
		assertEquals("comment", insight.getUniqueActions().get(0).getActionType());
		assertEquals(1, insight.getUniqueActions().get(0).getValue(), EPSILON);
		assertEquals("post_like", insight.getUniqueActions().get(1).getActionType());
		assertEquals(1, insight.getUniqueActions().get(1).getValue(), EPSILON);
		assertEquals("page_engagement", insight.getUniqueActions().get(2).getActionType());
		assertEquals(2, insight.getUniqueActions().get(2).getValue(), EPSILON);
		assertEquals("post_engagement", insight.getUniqueActions().get(3).getActionType());
		assertEquals(2, insight.getUniqueActions().get(3).getValue(), EPSILON);
		assertEquals(4, insight.getCostPerActionType().size());
		assertEquals("comment", insight.getCostPerActionType().get(0).getActionType());
		assertEquals(1, insight.getCostPerActionType().get(0).getValue(), EPSILON);
		assertEquals("post_like", insight.getCostPerActionType().get(1).getActionType());
		assertEquals(2, insight.getCostPerActionType().get(1).getValue(), EPSILON);
		assertEquals("page_engagement", insight.getCostPerActionType().get(2).getActionType());
		assertEquals(0.66666666666667, insight.getCostPerActionType().get(2).getValue(), EPSILON);
		assertEquals("post_engagement", insight.getCostPerActionType().get(3).getActionType());
		assertEquals(0.66666666666667, insight.getCostPerActionType().get(3).getValue(), EPSILON);
		assertEquals(1.3333333334, insight.getCostPerInlineLinkClick(), EPSILON);
		assertEquals(0.9999999999, insight.getCostPerInlinePostEngagement(), EPSILON);
		assertEquals(50, insight.getInlineLinkClicks());
		assertEquals(77, insight.getInlinePostEngagement());

		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAdInsight_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().getAdInsight("100123456789");
	}

	@Test
	public void createAd() throws Exception {
		String requestBody = "name=Test+ad&status=PAUSED&creative=%7B%22creative_id%22%3A+%22900123456789%22%7D&adset_id=800123456789";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/act_123456789/ads"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"100123456789\"}", MediaType.APPLICATION_JSON));

		Ad ad = new Ad();
		ad.setName("Test ad");
		ad.setStatus(Ad.AdStatus.PAUSED);
		ad.setAdSetId("800123456789");
		ad.setCreativeId("900123456789");
		assertEquals("100123456789", facebookAds.adOperations().createAd("123456789", ad));
		mockServer.verify();
	}

	@Test
	public void createAd_withBidInfo() throws Exception {
		String requestBody = "name=Test+ad&status=PAUSED&creative=%7B%22creative_id%22%3A+%22900123456789%22%7D&adset_id=800123456789";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/act_123456789/ads"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"id\": \"100123456789\"}", MediaType.APPLICATION_JSON));

		Ad ad = new Ad();
		ad.setName("Test ad");
		ad.setStatus(Ad.AdStatus.PAUSED);
		ad.setAdSetId("800123456789");
		ad.setCreativeId("900123456789");
		assertEquals("100123456789", facebookAds.adOperations().createAd("123456789", ad));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void createAd_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().createAd("123456789", new Ad());
	}

	@Test
	public void updateAd() throws Exception {
		String requestBody = "name=Updated+Ad&status=ARCHIVED";
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789"))
				.andExpect(method(POST))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andExpect(content().string(requestBody))
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));

		Ad ad = new Ad();
		ad.setStatus(Ad.AdStatus.ARCHIVED);
		ad.setName("Updated Ad");
		BidInfo bidInfo = new BidInfo();
		bidInfo.put("CLICKS", 500);
		assertTrue(facebookAds.adOperations().updateAd("100123456789", ad));
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void updateAd_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().updateAd("100123456789", new Ad());
	}

	@Test
	public void deleteAd() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789"))
				.andExpect(method(DELETE))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess("{\"success\": true}", MediaType.APPLICATION_JSON));
		facebookAds.adOperations().deleteAd("100123456789");
		mockServer.verify();
	}

	@Test(expected = NotAuthorizedException.class)
	public void deleteAd_unauthorized() throws Exception {
		unauthorizedFacebookAds.adOperations().deleteAd("100123456789");
	}

	@Test
	public void version24_addBidAmountField() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789?fields=id%2Caccount_id%2Cbid_amount%2Cbid_type%2Cbid_info%2Cadset_id%2Ccampaign_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time%2Cconfigured_status%2Ceffective_status"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad"), MediaType.APPLICATION_JSON));

		Ad ad = facebookAds.adOperations().getAd("100123456789");
		assertEquals(2400, ad.getBidAmount());
		mockServer.verify();
	}

	@Test
	public void version25_addStatusesFields() throws Exception {
		mockServer.expect(requestTo("https://graph.facebook.com/v2.5/100123456789?fields=id%2Caccount_id%2Cbid_amount%2Cbid_type%2Cbid_info%2Cadset_id%2Ccampaign_id%2Ccreated_time%2Ccreative%2Cname%2Ctargeting%2Cupdated_time%2Cconfigured_status%2Ceffective_status"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("ad"), MediaType.APPLICATION_JSON));

		Ad ad = facebookAds.adOperations().getAd("100123456789");
		assertEquals(ConfiguredStatus.PAUSED, ad.getConfiguredStatus());
		assertEquals(EffectiveStatus.PAUSED, ad.getEffectiveStatus());
		mockServer.verify();
	}

	private void verifyAd(Ad ad) {
		assertEquals("100123456789", ad.getId());
		assertEquals("123456789", ad.getAccountId());
		assertEquals("800123456789", ad.getAdSetId());
		assertEquals(2400, ad.getBidAmount());
		assertEquals("700123456789", ad.getCampaignId());
		assertEquals(toDate("2015-04-10T09:28:54+0200"), ad.getCreatedTime());
		assertEquals("900123456789", ad.getCreativeId());
		assertEquals("Test ad name", ad.getName());
		assertEquals(Integer.valueOf(18), ad.getTargeting().getAgeMin());
		assertEquals(Integer.valueOf(20), ad.getTargeting().getAgeMax());
		assertEquals(6004854404172L, ad.getTargeting().getBehaviors().get(0).getId());
		assertEquals("Technology late adopters", ad.getTargeting().getBehaviors().get(0).getName());
		assertEquals(Targeting.Gender.MALE, ad.getTargeting().getGenders().get(0));
		assertEquals("PL", ad.getTargeting().getGeoLocations().getCountries().get(0));
		assertEquals(TargetingLocation.LocationType.HOME, ad.getTargeting().getGeoLocations().getLocationTypes().get(0));
		assertEquals(TargetingLocation.LocationType.RECENT, ad.getTargeting().getGeoLocations().getLocationTypes().get(1));
		assertEquals(6003629266583L, ad.getTargeting().getInterests().get(0).getId());
		assertEquals("Hard drives", ad.getTargeting().getInterests().get(0).getName());
		assertEquals(Targeting.PageType.DESKTOPFEED, ad.getTargeting().getPageTypes().get(0));
		assertEquals(toDate("2015-04-10T13:32:09+0200"), ad.getUpdatedTime());
	}
}
