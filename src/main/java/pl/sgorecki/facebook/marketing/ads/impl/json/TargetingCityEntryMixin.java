package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Annotated mixin to add Jackson annotations to TargetingCityEntry.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TargetingCityEntryMixin {
	@JsonProperty("key")
	String key;

	@JsonProperty("radius")
	int radius;

	@JsonProperty("distance_unit")
	String distanceUnit;
}
