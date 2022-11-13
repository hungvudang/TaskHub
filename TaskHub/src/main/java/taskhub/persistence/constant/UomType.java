package taskhub.persistence.constant;

public enum UomType {
	PCS("PCS"),
	LIT("LIT"),
	PACK("PACK"),
	BOX("BOX");
	
	private String label;
	
	private UomType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	
	
}
