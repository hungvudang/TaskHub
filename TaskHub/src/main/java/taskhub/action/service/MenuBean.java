package taskhub.action.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import taskhub.persistence.constant.FormCode;

@Named
@RequestScoped
public class MenuBean {

	@Inject
	private FormSwitcher formSwitcher;
	
	private FormCode currentForm;
	
	public void _switch() {
		this.formSwitcher.switchToFormCode(this.currentForm);
	}

	public FormCode getCurrentForm() {
		return this.currentForm;
	}

	public void setCurrentForm(FormCode currentForm) {
		this.currentForm = currentForm;
	}
	
	
	
	
}
