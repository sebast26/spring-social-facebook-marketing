package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Enum used in Ad, AdSet and Campaign classes.
 *
 * @author Sebastian Górecki
 */
public enum EffectiveStatus {
    UNKNOWN, ACTIVE, PAUSED, DELETED, PENDING_REVIEW, DISAPPROVED, PREAPPROVED, PENDING_BILLING_INFO, CAMPAIGN_PAUSED,
    ARCHIVED, ADSET_PAUSED;

    @JsonCreator
    public static EffectiveStatus fromValue(String value) {
        return Arrays.stream(EffectiveStatus.values())
                .filter(status -> status.name().equals(value))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
