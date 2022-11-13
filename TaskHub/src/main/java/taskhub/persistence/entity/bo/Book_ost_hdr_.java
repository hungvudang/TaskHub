package taskhub.persistence.entity.bo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import taskhub.persistence.constant.BookCategory;
import taskhub.persistence.entity.Abstract_entity_;
import taskhub.persistence.entity.bo.Book_ost_hdr.Pk;

@Generated(value="Dali", date="2022-11-13T12:00:14.502+0700")
@StaticMetamodel(Book_ost_hdr.class)
public class Book_ost_hdr_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Book_ost_hdr, Pk> pk;
	public static volatile SingularAttribute<Book_ost_hdr, String> book_code;
	public static volatile SingularAttribute<Book_ost_hdr, String> title;
	public static volatile SingularAttribute<Book_ost_hdr, String> author;
	public static volatile SingularAttribute<Book_ost_hdr, String> book_desc;
	public static volatile SingularAttribute<Book_ost_hdr, byte[]> book_img;
	public static volatile SingularAttribute<Book_ost_hdr, Date> date_release;
	public static volatile SingularAttribute<Book_ost_hdr, Integer> number_of_page;
	public static volatile SingularAttribute<Book_ost_hdr, Boolean> variation;
	public static volatile SingularAttribute<Book_ost_hdr, BookCategory> book_category;

	@StaticMetamodel(Pk.class)
	public static class Pk_ {
		public static volatile SingularAttribute<Pk, String> book_code;
	}
}
