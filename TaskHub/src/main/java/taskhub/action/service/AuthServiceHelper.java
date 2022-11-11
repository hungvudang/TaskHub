package taskhub.action.service;

import java.util.Objects;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import taskhub.persistence.QueryHelper;
import taskhub.persistence.entity.LoginModel;
import taskhub.persistence.entity.Mt_user;
import taskhub.persistence.entity.Mt_user_;

@SuppressWarnings("serial")
@Named
@ConversationScoped
public class AuthServiceHelper extends AbstractService {

	private static final ThreadLocal<Mt_user> LOCAL_CONTEXT = new ThreadLocal<Mt_user>();

	@Inject
	private LoginModel model;

	public boolean isLoggedIn() {
		return !Objects.isNull(LOCAL_CONTEXT.get());
	}

	public void login() {
		if (this.model != null) {
			if (this.authenticate()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfuly", ""));
			} else {
				AuthServiceHelper.LOCAL_CONTEXT.set(null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Faild", "You have entered invalid username or password. Please try again."));
			}
		}
	}

	private boolean authenticate() {
		final QueryHelper<Mt_user, Mt_user> queryHelper = QueryHelper.create(Mt_user.class);
		queryHelper.query.where(queryHelper.cb.equal(queryHelper.root.get(Mt_user_.login_id), this.model.getLogin_id()),
				queryHelper.cb.equal(queryHelper.root.get(Mt_user_.login_password), this.model.getLogin_password()));

		try {
			final Mt_user userAuth = this.em.createQuery(queryHelper.query).setMaxResults(1).getSingleResult();
			AuthServiceHelper.LOCAL_CONTEXT.set(userAuth);
			return true;
		} catch (NoResultException | NonUniqueResultException e) {
			AuthServiceHelper.LOCAL_CONTEXT.set(null);
			return false;
		}
	}

	public LoginModel getModel() {
		return model;
	}

	public void setModel(LoginModel model) {
		this.model = model;
	}

}
