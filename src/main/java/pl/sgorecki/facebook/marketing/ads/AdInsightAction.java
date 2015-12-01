package pl.sgorecki.facebook.marketing.ads;

/**
 * Model class representing an ad insight action.
 *
 * @author Sebastian Górecki
 */
public class AdInsightAction {
	private String actionType;
	private double value;

	public String getActionType() {
		return actionType;
	}

	public double getValue() {
		return value;
	}
}
