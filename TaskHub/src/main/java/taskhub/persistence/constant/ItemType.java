package taskhub.persistence.constant;

public enum ItemType {
	I("Inventory"),
	S("Service");
	
	private String label;

	private ItemType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
	
}
