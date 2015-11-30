package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetingEntry {
	@JsonProperty("id")
	private long id;

	@JsonProperty("name")
	private String name;

	public TargetingEntry() {
	}

	public TargetingEntry(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
