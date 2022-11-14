package taskhub.action.service.form;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import taskhub.action.service.helper.AuthServiceHelper;
import taskhub.cdi.helper.ConversationHelper;
import taskhub.persistence.constant.FormCode;
import taskhub.persistence.entity.FormInfo;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class FormSwitcher implements Serializable {

	@Inject
	private AuthServiceHelper authServiceHelper;

	@Inject
	private ViewParamHolder viewParamHolder;

	public void home() {
		this.viewParamHolder.resetFormInfo();
		if (!this.authServiceHelper.isLoggedIn()) {
			this.switchLogInView();
		} else {
			ConversationHelper.beginIfTransient();
		}
	}

	public void switchLogInView() {
		ConversationHelper.endIfNotTransient();
		this.authServiceHelper.clear();
		this.viewParamHolder.resetFormInfo();
	}

	public void _switch(final FormCode formCode) {
		final FormInfo formInfo = new FormInfo(formCode);
		this.viewParamHolder.setFormInfo(formInfo);
		ConversationHelper.beginIfTransient();
	}

}
