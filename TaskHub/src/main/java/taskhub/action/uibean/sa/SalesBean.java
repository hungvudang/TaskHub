package taskhub.action.uibean.sa;

import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import taskhub.action.service.sa.SalesService;
import taskhub.action.uibean.AbstractBean;
import taskhub.persistence.entity.sa.Sa_order_new_det;
import taskhub.persistence.entity.sa.Sa_order_new_hdr;
import taskhub.persistence.entity.sa.Sa_order_ost_det;
import taskhub.persistence.entity.sa.Sa_order_ost_hdr;

@SuppressWarnings("serial")
@Named
@ConversationScoped
public class SalesBean extends AbstractBean {
	private static final String MODULE_CODE = "SA";

	@Inject
	private SalesService service;

	private Sa_order_new_hdr current_Sa_order_new_hdr;
	private Sa_order_new_det selected_Sa_order_new_det;
	
	private Sa_order_ost_hdr current_Sa_order_ost_hdr;
	private Sa_order_ost_det selected_Sa_order_ost_det;

	private List<Sa_order_new_hdr> sa_order_new_hdrs;
	
	private List<Sa_order_ost_hdr> sa_order_ost_hdrs;

	public void create() {
		final Sa_order_new_hdr newSalesOrder = this.service.create(MODULE_CODE);
		this.current_Sa_order_new_hdr = newSalesOrder;
		this.current_Sa_order_ost_hdr = null;
	}

	public void newSa_order_new_det() {
		this.service.newSa_order_new_det(this.current_Sa_order_new_hdr);
	}

	public void submit() {
		final boolean successfully = this.service.submit(this.current_Sa_order_new_hdr);
		if (successfully) {
			this.switchToSummary();
		}
	}

	public void delete() {
		final boolean successfully = this.service.delete(this.current_Sa_order_new_hdr);
		if (successfully) {
			this.switchToSummary();
		}
	}

	public void deleteSa_order_new_det() {
		this.service.deleteSa_order_new_det(this.selected_Sa_order_new_det);
	}

	public void revise() {

	}
	
	protected void switchToSummary() {
		this.current_Sa_order_new_hdr = null;
		this.current_Sa_order_ost_hdr = null;
		this.selected_Sa_order_new_det= null;
		this.selected_Sa_order_ost_det = null;
		this.sa_order_new_hdrs = null;
		this.sa_order_ost_hdrs = null;
	}

	public Sa_order_new_hdr getCurrent_Sa_order_new_hdr() {
		return this.current_Sa_order_new_hdr;
	}

	public void setCurrent_Sa_order_new_hdr(Sa_order_new_hdr current_Sa_order_new_hdr) {
		this.current_Sa_order_new_hdr = current_Sa_order_new_hdr;
	}

	public Sa_order_new_det getSelected_Sa_order_new_det() {
		return this.selected_Sa_order_new_det;
	}

	public void setSelected_Sa_order_new_det(Sa_order_new_det selected_Sa_order_new_det) {
		this.selected_Sa_order_new_det = selected_Sa_order_new_det;
	}

	public List<Sa_order_new_hdr> getSa_order_new_hdrs() {
		if (sa_order_new_hdrs == null) {
			sa_order_new_hdrs = this.service.findAll_Sa_order_new_hdr();
		}
		return this.sa_order_new_hdrs;
	}

	public void setSa_order_new_hdrs(List<Sa_order_new_hdr> sa_order_new_hdrs) {
		this.sa_order_new_hdrs = sa_order_new_hdrs;
	}

	public Sa_order_ost_hdr getCurrent_Sa_order_ost_hdr() {
		return this.current_Sa_order_ost_hdr;
	}

	public void setCurrent_Sa_order_ost_hdr(Sa_order_ost_hdr current_Sa_order_ost_hdr) {
		this.current_Sa_order_ost_hdr = current_Sa_order_ost_hdr;
	}

	public Sa_order_ost_det getSelected_Sa_order_ost_det() {
		return this.selected_Sa_order_ost_det;
	}

	public void setSelected_Sa_order_ost_det(Sa_order_ost_det selected_Sa_order_ost_det) {
		this.selected_Sa_order_ost_det = selected_Sa_order_ost_det;
	}

	public List<Sa_order_ost_hdr> getSa_order_ost_hdrs() {
		if (this.sa_order_ost_hdrs == null) {
			this.sa_order_ost_hdrs = this.service.findAll_Sa_order_ost_hdr();
		}
		return this.sa_order_ost_hdrs;
	}

	public void setSa_order_ost_hdrs(List<Sa_order_ost_hdr> sa_order_ost_hdrs) {
		this.sa_order_ost_hdrs = sa_order_ost_hdrs;
	}
	
	
	

}
