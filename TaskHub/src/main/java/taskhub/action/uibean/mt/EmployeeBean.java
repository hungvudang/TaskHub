package taskhub.action.uibean.mt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.time.DateUtils;

import taskhub.action.service.mt.EmployeeService;
import taskhub.action.uibean.AbstractBean;
import taskhub.persistence.entity.mt.Mt_employee;

@Named
@SessionScoped
public class EmployeeBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4680376843003184096L;
	private static final String MODULE_CODE = "EMP";

	@Inject
	private EmployeeService service;

	private List<Mt_employee> employeeList;

	private Mt_employee selectedEmployee;

	private List<Mt_employee> selectedEmployeeList;

	public void newEmployee() {
		this.selectedEmployee = this.service.addNew_Mt_employee(MODULE_CODE);
		this.employeeList = null;
	}

	public void deleteEmployessList() {
		if (this.selectedEmployeeList != null) {
			for (final Mt_employee e : selectedEmployeeList) {
				this.service.delete_Mt_employee(e);
			}
		}
		this.selectedEmployeeList = null;
		this.selectedEmployee = null;
	}

	public void deleteEmployee() {
		if (this.selectedEmployee != null) {
			this.service.delete_Mt_employee(this.selectedEmployee);
		}
		this.selectedEmployee = null;
		this.selectedEmployeeList = null;
	}

	public void validateEmployeeName(final FacesContext context, final UIComponent component, final Object obj) {
		if (obj != null) {
			final Date dob = (Date) obj;
			final Mt_employee curEmployee = (Mt_employee) component.getAttributes().get("curObj");
			final boolean isDuplicate = this.getEmployeeList().stream()
					.filter(e -> !e.getId().equalsIgnoreCase(curEmployee.getId()))
					.filter(e -> !Objects.isNull(e.getDob()))
					.anyMatch(e -> DateUtils.isSameDay(dob, e.getDob()));
			if (isDuplicate) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Already existed Employee with DoB " + new SimpleDateFormat("dd/MM/yyyy").format(dob)));
			}
		}
	}

	public List<Mt_employee> getEmployeeList() {
		if (this.employeeList == null) {
			this.employeeList = this.service.findAll();
		}
		return employeeList;
	}

	public void setEmployeeList(List<Mt_employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Mt_employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Mt_employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public List<Mt_employee> getSelectedEmployeeList() {
		return selectedEmployeeList;
	}

	public void setSelectedEmployeeList(List<Mt_employee> selectedEmployeeList) {
		this.selectedEmployeeList = selectedEmployeeList;
	}

}
