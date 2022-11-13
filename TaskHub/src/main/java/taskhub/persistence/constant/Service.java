package taskhub.persistence.constant;

public enum Service {
	S1("Service 1"),
	S2("Service 2"),S3("Service 3"),
	S4("Service 4"), S5("Service 5"), S6("Service 6");
	
	private String desc;

	private Service(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
	
}
