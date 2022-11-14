package taskhub.action.service.form;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import taskhub.action.service.AbstractService;
import taskhub.persistence.entity.FormInfo;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class ViewParamHolder extends AbstractService {
	
	private FormInfo formInfo;
	
	public FormInfo getFormInfo() {
		return this.formInfo;
	}

	public void setFormInfo(FormInfo formInfo) {
		this.formInfo = formInfo;
	}
	
	
	public void resetFormInfo() {
		this.formInfo = null;
	}
	
	
	
}
