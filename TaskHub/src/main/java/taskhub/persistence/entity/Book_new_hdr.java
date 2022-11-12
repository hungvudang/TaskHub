package taskhub.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import taskhub.persistence.constant.BookCategory;

@Entity
public class Book_new_hdr extends Abstract_entity {
	
	@Id
	@Column(insertable = false, updatable = false, length = 50)
	@Size(max = 50)
	private String book_code;

	@Column(length = 255)
	@Size(max = 255)
	private String title;

	@Column(length = 50)
	@Size(max = 50)
	private String author;

	@Column(length = 2000)
	@Size(max = 2000)
	private String book_desc;

	@Lob
	@Column
	private byte[] book_img;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date_release;

	@Column
	private int number_of_page;

	@Column
	@Enumerated(EnumType.STRING)
	private BookCategory book_category;

	protected Book_new_hdr() {
	}

	public Book_new_hdr(@Size(max = 50) String book_code) {
		this.book_code = book_code;
	}

	public String getBook_code() {
		return this.book_code;
	}

	protected void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBook_desc() {
		return this.book_desc;
	}

	public void setBook_desc(String book_desc) {
		this.book_desc = book_desc;
	}

	public byte[] getBook_img() {
		return this.book_img;
	}

	public void setBook_img(byte[] book_img) {
		this.book_img = book_img;
	}

	public Date getDate_release() {
		return this.date_release;
	}

	public void setDate_release(Date date_release) {
		this.date_release = date_release;
	}

	public int getNumber_of_page() {
		return this.number_of_page;
	}

	public void setNumber_of_page(int number_of_page) {
		this.number_of_page = number_of_page;
	}

	public BookCategory getBook_category() {
		return this.book_category;
	}

	public void setBook_category(BookCategory book_category) {
		this.book_category = book_category;
	}

}
