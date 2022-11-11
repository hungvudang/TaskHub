package taskhub.persistence.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Abstract_entity.class)
public class Abstract_entity_ {
	public static volatile SingularAttribute<Abstract_entity, String> created_by;
	public static volatile SingularAttribute<Abstract_entity, Date> created_datetime;
	public static volatile SingularAttribute<Abstract_entity, String> last_updated_by;
	public static volatile SingularAttribute<Abstract_entity, Date> last_updated_datetime;
	public static volatile SingularAttribute<Abstract_entity, Integer> object_version;
}
