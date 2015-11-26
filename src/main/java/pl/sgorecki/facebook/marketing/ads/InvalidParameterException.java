package pl.sgorecki.facebook.marketing.ads;

import org.springframework.social.ApiException;

/**
 * Exception thrown when one of the supplied parameters is invalid.
 *
 * @author Sebastian Górecki
 */
public class InvalidParameterException extends ApiException {

	public InvalidParameterException(String providerId, String message) {
		super(providerId, message);
	}
}
