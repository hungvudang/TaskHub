package taskhub.persistence.entity.mt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import taskhub.persistence.entity.Abstract_entity;
import taskhub.persistence.entity._Pk;

@SuppressWarnings("serial")
@Entity
public class Mt_employee extends Abstract_entity {

	@EmbeddedId
	private Pk pk;

	@Column(updatable = false, insertable = false)
	private String id;

	@Column(length = 255)
	@Size(max = 255)
	private String name;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	protected Mt_employee() {

	}

	public Mt_employee(@Size(max = 50) String id) {
		this.pk = new Pk(id);
		this.id = id;
	}

	public String getId() {
		return this.pk.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public _Pk getPk() {
		return this.pk;
	}

	@Embeddable
	public static class Pk extends _Pk {
		@Column(insertable = false, updatable = false, length = 50)
		@Size(max = 50)
		private String id;

		public Pk() {
		}
		
		public Pk(@Size(max = 50) String id) {
			super();
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
			if (this.id == null) {
				if (other.id != null)
					return false;
			} else if (!this.id.equals(other.id))
				return false;
			return true;
		}

	}

}
