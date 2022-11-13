package taskhub.action.service.sa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import taskhub.action.service.AbstractService;
import taskhub.action.service.helper.DnsHelper;
import taskhub.persistence.QueryHelper;
import taskhub.persistence.constant.ItemType;
import taskhub.persistence.constant.UomType;
import taskhub.persistence.entity.sa.Sa_order_new_det;
import taskhub.persistence.entity.sa.Sa_order_new_hdr;
import taskhub.persistence.entity.sa.Sa_order_new_hdr_;
import taskhub.persistence.entity.sa.Sa_order_ost_det;
import taskhub.persistence.entity.sa.Sa_order_ost_hdr;
import taskhub.persistence.entity.sa.Sa_order_ost_hdr_;
import taskhub.util.BeanCoppier;

@SuppressWarnings("serial")
@Named
public class SalesService extends AbstractService {

	@Inject
	private DnsHelper dnsHelper;

	public Sa_order_new_hdr create(final String moduleCode) {
		final String sales_order_no = dnsHelper.next(moduleCode);
		final Sa_order_new_hdr sa_order_new_hdr = new Sa_order_new_hdr(sales_order_no);
		this.em.persist(sa_order_new_hdr);
		sa_order_new_hdr.setOrder_date(new Date());
		this.em.flush();
		return sa_order_new_hdr;
	}
	
	public Sa_order_new_det newSa_order_new_det(final Sa_order_new_hdr sa_order_new_hdr) {
		final Sa_order_new_det sa_order_new_det = new Sa_order_new_det(sa_order_new_hdr, Sa_order_new_det.findNext_Pk_det_no(sa_order_new_hdr.getSales_order_no()));
		this.em.persist(sa_order_new_det);
		sa_order_new_det.setItem_type(ItemType.S);
		sa_order_new_det.setUom_code(UomType.PCS);
		this.em.flush();
		return sa_order_new_det;
	}

	public boolean submit(final Sa_order_new_hdr sa_order_new_hdr) {
		final boolean valid = this.validateBeforeSubmit(sa_order_new_hdr);
		boolean successfully = false;
		if (valid) {
			try {
				final String vch_no = sa_order_new_hdr.getSales_order_no();
				Sa_order_ost_hdr sa_order_ost_hdr = this.em.find(Sa_order_ost_hdr.class, new taskhub.persistence.entity.sa.Sa_order_ost_hdr.Pk(vch_no));
				if (sa_order_ost_hdr == null) {
					sa_order_ost_hdr = new Sa_order_ost_hdr(vch_no);
					this.em.persist(sa_order_ost_hdr);
				}
				sa_order_ost_hdr.setTotal_amt(0.0);
				
				BeanCoppier.copy(sa_order_new_hdr, sa_order_ost_hdr);
				for (final Sa_order_new_det sa_order_new_det : sa_order_new_hdr.getSa_order_new_dets()) {
					Sa_order_ost_det sa_order_ost_det = this.em.find(Sa_order_ost_det.class, new taskhub.persistence.entity.sa.Sa_order_ost_det.Pk(vch_no, sa_order_new_det.getPk_no_det()));
					if (sa_order_ost_det == null) {
						sa_order_ost_det = new Sa_order_ost_det(sa_order_ost_hdr, sa_order_new_det.getPk_no_det());
						this.em.persist(sa_order_ost_det);
					}
					BeanCoppier.copy(sa_order_new_det, sa_order_ost_det);
				}
				this.em.remove(sa_order_new_hdr);
				this.em.flush();
				sa_order_ost_hdr.setVariation(false);
				successfully = true;
			} catch (Throwable e) {
				throw e;
			}
		}

		return successfully;
	}
	
