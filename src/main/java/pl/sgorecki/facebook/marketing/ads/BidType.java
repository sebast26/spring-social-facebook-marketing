package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Enum used in Ad and AdSet objects.
 *
 * @author Sebastian G�recki
 */
public enum BidType {
	CPM, CPC, MULTI_PREMIUM, ABSOLUTE_OCPM, CPA, UNKNOWN;

	@JsonCreator
	public static BidType fromValue(String value) {
		for (BidType type : BidType.values()) {
			if (type.name().equals(value)) {
				return type;
			}
		}
		return UNKNOWN;
	}
}
