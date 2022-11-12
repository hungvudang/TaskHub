package taskhub.action.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import taskhub.cdi.helper.ConversationHelper;
import taskhub.persistence.constant.FormCode;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class FormSwitcher extends AbstractService {
	// private static final String HOME = "HOME";
	
	@Inject
	private AuthServiceHelper authServiceHelper;
	
	@Inject
	private ViewParamService viewParamService;
	
	public void home() {
		viewParamService.resetInfo();
		if (!authServiceHelper.isLoggedIn()) {
			this.switchToLogin();
		} else {
			ConversationHelper.beginIfTransient();
		}
	}

	public void switchToLogin() {
		authServiceHelper.setGuest(false);
		authServiceHelper.clear();
		viewParamService.resetInfo();
		ConversationHelper.endIfNotTransient();
	}
	
	public void switchToFormCode(final FormCode formCode) {
		viewParamService.resetInfo();
		ConversationHelper.endIfNotTransient();
		
		if (formCode != null) {
			this.viewParamService.setActiveFormCode(formCode);
			ConversationHelper.beginIfTransient();
		}
		
		
	}
}
