package pl.sgorecki.facebook.marketing.ads.impl;

import org.springframework.social.MissingAuthorizationException;

import java.util.Date;

/**
 * @author Sebastian Górecki
 */
public class AbstractFacebookAdsOperations {
	private final boolean isAuthorized;

	public AbstractFacebookAdsOperations(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public String getAdAccountId(String id) {
		return "act_" + id;
	}

	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("facebook");
		}
	}

	public String getUnixTime(Date date) {
		return date != null ? String.valueOf(date.getTime() / 1000L) : "";
	}

}
