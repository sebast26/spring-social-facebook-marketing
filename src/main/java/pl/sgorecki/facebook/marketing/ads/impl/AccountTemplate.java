package pl.sgorecki.facebook.marketing.ads.impl;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.sgorecki.facebook.marketing.ads.*;

/**
 * @author Sebastian Górecki
 */
public class AccountTemplate extends AbstractFacebookAdsOperations implements AccountOperations {

	private final GraphApi graphApi;

	public AccountTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.graphApi = graphApi;
	}

	public PagedList<AdAccount> getAdAccounts(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "adaccounts", AdAccount.class, AD_ACCOUNT_FIELDS);
	}

	public AdAccount getAdAccount(String accountId) {
		requireAuthorization();
		return graphApi.fetchObject(getAdAccountId(accountId), AdAccount.class, AD_ACCOUNT_FIELDS);
	}

	public PagedList<AdCampaign> getAdAccountCampaigns(String accountId) {
		return graphApi.fetchConnections(getAdAccountId(accountId), "adcampaign_groups", AdCampaign.class, CampaignOperations.AD_CAMPAIGN_FIELDS);
	}

	public PagedList<AdUser> getAdAccountUsers(String accountId) {
		requireAuthorization();
		return graphApi.fetchConnections(getAdAccountId(accountId), "users", AdUser.class);
	}

	public void addUserToAdAccount(String accountId, String userId, AdUser.AdUserRole role) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.set("uid", userId);
		map.set("role", String.valueOf(role.getValue()));
		graphApi.post(getAdAccountId(accountId) + "/users", "", map);
	}

	public void deleteUserFromAdAccount(String accountId, String userId) {
		requireAuthorization();
		graphApi.delete(getAdAccountId(accountId) + "/users/" + userId);
	}

	public AdInsight getAdAccountInsight(String accountId) {
		requireAuthorization();
		PagedList<AdInsight> insights = graphApi.fetchConnections(getAdAccountId(accountId), "insights", AdInsight.class, AD_ACCOUNT_INSIGHT_FIELDS);
		return insights.get(0);
	}

	public boolean updateAdAccount(String accountId, AdAccount adAccount) {
		requireAuthorization();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		if (adAccount.getName() != null) {
			map.set("name", adAccount.getName());
		}
		if (adAccount.getSpendCap() != null) {
			map.set("spend_cap", adAccount.getSpendCap());
		}
		graphApi.post(getAdAccountId(accountId), map);
		return true;
	}
}
