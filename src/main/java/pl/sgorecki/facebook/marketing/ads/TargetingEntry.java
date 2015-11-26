package pl.sgorecki.facebook.marketing.ads;

/**
 * @author Sebastian G�recki
 */
public class TargetingEntry {
	private long id;
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
