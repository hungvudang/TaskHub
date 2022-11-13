package taskhub.persistence.entity.mt;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import taskhub.persistence.entity.Abstract_entity_;
import taskhub.persistence.entity.mt.Mt_document_numbering_scheme.Pk;

@Generated(value="Dali", date="2022-11-13T12:00:05.587+0700")
@StaticMetamodel(Mt_document_numbering_scheme.class)
public class Mt_document_numbering_scheme_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Mt_document_numbering_scheme, Pk> pk;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, String> numbering_scheme_key;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, String> prefix;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, String> suffix;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, Integer> last_no;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, Integer> length_to_fill;

	@StaticMetamodel(Pk.class)
	public static class Pk_ {
		public static volatile SingularAttribute<Pk, String> numbering_scheme_key;
	}
}
