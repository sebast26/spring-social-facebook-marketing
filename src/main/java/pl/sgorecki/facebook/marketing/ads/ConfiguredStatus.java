package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Enum used in Ad, AdSet and Campaign classes.
 *
 * @author Sebastian Górecki
 */
public enum ConfiguredStatus {
    UNKNOWN, ACTIVE, PAUSED, DELETED, ARCHIVED;

    @JsonCreator
    public static ConfiguredStatus fromValue(String value) {
        return Arrays.stream(ConfiguredStatus.values())
                .filter(status -> status.name().equals(value))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
