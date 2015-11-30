package pl.sgorecki.facebook.marketing.ads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdCreative {
	@JsonProperty("id")
	private String id;

	@JsonProperty("object_type")
	private AdCreative.AdCreativeType type;

	@JsonProperty("name")
	private String name;

	@JsonProperty("title")
	private String title;

	@JsonProperty("run_status")
	private AdCreative.AdCreativeStatus status;

	@JsonProperty("body")
	private String body;

	@JsonProperty("object_id")
	private String objectId;

	@JsonProperty("image_hash")
	private String imageHash;

	@JsonProperty("image_url")
	private String imageUrl;

	@JsonProperty("link_url")
	private String linkUrl;

	@JsonProperty("object_story_id")
	private String objectStoryId;

	@JsonProperty("object_url")
	private String objectUrl;

	@JsonProperty("url_tags")
	private String urlTags;

	@JsonProperty("thumbnail_url")
	private String thumbnailUrl;

	public AdCreativeType getType() {
		return type;
	}

	public void setType(AdCreativeType type) {
		this.type = type;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AdCreativeStatus getStatus() {
		return status;
	}

	public void setStatus(AdCreativeStatus status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getImageHash() {
		return imageHash;
	}

	public void setImageHash(String imageHash) {
		this.imageHash = imageHash;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getObjectStoryId() {
		return objectStoryId;
	}

	public void setObjectStoryId(String objectStoryId) {
		this.objectStoryId = objectStoryId;
	}

	public String getObjectUrl() {
		return objectUrl;
	}

	public void setObjectUrl(String objectUrl) {
		this.objectUrl = objectUrl;
	}

	public String getUrlTags() {
		return urlTags;
	}

	public void setUrlTags(String urlTags) {
		this.urlTags = urlTags;
	}

	public enum AdCreativeType {
		PAGE, DOMAIN, EVENT, STORE_ITEM, OFFER, SHARE, PHOTO, STATUS, VIDEO, APPLICATION, INVALID, UNKNOWN;

		@JsonCreator
		public static AdCreativeType fromValue(String value) {
			for (AdCreativeType type : AdCreativeType.values()) {
				if (type.name().equals(value)) {
					return type;
				}
			}
			return UNKNOWN;
		}
	}

	public enum AdCreativeStatus {
		PENDING, ACTIVE, PAUSED, DELETED, PENDING_REVIEW, DISAPPROVED, PREAPPROVED, PENDING_BILLING_INFO,
		CAMPAIGN_PAUSED, ADGROUP_PAUSED, CAMPAIGN_GROUP_PAUSED, ARCHIVED, UNKNOWN;

		@JsonCreator
		public static AdCreativeStatus fromValue(String value) {
			for (AdCreativeStatus status : AdCreativeStatus.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return UNKNOWN;
		}
	}
}
