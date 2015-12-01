package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.sgorecki.facebook.marketing.ads.TargetingCityEntry;
import pl.sgorecki.facebook.marketing.ads.TargetingLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to TargetingLocation.
 *
 * @author Sebastian G�recki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = TargetingLocationSerializer.class)
public abstract class TargetingLocationMixin {
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
