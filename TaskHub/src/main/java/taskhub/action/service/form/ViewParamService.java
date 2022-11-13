package taskhub.action.service.form;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import taskhub.action.service.AbstractService;
import taskhub.persistence.constant.FormCode;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class ViewParamService extends AbstractService {
	
	private FormCode activeFormCode;

	public FormCode getActiveFormCode() {
		return this.activeFormCode;
	}

	public void setActiveFormCode(FormCode activeFormCode) {
		this.activeFormCode = activeFormCode;
	}
	
	public void resetInfo() {
		this.activeFormCode =null;
	}
	
	public String locateSrcForm() {
		if (this.activeFormCode != null) {
			return "/" + StringUtils.join(Arrays.asList("faces", this.activeFormCode.getContext(), this.activeFormCode.getModule_code(), this.activeFormCode.getForm_code(), "_" +this.activeFormCode.name() +".xhtml"), "/");
		}
		return null;
	}

}
