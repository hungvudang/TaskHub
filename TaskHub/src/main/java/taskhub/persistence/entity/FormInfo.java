package taskhub.persistence.entity;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import taskhub.persistence.constant.FormCode;

@SuppressWarnings("serial")
@Named
@RequestScoped
public class FormInfo implements Serializable{
	private FormCode formCode;

	
	public FormInfo() {
	}

	public FormInfo(FormCode formCode) {
		this.formCode = formCode;
	}

	public FormCode getFormCode() {
		return this.formCode;
	}

	public void setFormCode(FormCode formCode) {
		this.formCode = formCode;
	}
	
}