	protected boolean validateBeforeSubmit(final Sa_order_new_hdr sa_order_new_hdr) {
		boolean valid = true;
		final List<FacesMessage> errors = new ArrayList<FacesMessage>();
		if (StringUtils.isBlank(sa_order_new_hdr.getCustomer_name())) {
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR,//
					"The value is required",//
					"The customer name is required."));
		}
		
		if (sa_order_new_hdr.getOrder_date() == null) {
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR,//
					"The value is required",//
					"The order date is required."));
		}
		
		if (StringUtils.isBlank(sa_order_new_hdr.getCustomer_address())) {
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR,//
					"The value is required",//
					"The customer address is required."));
		}
		
		if (CollectionUtils.isEmpty(sa_order_new_hdr.getSa_order_new_dets())) {
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR,//
					"Unable to submit this voucher",//
					"Please add at least one Item Code to continue."));
		}
		
		if (!CollectionUtils.isEmpty(errors)) {
			for (final FacesMessage m : errors) {
				FacesContext.getCurrentInstance().addMessage(null, m);
			}
			valid = false;
		}
		
		return valid;
	}

	public boolean delete(final Sa_order_new_hdr sa_order_new_hdr) {
		this.em.remove(sa_order_new_hdr);
		final String vch_no = sa_order_new_hdr.getSales_order_no();
		Sa_order_ost_hdr sa_order_ost_hdr = this.em.find(Sa_order_ost_hdr.class, new taskhub.persistence.entity.sa.Sa_order_ost_hdr.Pk(vch_no));
		if (sa_order_ost_hdr != null) {
			sa_order_ost_hdr.setVariation(false);
		}
		this.em.flush();
		return true;
	}
	
	public boolean deleteSa_order_new_det(final Sa_order_new_det sa_order_new_det) {
		sa_order_new_det.setQty(0.0);
		sa_order_new_det.setGross_price(0.0);
		this.em.remove(sa_order_new_det);
		final Sa_order_new_hdr sa_order_new_hdr = sa_order_new_det.getSa_order_new_hdr();
		sa_order_new_hdr.getSa_order_new_dets().remove(sa_order_new_det);
		this.em.flush();
		return true;
	}
	
	public Sa_order_new_hdr revise(final Sa_order_ost_hdr sa_order_ost_hdr) {
		final Sa_order_new_hdr sa_order_new_hdr = new Sa_order_new_hdr(sa_order_ost_hdr.getSales_order_no());
		this.em.persist(sa_order_new_hdr);
		BeanCoppier.copy(sa_order_ost_hdr, sa_order_new_hdr);
		sa_order_new_hdr.setTotal_amt(0.0);
		for (final Sa_order_ost_det sa_order_ost_det : sa_order_ost_hdr.getSa_order_ost_dets()) {
			final Sa_order_new_det sa_order_new_det = new Sa_order_new_det(sa_order_new_hdr, sa_order_ost_det.getPk_no_det());
			this.em.persist(sa_order_new_det);
			BeanCoppier.copy(sa_order_ost_det, sa_order_new_det);
		}
		sa_order_ost_hdr.setVariation(true);
		this.em.flush();
		return sa_order_new_hdr;
	}

	public List<Sa_order_new_hdr> findAll_Sa_order_new_hdr() {
		final QueryHelper<Sa_order_new_hdr, Sa_order_new_hdr> queryHelper = QueryHelper.create(Sa_order_new_hdr.class);
		queryHelper.query.orderBy(
				queryHelper.cb.desc(queryHelper.root.get(Sa_order_new_hdr_.order_date)),
				queryHelper.cb.desc(queryHelper.root.get(Sa_order_new_hdr_.sales_order_no)));
		return this.em.createQuery(queryHelper.query).getResultList();
	}
	
	public List<Sa_order_ost_hdr> findAll_Sa_order_ost_hdr() {
		final QueryHelper<Sa_order_ost_hdr, Sa_order_ost_hdr> queryHelper = QueryHelper.create(Sa_order_ost_hdr.class);
		queryHelper.query.orderBy(
				queryHelper.cb.desc(queryHelper.root.get(Sa_order_ost_hdr_.order_date)),
				queryHelper.cb.desc(queryHelper.root.get(Sa_order_ost_hdr_.sales_order_no)));
		return this.em.createQuery(queryHelper.query).getResultList();
	}

}
