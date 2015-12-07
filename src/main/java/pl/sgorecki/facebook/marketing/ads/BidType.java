package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Enum used in Ad and AdSet objects.
 *
 * @author Sebastian Górecki
 */
public enum BidType {
	CPM, CPC, MULTI_PREMIUM, ABSOLUTE_OCPM, CPA, UNKNOWN;

	@JsonCreator
	public static BidType fromValue(String value) {
		return Arrays.stream(BidType.values())
				.filter(type -> type.name().equals(value))
				.findFirst()
				.orElse(UNKNOWN);
	}
}
