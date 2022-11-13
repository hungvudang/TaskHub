package taskhub.action.service.form;

import java.io.Serializable;
import java.util.Arrays;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import taskhub.action.service.helper.AuthServiceHelper;
import taskhub.cdi.helper.ConversationHelper;
import taskhub.persistence.constant.FormCode;
import taskhub.persistence.entity.FormInfo;

@SuppressWarnings("serial")
@Named
@RequestScoped
public class FormSwitcher implements Serializable {

	@Inject
	private AuthServiceHelper authServiceHelper;

	@Inject
	private ViewParamHolder viewParamHolder;
	
	public String locateSrcForm() {
		final FormInfo formInfo = this.viewParamHolder.getFormInfo();
		if (formInfo != null && formInfo.getFormCode() != null) {
			final FormCode form = formInfo.getFormCode();
			return "/" + StringUtils.join(Arrays.asList("faces", form.getContext(), form.getModule_code(),
											form.getForm_code(), "_" + form.name() + ".xhtml"), "/");
		}
		return null;
	}

	public void home() {
		this.viewParamHolder.resetFormInfo();
		if (!this.authServiceHelper.isLoggedIn()) {
			this.switchLogInView();
		} else {
			ConversationHelper.beginIfTransient();
		}
	}

	public void switchLogInView() {
		this.viewParamHolder.resetInfo();
		this.authServiceHelper.clear();
		ConversationHelper.endIfNotTransient();
	}

	public void _switch(final FormCode formCode) {
		final FormInfo formInfo = new FormInfo(formCode);
		this.viewParamHolder.setFormInfo(formInfo);
		ConversationHelper.beginIfTransient();
	}

}
