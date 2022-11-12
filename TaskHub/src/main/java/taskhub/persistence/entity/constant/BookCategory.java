package taskhub.persistence.entity.constant;

public enum BookCategory {
	BUSINESS("Business"),
	COMICS("Comics"),
	COOKING("Cooking"),
	HISTORY("History"),
	KIDS("Kids");
	
	private String label;
	
	BookCategory(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	
}
