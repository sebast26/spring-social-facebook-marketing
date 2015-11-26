package pl.sgorecki.facebook.marketing.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Annotated mixin to add Jackson annotations to AdInsightAction.
 *
 * @author Sebastian G�recki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AdInsightActionMixin {
	@JsonProperty("action_type")
	String actionType;

	@JsonProperty("value")
	double value;
}
