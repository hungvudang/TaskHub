package taskhub.persistence.entity;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import taskhub.persistence.constant.FormCode;

public class FormInfo {
	private FormCode formCode;

	protected FormInfo() {

	}

	public FormInfo(FormCode formCode) {
		this.formCode = formCode;
	}

	public FormCode getFormCode() {
		return this.formCode;
	}

	public String getLocatePathForm() {
		if (this.formCode != null) {
			return "/" + StringUtils.join(Arrays.asList("faces", //
					formCode.getContext(), //
					formCode.getModule_code(), //
					formCode.getForm_code(), //
					"_" + formCode.name() + ".xhtml"), //
					"/");
		}
		return "HOME";
	}

	public void setFormCode(FormCode formCode) {
		this.formCode = formCode;
	}

}
