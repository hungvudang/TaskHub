package taskhub.persistence.entity.sa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import taskhub.persistence.constant.Inventory;
import taskhub.persistence.constant.ItemType;
import taskhub.persistence.constant.Service;
import taskhub.persistence.constant.UomType;
import taskhub.persistence.entity.Abstract_entity;
import taskhub.persistence.entity._Pk;

@SuppressWarnings("serial")
@Entity
public class Sa_order_ost_det extends Abstract_entity {
	
	@EmbeddedId
	private Pk pk;
	@Column(updatable = false, insertable = false)
	private String sales_order_no;
	
	@Column(updatable = false, insertable = false)
	private int pk_no_det;
	
	@Column
	@Enumerated(EnumType.STRING)
	private ItemType item_type;
	
	@Column
	private double qty;
	
	@Column
	private double gross_price;
	
	@Column
	@Enumerated(EnumType.STRING)
	private UomType uom_code;
	
	@Column
	private double base_extended_amt;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Inventory inventory_code;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Service service_code;
	
	@Column(length = 2000)
	@Size(max = 2000)
	private String item_remark;
	
	protected Sa_order_ost_det() {
	}
	
	public Sa_order_ost_det(final Sa_order_ost_hdr sa_order_ost_hdr, final int pk_no_det) {
		this(sa_order_ost_hdr.getSales_order_no(), pk_no_det);
		this.sa_order_ost_hdr = sa_order_ost_hdr;
		this.sa_order_ost_hdr.getSa_order_ost_dets().add(this);
	}
	
	public Sa_order_ost_det(final String sales_order_no, final int pk_no_det) {
		this.pk = new Pk(sales_order_no, pk_no_det);
		this.sales_order_no = sales_order_no;
		this.pk_no_det = pk_no_det;
	}
	
	public String getSales_order_no() {
		return this.pk.getSales_order_no();
	}
	
	public int getPk_no_det() {
		return this.pk.getPk_no_det();
	}
	
	
	public ItemType getItem_type() {
		return this.item_type;
	}

	public void setItem_type(ItemType item_type) {
		this.item_type = item_type;
	}

	public double getQty() {
		return this.qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getGross_price() {
		return this.gross_price;
	}

	public void setGross_price(double gross_price) {
		this.gross_price = gross_price;
	}

	public UomType getUom_code() {
		return this.uom_code;
	}

	public void setUom_code(UomType uom_code) {
		this.uom_code = uom_code;
	}
	
	public double getBase_extended_amt() {
		return this.base_extended_amt;
	}

	public void setBase_extended_amt(double base_extended_amt) {
		this.base_extended_amt = base_extended_amt;
	}
	

	public Inventory getInventory_code() {
		return this.inventory_code;
	}

	public void setInventory_code(Inventory inventory_code) {
		this.inventory_code = inventory_code;
	}

	public Service getService_code() {
		return this.service_code;
	}

	public void setService_code(Service service_code) {
		this.service_code = service_code;
	}
	
	public String getItem_remark() {
		return this.item_remark;
	}

	public void setItem_remark(String item_remark) {
		this.item_remark = item_remark;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "sales_order_no", referencedColumnName = "sales_order_no", updatable = false, insertable = false)
	})
	private Sa_order_ost_hdr sa_order_ost_hdr;
	
	public Sa_order_ost_hdr getSa_order_ost_hdr() {
		return this.sa_order_ost_hdr;
	}

	public void setSa_order_ost_hdr(Sa_order_ost_hdr sa_order_ost_hdr) {
		this.sa_order_ost_hdr = sa_order_ost_hdr;
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
		
		@Column(insertable = false, updatable = false)
		private int pk_no_det;
		
		public Pk() {
		}
		
		public Pk(@Size(max = 50) String sales_order_no, int pk_no_det) {
			this.sales_order_no = sales_order_no;
			this.pk_no_det = pk_no_det;
		}

		public String getSales_order_no() {
			return this.sales_order_no;
		}

		public int getPk_no_det() {
			return this.pk_no_det;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + this.pk_no_det;
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
			if (this.pk_no_det != other.pk_no_det)
				return false;
			if (this.sales_order_no == null) {
				if (other.sales_order_no != null)
					return false;
			} else if (!this.sales_order_no.equals(other.sales_order_no))
				return false;
			return true;
		}
		
		
	}
	
}
