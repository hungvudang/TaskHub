package taskhub.action.uibean;

import java.io.Serializable;

import javax.faces.validator.ValidatorException;
import javax.transaction.Transactional;

@SuppressWarnings("serial")
@Transactional(rollbackOn = { Exception.class }, dontRollbackOn = { ValidatorException.class })
public abstract class AbstractBean implements Serializable {
	
}
