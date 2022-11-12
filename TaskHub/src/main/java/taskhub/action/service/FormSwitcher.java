package taskhub.action.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import taskhub.cdi.helper.ConversationHelper;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class FormSwitcher extends AbstractService {
	// private static final String HOME = "HOME";

	@Inject
	private AuthServiceHelper authServiceHelper;

	public void home() {
		if (!authServiceHelper.isLoggedIn()) {
			this.switchToLogin();
		} else {
			ConversationHelper.beginIfTransient();
		}
	}

	public void switchToLogin() {
		authServiceHelper.setGuest(false);
		authServiceHelper.clear();
		ConversationHelper.endIfNotTransient();
	}
}
