package pl.sgorecki.facebook.marketing.ads;

/**
 * Exception is thrown when a new ad campaign is created with status other that ACTIVE or PAUSED.
 *
 * @author Sebastian Górecki
 */
public class InvalidCampaignStatusException extends InvalidParameterException {
	public InvalidCampaignStatusException(String providerId, String message) {
		super(providerId, message);
	}
}
