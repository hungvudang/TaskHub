package taskhub.action.service.helper;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import taskhub.action.service.AbstractService;
import taskhub.action.service.form.FormSwitcher;
import taskhub.cdi.Instances;
import taskhub.cdi.helper.ConversationHelper;
import taskhub.persistence.QueryHelper;
import taskhub.persistence.entity.LoginModel;
import taskhub.persistence.entity.mt.Mt_user;
import taskhub.persistence.entity.mt.Mt_user_;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class AuthServiceHelper extends AbstractService {

	private static final ThreadLocal<Mt_user> LOCAL_CONTEXT = new ThreadLocal<Mt_user>();
	
	@Inject
	private LoginModel model;

	private boolean guest = false;
	private boolean loggedIn = false;

	public boolean isLoggedIn() {
		return this.loggedIn;
	}
	
	public void setLoggedIn(final boolean b) {
		this.loggedIn = b;
	}
	
	public Mt_user getCurrentUser() {
		return AuthServiceHelper.LOCAL_CONTEXT.get() ;
	}

	public void login() {
		if (this.model != null) {
			if (this.authenticate()) {
				Instances.get(FormSwitcher.class).home();
			} else {
				AuthServiceHelper.LOCAL_CONTEXT.set(null);
				this.loggedIn = true;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Faild", "You have entered invalid username or password. Please try again."));
			}
		}
	}

	public void loginAsGuest() {
		this.guest = true;
		AuthServiceHelper.LOCAL_CONTEXT.set(null);
		this.loggedIn = false;
		ConversationHelper.beginIfTransient();
	}

	private boolean authenticate() {
		final QueryHelper<Mt_user, Mt_user> queryHelper = QueryHelper.create(Mt_user.class);
		queryHelper.query.where(queryHelper.cb.equal(queryHelper.root.get(Mt_user_.login_id), this.model.getLogin_id()),
				queryHelper.cb.equal(queryHelper.root.get(Mt_user_.login_password), this.model.getLogin_password()));

		try {
			final Mt_user userAuth = this.em.createQuery(queryHelper.query).setMaxResults(1).getSingleResult();
			AuthServiceHelper.LOCAL_CONTEXT.set(userAuth);
			this.loggedIn = true;
			return true;
		} catch (NoResultException | NonUniqueResultException e) {
			AuthServiceHelper.LOCAL_CONTEXT.set(null);
			this.loggedIn = false;
			return false;
		} finally {
			this.guest = false;
		}
	}

	public void clear() {
		this.guest = false;
		this.loggedIn = false;
		AuthServiceHelper.LOCAL_CONTEXT.set(null);
	}

	public LoginModel getModel() {
		return model;
	}

	public void setModel(LoginModel model) {
		this.model = model;
	}

	public boolean isGuest() {
		return this.guest;
	}

	public void setGuest(boolean guest) {
		this.guest = guest;
	}

}
