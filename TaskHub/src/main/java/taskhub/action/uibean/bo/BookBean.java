package taskhub.action.uibean.bo;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import taskhub.action.service.bo.BookService;
import taskhub.action.uibean.AbstractBean;
import taskhub.persistence.constant.BookCategory;
import taskhub.persistence.entity.bo.Book_new_hdr;
import taskhub.persistence.entity.bo.Book_ost_hdr;

@SuppressWarnings("serial")
@Named
@ConversationScoped
public class BookBean extends AbstractBean {
	private static final String MODULE_CODE = "BO";
	@Inject
	private BookService service;
	
	private Book_new_hdr current_book_new;
	private Book_ost_hdr current_book_ost;
	
	private List<Book_new_hdr> book_new_hdrs;
	private List<Book_ost_hdr> book_ost_hdrs;
	
	
	public void create() {
		final Book_new_hdr book_new_hdr = this.service.create(MODULE_CODE);
		this.current_book_new = book_new_hdr;
	}
	
	public void delete() {
		if (this.current_book_new != null) {
			this.service.delete(current_book_new);
			this.switchToSummary();
		}
	}
	
	public void submit() {
		if (this.current_book_new != null) {
			final boolean successfully = this.service.submit(this.current_book_new);
			if (successfully) {
				this.switchToSummary();
			}
		}
	}
	
	public void revise() {
		if (this.current_book_ost != null) {
			final Book_new_hdr book = this.service.revise(this.current_book_ost);
			if (book != null) {
				this.current_book_new = book;
				this.current_book_ost = null;
			}
		}
	}
	
	public void updateImage(final FileUploadEvent event) {
		if (this.current_book_new != null) {
			this.current_book_new.setBook_img(event.getFile().getContent());
		}
	}
	
	public List<SelectItem> getBookCategorySelectItem() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		selectItems.add(new SelectItem(null, "Please Select ..."));
		
		for (final BookCategory c :  BookCategory.values() ) {
			selectItems.add(new SelectItem(c, c.getLabel()));
		}
		return selectItems;
	}
	
	public void switchToSummary() {
		this.current_book_new = null;
		this.current_book_ost = null;
		
		this.book_new_hdrs = null;
		this.book_ost_hdrs = null;
		
	}
	
	public Book_new_hdr getCurrent_book_new() {
		return this.current_book_new;
	}

	public void setCurrent_book_new(Book_new_hdr current_book_new) {
		this.current_book_new = current_book_new;
	}
	
	public Book_ost_hdr getCurrent_book_ost() {
		return this.current_book_ost;
	}

	public void setCurrent_book_ost(Book_ost_hdr current_book_ost) {
		this.current_book_ost = current_book_ost;
	}

	public List<Book_new_hdr> getBook_new_hdrs() {
		if (this.book_new_hdrs == null) {
			this.book_new_hdrs = this.service.findAll_Book_new_hdr();
		}
		return this.book_new_hdrs;
	}
	public void setBook_new_hdrs(List<Book_new_hdr> book_new_hdrs) {
		this.book_new_hdrs = book_new_hdrs;
	}

	public List<Book_ost_hdr> getBook_ost_hdrs() {
		if (this.book_ost_hdrs == null) {
			this.book_ost_hdrs = this.service.findAll_Book_ost_hdr();
		}
		return this.book_ost_hdrs;
	}

	public void setBook_ost_hdrs(List<Book_ost_hdr> book_ost_hdrs) {
		this.book_ost_hdrs = book_ost_hdrs;
	}
	
}
