package taskhub.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Mt_user extends Abstract_entity {
	
	@Column(length = 50)
	@Size(max = 50)
	@Id
	private String user_id;
	
	@Column(length = 50)
	@Size(max = 50)
	private String login_id;
	
	@Column(length = 50)
	@Size(max = 50)
	private String login_password;

	@Column(length = 255)
	@Size(max = 255)
	private String alias;
	
	
	protected Mt_user() {
		super();
	}

	public Mt_user(@Size(max = 50) String user_id) {
		super();
		this.user_id = user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	

}
