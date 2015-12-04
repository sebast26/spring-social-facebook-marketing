package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Annotated mixin to add Jackson annotations to TargetingEntry.
 *
 * @author Sebastian Górecki
 */
public abstract class TargetingEntryMixin {
	@JsonProperty("id")
	long id;

	@JsonProperty("name")
	String name;
}
