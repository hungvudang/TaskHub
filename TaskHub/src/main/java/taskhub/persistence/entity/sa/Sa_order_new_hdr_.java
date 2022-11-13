package taskhub.persistence.entity.sa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import taskhub.persistence.entity.Abstract_entity_;
import taskhub.persistence.entity.sa.Sa_order_new_hdr.Pk;

@Generated(value="Dali", date="2022-11-13T12:00:24.742+0700")
@StaticMetamodel(Sa_order_new_hdr.class)
public class Sa_order_new_hdr_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Sa_order_new_hdr, Pk> pk;
	public static volatile SingularAttribute<Sa_order_new_hdr, String> sales_order_no;
	public static volatile ListAttribute<Sa_order_new_hdr, Sa_order_new_det> sa_order_new_dets;

	@StaticMetamodel(Pk.class)
	public static class Pk_ {
		public static volatile SingularAttribute<Pk, String> sales_order_no;
	}
}
