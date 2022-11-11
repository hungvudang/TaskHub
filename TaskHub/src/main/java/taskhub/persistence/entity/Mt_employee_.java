package taskhub.persistence.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Mt_employee.class)
public class Mt_employee_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Mt_employee, String> id;
	public static volatile SingularAttribute<Mt_employee, String> name;
	public static volatile SingularAttribute<Mt_employee, Date> dob;
}
