package taskhub.persistence.entity.sa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import taskhub.persistence.entity.Abstract_entity;
import taskhub.persistence.entity._Pk;

@SuppressWarnings("serial")
@Entity
public class Sa_order_new_hdr extends Abstract_entity {
	@EmbeddedId
	private Pk pk;

	@Column(updatable = false, insertable = false)
	private String sales_order_no;

	@Column(length = 50)
	@Size(max = 50)
	private String customer_code;

	@Column(length = 255)
	@Size(max = 255)
	private String customer_name;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date order_date;

	@Column(length = 2000)
	@Size(max = 2000)
	private String customer_address;

	@Column(length = 255)
	@Size(max = 255)
	private String customer_contact_no;

	@Column(length = 255)
	@Size(max = 255)
	private String ref_no;

	@Column(length = 2000)
	@Size(max = 2000)
	private String subject;

	@Column
	private double total_amt;

	protected Sa_order_new_hdr() {

	}

	public Sa_order_new_hdr(final String sales_order_no) {
		this.pk = new Pk(sales_order_no);
		this.sales_order_no = sales_order_no;
	}

	public String getSales_order_no() {
		return this.pk.getSales_order_no();
	}

	public String getCustomer_code() {
		return this.customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getCustomer_name() {
		return this.customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_address() {
		return this.customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_contact_no() {
		return this.customer_contact_no;
	}

	public void setCustomer_contact_no(String customer_contact_no) {
		this.customer_contact_no = customer_contact_no;
	}

	public String getRef_no() {
		return this.ref_no;
	}

	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getOrder_date() {
		return this.order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public double getTotal_amt() {
		return this.total_amt;
	}

	public void setTotal_amt(double total_amt) {
		this.total_amt = total_amt;
	}

	@OneToMany(mappedBy = "sa_order_new_hdr", cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Sa_order_new_det> sa_order_new_dets = new ArrayList<Sa_order_new_det>();

	public List<Sa_order_new_det> getSa_order_new_dets() {
		return this.sa_order_new_dets;
	}

	public void setSa_order_new_dets(List<Sa_order_new_det> sa_order_new_dets) {
		this.sa_order_new_dets = sa_order_new_dets;
	}

	@Override
	public _Pk getPk() {
		return this.pk;
	}
	
	public void addTotal_amt(final double delta) {
		this.setTotal_amt(this.getTotal_amt() + delta);
	}
	

	@Embeddable
	public static class Pk extends _Pk {

		@Column(insertable = false, updatable = false, length = 50)
		@Size(max = 50)
		private String sales_order_no;

		public Pk() {
		}

		public Pk(@Size(max = 50) String sales_order_no) {
			super();
			this.sales_order_no = sales_order_no;
		}

		public String getSales_order_no() {
			return this.sales_order_no;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.sales_order_no == null) ? 0 : this.sales_order_no.hashCode());
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
			if (this.sales_order_no == null) {
				if (other.sales_order_no != null)
					return false;
			} else if (!this.sales_order_no.equals(other.sales_order_no))
				return false;
			return true;
		}

	}

}
