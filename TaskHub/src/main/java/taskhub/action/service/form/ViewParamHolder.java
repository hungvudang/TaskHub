package taskhub.action.service.form;

import java.util.Map;
import java.util.WeakHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import taskhub.action.service.AbstractService;
import taskhub.persistence.entity.FormInfo;
@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class ViewParamHolder extends AbstractService {
	private static Map<String, Object> HOLDER = new WeakHashMap<String, Object>();
	
	@Inject
	private FormInfo formInfo;
	
	
	public Object getProperty(final String key)  {
		return ViewParamHolder.HOLDER.get(key);
	}
	
	public void setProperty(final String key, final Object value) {
		ViewParamHolder.HOLDER.put(key, value);
	}
	
	public FormInfo getFormInfo() {
		return this.formInfo;
	}

	public void setFormInfo(FormInfo formInfo) {
		this.formInfo = formInfo;
	}

	public void resetFormInfo() {
		this.formInfo = null;
	}
	
	public void resetInfo() {
		ViewParamHolder.HOLDER.clear();
	}
}
