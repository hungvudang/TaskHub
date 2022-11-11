package taskhub.persistence.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Mt_document_numbering_scheme.class)
public class Mt_document_numbering_scheme_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Mt_document_numbering_scheme, String> numbering_scheme_key;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, String> prefix;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, String> suffix;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, Integer> last_no;
	public static volatile SingularAttribute<Mt_document_numbering_scheme, Integer> length_to_fill;
}
