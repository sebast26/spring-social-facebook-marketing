package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Annotated mixin to add Jackson annotations to AdAccountGroup.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AdAccountGroupMixin {

	@JsonProperty("account_group_id")
	String id;

	@JsonProperty("name")
	String name;

	@JsonProperty("status")
	int status;
}
