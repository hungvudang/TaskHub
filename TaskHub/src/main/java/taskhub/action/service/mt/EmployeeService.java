package taskhub.action.service.mt;

import java.util.List;

import javax.inject.Inject;

import taskhub.action.service.AbstractService;
import taskhub.action.service.helper.DnsHelper;
import taskhub.persistence.QueryHelper;
import taskhub.persistence.entity.mt.Mt_employee;
import taskhub.persistence.entity.mt.Mt_employee_;

@SuppressWarnings("serial")
public class EmployeeService extends AbstractService {

	
	
	@Inject
	private DnsHelper dnsHelper;
	
	public Mt_employee addNew_Mt_employee(final String numbering_scheme_key) {
		final String employeeId= this.dnsHelper.next(numbering_scheme_key);
		final Mt_employee e = new Mt_employee(employeeId);
		this.em.persist(e);
		this.em.flush();
		return e;
	}

	public void delete_Mt_employee(final Mt_employee e) {
		this.em.remove(e);
		this.em.flush();
	}


	public List<Mt_employee> findAll() {
		final QueryHelper<Mt_employee, Mt_employee> queryHelper = QueryHelper.create(Mt_employee.class);
		queryHelper.query.orderBy(queryHelper.cb.asc(
				queryHelper.root.get(Mt_employee_.pk).get(taskhub.persistence.entity.mt.Mt_employee_.Pk_.id)));
		return this.em.createQuery(queryHelper.query).getResultList();
	}
}
