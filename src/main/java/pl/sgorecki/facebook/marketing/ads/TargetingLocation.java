package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.sgorecki.facebook.marketing.ads.impl.json.TargetingLocationSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = TargetingLocationSerializer.class)
public class TargetingLocation {
	@JsonProperty("countries")
	List<String> countries;

	@JsonProperty("regions")
	@JsonDeserialize(using = ListOfMapsDeserializer.class)
	List<String> regions;

	@JsonProperty("cities")
	List<TargetingCityEntry> cities;

	@JsonProperty("zips")
	@JsonDeserialize(using = ListOfMapsDeserializer.class)
	List<String> zips;

	@JsonProperty("location_types")
	List<TargetingLocation.LocationType> locationTypes;

	public List<LocationType> getLocationTypes() {
		return locationTypes;
	}

	public void setLocationTypes(List<LocationType> locationTypes) {
		this.locationTypes = locationTypes;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}

	public List<TargetingCityEntry> getCities() {
		return cities;
	}

	public void setCities(List<TargetingCityEntry> cities) {
		this.cities = cities;
	}

	public List<String> getZips() {
		return zips;
	}

	public void setZips(List<String> zips) {
		this.zips = zips;
	}

	public enum LocationType {
		UNKNOWN("unknown"), RECENT("recent"), HOME("home"), TRAVEL_IN("travel_in");

		private final String value;

		LocationType(String value) {
			this.value = value;
		}

		@JsonCreator
		public static LocationType fromValue(String value) {
			for (LocationType type : LocationType.values()) {
				if (type.getValue().equals(value)) return type;
			}
			return UNKNOWN;
		}

		@JsonValue
		public String getValue() {
			return value;
		}
	}

	private static class ListOfMapsDeserializer extends JsonDeserializer<List<String>> {
		@SuppressWarnings("unchecked")
		@Override
		public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
				List<String> retList = new ArrayList<String>();
				try {
					while (jp.nextToken() != JsonToken.END_ARRAY) {
						HashMap<String, String> regionMap = jp.readValueAs(HashMap.class);
						retList.add(regionMap.get("key"));
					}
					return retList;
				} catch (IOException e) {
					return Collections.emptyList();
				}
			}
			return Collections.emptyList();
		}
	}
}
