package taskhub.action.uibean;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.validator.ValidatorException;
import javax.transaction.Transactional;

@SuppressWarnings("serial")
@Transactional(rollbackOn = { Exception.class }, dontRollbackOn = { ValidatorException.class })
public abstract class AbstractBean implements Serializable {
	
	protected final Logger LOG = Logger.getLogger(this.getClass().getName());
	
}
