package taskhub.action.service;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SuppressWarnings("serial")
@ApplicationScoped
@Transactional (rollbackOn = {Exception.class}, dontRollbackOn = {ValidatorException.class})
public abstract class AbstractService implements Serializable{
	
	@Inject
	protected EntityManager em;
}
