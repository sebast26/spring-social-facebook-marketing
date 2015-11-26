package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sgorecki.facebook.marketing.ads.AdUser;

import java.util.List;


/**
 * Annotated mixin to add Jackson annotations to AdUser.
 *
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AdUserMixin {
	@JsonProperty("id")
	String id;

	@JsonProperty("name")
	String name;

	@JsonProperty("permissions")
	List<AdUser.AdUserPermission> permissions;

	@JsonProperty("role")
	AdUser.AdUserRole role;
}
