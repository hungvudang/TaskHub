package taskhub.action.service.sa;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import taskhub.action.service.AbstractService;
import taskhub.action.service.helper.DnsHelper;
import taskhub.persistence.QueryHelper;
import taskhub.persistence.entity.sa.Sa_order_new_det;
import taskhub.persistence.entity.sa.Sa_order_new_hdr;
import taskhub.persistence.entity.sa.Sa_order_new_hdr_;
import taskhub.persistence.entity.sa.Sa_order_ost_hdr;
import taskhub.persistence.entity.sa.Sa_order_ost_hdr_;

@SuppressWarnings("serial")
@Named
public class SalesService extends AbstractService {

	@Inject
	private DnsHelper dnsHelper;

	public Sa_order_new_hdr create(final String moduleCode) {
		final String sales_order_no = dnsHelper.next(moduleCode);
		final Sa_order_new_hdr sa_order_new_hdr = new Sa_order_new_hdr(sales_order_no);
		this.em.persist(sa_order_new_hdr);
		this.em.flush();
		return sa_order_new_hdr;
	}
	
	public Sa_order_new_det newSa_order_new_det(final Sa_order_new_hdr sa_order_new_hdr) {
		final Sa_order_new_det sa_order_new_det = new Sa_order_new_det(sa_order_new_hdr, Sa_order_new_det.findNext_Pk_det_no(sa_order_new_hdr.getSa_order_no()));
		this.em.persist(sa_order_new_det);
		this.em.flush();
		return sa_order_new_det;
	}

	public boolean submit(final Sa_order_new_hdr sa_order_new_hdr) {
		final boolean valid = this.validateBeforeSubmit(sa_order_new_hdr);
		boolean successfully = false;
		if (valid) {
				
		}

		return successfully;
	}
	
	protected boolean validateBeforeSubmit(final Sa_order_new_hdr sa_order_new_hdr) {
		return true;
	}

	public boolean delete(final Sa_order_new_hdr sa_order_new_hdr) {
		boolean successfully = false;

		return successfully;
	}
	
	public boolean deleteSa_order_new_det(final Sa_order_new_det sa_order_new_det) {
		boolean successfully = false;

		return successfully;
	}
	
	public Sa_order_new_hdr revise() {
		
		return null;
	}

	public List<Sa_order_new_hdr> findAll_Sa_order_new_hdr() {
		final QueryHelper<Sa_order_new_hdr, Sa_order_new_hdr> queryHelper = QueryHelper.create(Sa_order_new_hdr.class);
		queryHelper.query.orderBy(queryHelper.cb.desc(queryHelper.root.get(Sa_order_new_hdr_.sales_order_no)));
		return this.em.createQuery(queryHelper.query).getResultList();
	}
	
	public List<Sa_order_ost_hdr> findAll_Sa_order_ost_hdr() {
		final QueryHelper<Sa_order_ost_hdr, Sa_order_ost_hdr> queryHelper = QueryHelper.create(Sa_order_ost_hdr.class);
		queryHelper.query.orderBy(queryHelper.cb.desc(queryHelper.root.get(Sa_order_ost_hdr_.sales_order_no)));
		return this.em.createQuery(queryHelper.query).getResultList();
	}

}
