package pl.sgorecki.facebook.marketing.ads;

import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.facebook.api.PagedList;

/**
 * Defines operations for working with Facebook Ad set object.
 *
 * @author Sebastian Górecki
 */
public interface AdSetOperations {
	static final String[] AD_SET_FIELDS = {
			"account_id", "bid_info", "billing_event", "bid_amount", "budget_remaining", "campaign_id", "created_time",
			"creative_sequence", "daily_budget", "end_time", "id", "is_autobid", "lifetime_budget", "name", "optimization_goal",
			"promoted_object", "rtb_flag", "start_time", "targeting", "updated_time", "configured_status", "effective_status"
	};

	static final String[] AD_SET_INSIGHT_FIELDS = {
			"account_id", "account_name", "date_start", "date_stop", "unique_clicks",
			"cost_per_total_action", "cost_per_unique_click", "cpm", "cpp", "ctr", "unique_ctr",
			"frequency", "impressions", "unique_impressions", "reach",
			"social_clicks", "unique_social_clicks", "social_impressions", "unique_social_impressions", "social_reach",
			"spend", "total_action_value", "total_actions", "total_unique_actions", "actions",
			"unique_actions", "cost_per_action_type", "inline_link_clicks", "cost_per_inline_link_click",
			"inline_post_engagement", "cost_per_inline_post_engagement"
	};

	/**
	 * Gets all ad sets from ad account given by account id.
	 *
	 * @param accountId the ID of an ad account
	 * @return the list of {@link AdSet} objects
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdSet> getAccountAdSets(String accountId);

	/**
	 * Get all ad sets for the given campaign
	 *
	 * @param campaignId the id of the ad campaing
	 * @return the list of {@link AdSet} objects.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	PagedList<AdSet> getCampaignAdSets(String campaignId);

	/**
	 * Gets ad set by given id.
	 *
	 * @param id the id of the ad set
	 * @return the {@link AdSet} object
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	AdSet getAdSet(String id);

	/**
	 * Get the insight for the ad set.
	 *
	 * @param adSetId the id of the ad set
	 * @return the {@link AdInsight} object
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	AdInsight getAdSetInsight(String adSetId);

	/**
	 * Creates an ad set in the given account. When targeting string is given in adSet object it value will be set instead
	 * of targeting object.
	 *
	 * @param accountId the account id
	 * @param adSet     the ad set object
	 * @return the id of the new ad set.
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	String createAdSet(String accountId, AdSet adSet);

	/**
	 * Updates the ad set. When targeting string is given in adSet object it value will be set instead
	 * of targeting object.
	 *
	 * @param adSetId the id of the ad set
	 * @param adSet   the ad set object
	 * @return true if update was successful
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	boolean updateAdSet(String adSetId, AdSet adSet);

	/**
	 * Deletes ad set given by id.
	 *
	 * @param adSetId the id of the ad set
	 * @throws ApiException                    if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "ads_read" or "ads_management" permission.
	 * @throws MissingAuthorizationException   if FacebookAdsTemplate was not created with an access token.
	 */
	void deleteAdSet(String adSetId);
}
