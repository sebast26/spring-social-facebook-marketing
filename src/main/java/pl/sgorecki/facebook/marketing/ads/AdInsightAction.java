package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing an ad insight action.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdInsightAction {
	@JsonProperty("action_type")
	private String actionType;

	@JsonProperty("value")
	private double value;

	public String getActionType() {
		return actionType;
	}

	public double getValue() {
		return value;
	}
}
