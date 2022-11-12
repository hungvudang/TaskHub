package taskhub.action.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import taskhub.persistence.QueryHelper;
import taskhub.persistence.entity.Book_new_hdr;
import taskhub.persistence.entity.Book_new_hdr_;
import taskhub.persistence.entity.Book_ost_hdr;
import taskhub.persistence.entity.Book_ost_hdr_;
import taskhub.util.BeanCopper;

@SuppressWarnings("serial")
@Named
public class BookService extends AbstractService {

	@Inject
	private DnsHelper dnsHelper;

	public Book_new_hdr create(final String moduleCode) {
		final String bookCode = dnsHelper.next(moduleCode);
		final Book_new_hdr book_new_hdr = new Book_new_hdr(bookCode);
		this.em.persist(book_new_hdr);
		this.em.flush();
		return book_new_hdr;
	}

	public void delete(final Book_new_hdr book_new_hdr) {
		this.em.remove(book_new_hdr);
		
		final Book_ost_hdr book_ost_hdr = this.em.find(Book_ost_hdr.class, book_new_hdr.getBook_code());
		if (book_ost_hdr != null) {
			book_ost_hdr.setVariation(false);
		}
		this.em.flush();
	}
	
	public boolean submit(final Book_new_hdr book_new_hdr) {
		final boolean valid = this.validateBeforeSubmit(book_new_hdr);
		boolean successfully = false;
		if (valid) {
			try {
				Book_ost_hdr book_ost_hdr = this.em.find(Book_ost_hdr.class, book_new_hdr.getBook_code());
				// Variation case
				if (book_ost_hdr == null) {
					book_ost_hdr = new Book_ost_hdr(book_new_hdr.getBook_code());
					this.em.persist(book_ost_hdr);
				}
				
				BeanCopper.copy(book_new_hdr, book_ost_hdr);
				this.em.remove(book_new_hdr);
				book_ost_hdr.setVariation(false);
				this.em.flush();
				successfully = true;
				
			} catch (Throwable e) {
			}
		}
		return successfully;
	}
	
	public Book_new_hdr revise(final Book_ost_hdr book_ost_hdr) {
		try {
			final Book_new_hdr book_new_hdr = new Book_new_hdr(book_ost_hdr.getBook_code());
			this.em.persist(book_new_hdr);
			BeanCopper.copy(book_ost_hdr, book_new_hdr);
			book_ost_hdr.setVariation(true);
			this.em.flush();
			return book_new_hdr;
			
		} catch (Throwable e) {
			return null;
		}
	}
	
	public List<Book_new_hdr> findAll_Book_new_hdr() {
		final QueryHelper<Book_new_hdr, Book_new_hdr> queryHelper = QueryHelper.create(Book_new_hdr.class);
		queryHelper.query.orderBy(queryHelper.cb.desc(queryHelper.root.get(Book_new_hdr_.book_code)));
		return this.em.createQuery(queryHelper.query).getResultList();
	}
	
	public List<Book_ost_hdr> findAll_Book_ost_hdr() {
		final QueryHelper<Book_ost_hdr, Book_ost_hdr> queryHelper = QueryHelper.create(Book_ost_hdr.class);
		queryHelper.query.orderBy(queryHelper.cb.desc(queryHelper.root.get(Book_ost_hdr_.book_code)));
		return this.em.createQuery(queryHelper.query).getResultList();
	}
	
	private boolean validateBeforeSubmit(final Book_new_hdr book_new_hdr) {
		boolean valid = true;
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final List<FacesMessage> errors = new ArrayList<FacesMessage>();
		if (StringUtils.isBlank(book_new_hdr.getTitle())) {
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR,//
					"The value is required",//
					"The title is required."));
		}
		if (StringUtils.isBlank(book_new_hdr.getAuthor())) {
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR,//
					"The value is required",//
					"The author is required."));
		}
		if (book_new_hdr.getDate_release() == null) {
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR,//
					"The value is required",//
					"The release date is required."));
		}
		
		if (!CollectionUtils.isEmpty(errors)) {
			valid = false;
			for (final FacesMessage m : errors) {
				facesContext.addMessage(null, m);
			}
		}
			
		return valid;
	}
}
