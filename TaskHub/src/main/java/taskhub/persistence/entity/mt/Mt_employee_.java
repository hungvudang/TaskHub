package taskhub.persistence.entity.mt;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import taskhub.persistence.entity.Abstract_entity_;
import taskhub.persistence.entity.mt.Mt_employee.Pk;

@Generated(value="Dali", date="2022-11-13T12:00:08.977+0700")
@StaticMetamodel(Mt_employee.class)
public class Mt_employee_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Mt_employee, Pk> pk;
	public static volatile SingularAttribute<Mt_employee, String> id;
	public static volatile SingularAttribute<Mt_employee, String> name;
	public static volatile SingularAttribute<Mt_employee, Date> dob;

	@StaticMetamodel(Pk.class)
	public static class Pk_ {
		public static volatile SingularAttribute<Pk, String> id;
	}
}
