package taskhub.persistence.constant;

public enum Inventory {
	I1("Inventory 1"), 
	I2("Inventory 2"), 
	I3("Inventory 3"),
	I4("Inventory 4"), 
	I5("Inventory 5"), 
	I6("Inventory 6"), 
	I7("Inventory 7");
	
	
	private String desc;

	private Inventory(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
	
}
