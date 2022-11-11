package taskhub.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Abstract_entity {

	@Column(length = 50)
	@Size(max = 50)
	private String created_by;
	
	@Column
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date created_datetime;
	
	@Column(length = 50)
	@Size(max = 50)
	private String last_updated_by;
	
	
	@Column
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date last_updated_datetime;
	
	@Version
	private Integer object_version;

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(Date created_datetime) {
		this.created_datetime = created_datetime;
	}

	public String getLast_updated_by() {
		return last_updated_by;
	}

	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public Date getLast_updated_datetime() {
		return last_updated_datetime;
	}

	public void setLast_updated_datetime(Date last_updated_datetime) {
		this.last_updated_datetime = last_updated_datetime;
	}
	
	
	@PrePersist
	protected void prePersist() {
		this.setCreated_datetime( new Date());
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.setLast_updated_datetime(new Date());
	}
}
