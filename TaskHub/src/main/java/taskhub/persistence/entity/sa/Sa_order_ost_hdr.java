package taskhub.persistence.entity.sa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import taskhub.persistence.entity.Abstract_entity;
import taskhub.persistence.entity._Pk;


@SuppressWarnings("serial")
@Entity
public class Sa_order_ost_hdr extends Abstract_entity {
	@EmbeddedId
	private Pk pk;
	
	@Column(updatable = false, insertable = false)
	private String sales_order_no;
	
	protected Sa_order_ost_hdr() {
		
	}
	
	public Sa_order_ost_hdr(final String sales_order_no ) {
		this.pk = new Pk(sales_order_no);
		this.sales_order_no = sales_order_no;
	}
	
	public String getSa_order_no() {
		return this.pk.getSales_order_no();
	}
	
	@OneToMany(mappedBy = "sa_order_ost_hdr", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Sa_order_ost_det> sa_order_ost_dets;
	
	public List<Sa_order_ost_det> getSa_order_ost_dets() {
		return this.sa_order_ost_dets;
	}

	public void setSa_order_ost_dets(List<Sa_order_ost_det> sa_order_ost_dets) {
		this.sa_order_ost_dets = sa_order_ost_dets;
	}

	@Override
	public _Pk getPk() {
		return this.pk;
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
