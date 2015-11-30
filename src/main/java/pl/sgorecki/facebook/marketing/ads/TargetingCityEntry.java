package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetingCityEntry {
	@JsonProperty("key")
	private String key;

	@JsonProperty("radius")
	private int radius;

	@JsonProperty("distance_unit")
	private String distanceUnit;

	TargetingCityEntry() {
	}

	public TargetingCityEntry(String key, int radius, String distanceUnit) {
		this.key = key;
		this.radius = radius;
		this.distanceUnit = distanceUnit;
	}

	public String getKey() {
		return key;
	}

	public int getRadius() {
		return radius;
	}

	public String getDistanceUnit() {
		return distanceUnit;
	}
}
