package taskhub.persistence.entity.mt;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import taskhub.persistence.entity.Abstract_entity;
import taskhub.persistence.entity._Pk;

@SuppressWarnings("serial")
@Entity
public class Mt_user extends Abstract_entity {
	
	@EmbeddedId
	private Pk pk;
	
	@Column(updatable = false, insertable = false)
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
		this.pk = new Pk(user_id);
		this.user_id = user_id;
	}

	public String getUser_id() {
		return this.pk.getUser_id();
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

	@Override
	public _Pk getPk() {
		return this.pk;
	}
	
	@Embeddable
	public static class Pk extends _Pk {
		@Column(length = 50, updatable = false, insertable = false)
		@Size(max = 50)
		private String user_id;

		public Pk() {
		}
		
		public Pk(@Size(max = 50) String user_id) {
			super();
			this.user_id = user_id;
		}

		public String getUser_id() {
			return this.user_id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.user_id == null) ? 0 : this.user_id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pk other = (Pk) obj;
			if (this.user_id == null) {
				if (other.user_id != null)
					return false;
			} else if (!this.user_id.equals(other.user_id))
				return false;
			return true;
		}
		
	}
	
	
	

}
