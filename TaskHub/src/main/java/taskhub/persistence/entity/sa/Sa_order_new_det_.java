package taskhub.persistence.entity.sa;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import taskhub.persistence.entity.Abstract_entity_;
import taskhub.persistence.entity.sa.Sa_order_new_det.Pk;

@Generated(value="Dali", date="2022-11-13T12:00:22.440+0700")
@StaticMetamodel(Sa_order_new_det.class)
public class Sa_order_new_det_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Sa_order_new_det, Pk> pk;
	public static volatile SingularAttribute<Sa_order_new_det, String> sales_order_no;
	public static volatile SingularAttribute<Sa_order_new_det, Integer> pk_no_det;
	public static volatile SingularAttribute<Sa_order_new_det, Sa_order_new_hdr> sa_order_new_hdr;

	@StaticMetamodel(Pk.class)
	public static class Pk_ {
		public static volatile SingularAttribute<Pk, String> sales_order_no;
		public static volatile SingularAttribute<Pk, Integer> pk_no_det;
	}
}
