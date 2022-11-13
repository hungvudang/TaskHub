package taskhub.persistence.entity.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import taskhub.persistence.constant.BookCategory;
import taskhub.persistence.entity.Abstract_entity;
import taskhub.persistence.entity._Pk;

@SuppressWarnings("serial")
@Entity
public class Book_ost_hdr extends Abstract_entity {

	@EmbeddedId
	private Pk pk;
	
	@Column(updatable = false, insertable = false)
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
	private boolean variation = false;

	@Column
	@Enumerated(EnumType.STRING)
	private BookCategory book_category;

	protected Book_ost_hdr() {
	}

	public Book_ost_hdr(@Size(max = 50) String book_code) {
		this.pk = new Pk(book_code);
		this.book_code = book_code;
	}

	public String getBook_code() {
		return this.pk.getBook_code();
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
	
	public boolean isVariation() {
		return this.variation;
	}

	public void setVariation(boolean variation) {
		this.variation = variation;
	}

	public BookCategory getBook_category() {
		return this.book_category;
	}

	public void setBook_category(BookCategory book_category) {
		this.book_category = book_category;
	}
	
	@Override
	public _Pk getPk() {
		return this.pk;
	}
	
	@Embeddable
	public static class Pk extends _Pk {
		@Column(insertable = false, updatable = false, length = 50)
		@Size(max = 50)
		private String book_code;
		
		public Pk() {
		}
		
		public Pk(@Size(max = 50) String book_code) {
			super();
			this.book_code = book_code;
		}

		public String getBook_code() {
			return this.book_code;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.book_code == null) ? 0 : this.book_code.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pk other = (Pk) obj;
			if (this.book_code == null) {
				if (other.book_code != null)
					return false;
			} else if (!this.book_code.equals(other.book_code))
				return false;
			return true;
		}
		
	}
	

}
