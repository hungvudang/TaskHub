package taskhub.persistence.constant;

public enum FormCode {
	SALES("processing", "sa", "sales"),
	CUSTOMER("master_setup", "mt", "customer"),
	BOOK_MANAGEMENT("demo", "bo", "book");
	
	private String context;
	private String module_code;
	private String form_code;
	
	private FormCode(String context, final String module_code, final String form_code) {
		this.context = context;
		this.module_code = module_code;
		this.form_code = form_code;
	}

	public String getContext() {
		return this.context;
	}
	
	public String getModule_code() {
		return this.module_code;
	}

	public String getForm_code() {
		return this.form_code;
	}
	
}
