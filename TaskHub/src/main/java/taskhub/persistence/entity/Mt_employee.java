package taskhub.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
public class Mt_employee extends Abstract_entity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(insertable = false, updatable = false, length = 50)
	@Size(max = 50)
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
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
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
	
	
	
   
}
