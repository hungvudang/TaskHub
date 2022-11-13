package taskhub.persistence.entity.sa;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import taskhub.persistence.entity.Abstract_entity_;
import taskhub.persistence.entity.sa.Sa_order_ost_hdr.Pk;

@StaticMetamodel(Sa_order_ost_hdr.class)
public class Sa_order_ost_hdr_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Sa_order_ost_hdr, Pk> pk;
	public static volatile SingularAttribute<Sa_order_ost_hdr, String> sales_order_no;
	public static volatile ListAttribute<Sa_order_ost_hdr, Sa_order_ost_det> sa_order_ost_dets;

	@StaticMetamodel(Pk.class)
	public static class Pk_ {
		public static volatile SingularAttribute<Pk, String> sales_order_no;
	}
}
