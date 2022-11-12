package taskhub.persistence.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

	@StaticMetamodel(Book_new_hdr.class)
public class Book_new_hdr_ extends Abstract_entity_ {
	public static volatile SingularAttribute<Book_new_hdr, String> book_code;
	public static volatile SingularAttribute<Book_new_hdr, String> title;
	public static volatile SingularAttribute<Book_new_hdr, String> author;
	public static volatile SingularAttribute<Book_new_hdr, String> book_desc;
	public static volatile SingularAttribute<Book_new_hdr, byte[]> book_img;
	public static volatile SingularAttribute<Book_new_hdr, Date> date_release;
	public static volatile SingularAttribute<Book_new_hdr, Integer> number_of_page;
	public static volatile SingularAttribute<Book_new_hdr, String> book_category;
}
