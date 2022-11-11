package taskhub.cdi;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@ApplicationScoped
public class Resources implements Serializable {
	
	@Produces
	@ConversationScoped
	EntityManager producesEmMain() {
		return EmLocator.getEm();
	}


	@Produces
	@RequestScoped
	FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	
}
