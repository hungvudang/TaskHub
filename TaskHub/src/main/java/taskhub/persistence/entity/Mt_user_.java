package taskhub.persistence.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Mt_user.class)
public class Mt_user_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Mt_user, String> user_id;
	public static volatile SingularAttribute<Mt_user, String> login_id;
	public static volatile SingularAttribute<Mt_user, String> login_password;
	public static volatile SingularAttribute<Mt_user, String> alias;
}
