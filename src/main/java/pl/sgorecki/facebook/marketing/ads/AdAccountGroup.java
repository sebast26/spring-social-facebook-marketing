package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.facebook.api.FacebookObject;

/**
 * Model class representing an ad account group.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdAccountGroup extends FacebookObject {
	@JsonProperty("account_group_id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("status")
	private int status;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStatus() {
		return status;
	}
}
