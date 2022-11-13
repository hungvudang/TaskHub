package taskhub.persistence.entity.mt;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import taskhub.persistence.entity.Abstract_entity_;
import taskhub.persistence.entity.mt.Mt_user.Pk;

@Generated(value="Dali", date="2022-11-13T12:00:11.614+0700")
@StaticMetamodel(Mt_user.class)
public class Mt_user_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Mt_user, Pk> pk;
	public static volatile SingularAttribute<Mt_user, String> user_id;
	public static volatile SingularAttribute<Mt_user, String> login_id;
	public static volatile SingularAttribute<Mt_user, String> login_password;
	public static volatile SingularAttribute<Mt_user, String> alias;

	@StaticMetamodel(Pk.class)
	public static class Pk_ {
		public static volatile SingularAttribute<Pk, String> user_id;
	}
}
