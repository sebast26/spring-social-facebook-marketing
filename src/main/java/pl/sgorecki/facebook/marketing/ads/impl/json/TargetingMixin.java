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
import pl.sgorecki.facebook.marketing.ads.Targeting;
import pl.sgorecki.facebook.marketing.ads.TargetingEntry;
import pl.sgorecki.facebook.marketing.ads.TargetingLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to Targeting.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = TargetingSerializer.class)
public abstract class TargetingMixin {
	// demographics
	@JsonProperty("genders")
	List<Targeting.Gender> genders;

	@JsonProperty("age_min")
	Integer ageMin;

	@JsonProperty("age_max")
	Integer ageMax;

	@JsonProperty("relationship_statuses")
	List<Targeting.RelationshipStatus> relationshipStatuses;

	@JsonProperty("interested_in")
	List<Targeting.InterestedIn> interestedIn;

	// location
	@JsonProperty("geo_locations")
	TargetingLocation geoLocations;

	@JsonProperty("excluded_geo_locations")
	TargetingLocation excludedGeoLocations;

	// placement
	@JsonProperty("page_types")
	List<Targeting.PageType> pageTypes;

	// connections
	@JsonProperty("connections")
	@JsonDeserialize(using = ListOfMapsDeserializer.class)
	List<String> connections;

	@JsonProperty("excluded_connections")
	@JsonDeserialize(using = ListOfMapsDeserializer.class)
	List<String> excludedConnections;

	@JsonProperty("friends_of_connections")
	@JsonDeserialize(using = ListOfMapsDeserializer.class)
	List<String> friendsOfConnections;

	// interests
	@JsonProperty("interests")
	List<TargetingEntry> interests;

	// behaviors
	@JsonProperty("behaviors")
	List<TargetingEntry> behaviors;

	// education and workplace
	@JsonProperty("education_schools")
	List<TargetingEntry> educationSchools;

	@JsonProperty("education_statuses")
	List<Targeting.EducationStatus> educationStatuses;

	@JsonProperty("college_years")
	List<Integer> collegeYears;

	@JsonProperty("education_majors")
	List<TargetingEntry> educationMajors;

	@JsonProperty("work_employers")
	List<TargetingEntry> workEmployers;

	@JsonProperty("work_positions")
	List<TargetingEntry> workPositions;

	private static class ListOfMapsDeserializer extends JsonDeserializer<List<String>> {

		@Override
		public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
				List<String> retList = new ArrayList<String>();
				try {
					JsonToken nextToken = jp.nextToken();
					while (nextToken != JsonToken.END_ARRAY) {
						if (nextToken == JsonToken.START_OBJECT) {
							HashMap<String, String> regionMap = jp.readValueAs(HashMap.class);
							retList.add(String.valueOf(regionMap.get("id")));
						} else {
							String value = jp.readValueAs(String.class);
							retList.add(value);
						}
						nextToken = jp.nextToken();
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
