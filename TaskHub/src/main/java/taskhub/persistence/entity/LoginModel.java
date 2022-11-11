package taskhub.persistence.entity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginModel {
	private String login_id;
	private String login_password;
	
	public LoginModel() {
	}

	public LoginModel(String login_id, String login_password) {
		this.login_id = login_id;
		this.login_password = login_password;
	}
	
	
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}
}
