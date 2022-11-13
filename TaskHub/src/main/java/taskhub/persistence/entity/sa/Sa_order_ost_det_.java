package taskhub.persistence.entity.sa;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import taskhub.persistence.constant.Inventory;
import taskhub.persistence.constant.ItemType;
import taskhub.persistence.constant.Service;
import taskhub.persistence.constant.UomType;
import taskhub.persistence.entity.Abstract_entity_;
import taskhub.persistence.entity.sa.Sa_order_ost_det.Pk;

@StaticMetamodel(Sa_order_ost_det.class)
public class Sa_order_ost_det_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Sa_order_ost_det, Pk> pk;
	public static volatile SingularAttribute<Sa_order_ost_det, String> sales_order_no;
	public static volatile SingularAttribute<Sa_order_ost_det, Integer> pk_no_det;
	public static volatile SingularAttribute<Sa_order_ost_det, ItemType> item_type;
	public static volatile SingularAttribute<Sa_order_ost_det, Double> qty;
	public static volatile SingularAttribute<Sa_order_ost_det, Double> gross_price;
	public static volatile SingularAttribute<Sa_order_ost_det, UomType> uom_code;
	public static volatile SingularAttribute<Sa_order_ost_det, Double> base_extended_amt;
	public static volatile SingularAttribute<Sa_order_ost_det, Inventory> inventory_code;
	public static volatile SingularAttribute<Sa_order_ost_det, Service> service_code;
	public static volatile SingularAttribute<Sa_order_ost_det, Sa_order_ost_hdr> sa_order_ost_hdr;
	public static volatile SingularAttribute<Sa_order_ost_det, String> item_remark;

	@StaticMetamodel(Pk.class)
	public static class Pk_ {
		public static volatile SingularAttribute<Pk, String> sales_order_no;
		public static volatile SingularAttribute<Pk, Integer> pk_no_det;
	}
}
