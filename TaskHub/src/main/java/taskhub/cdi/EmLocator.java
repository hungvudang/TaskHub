package taskhub.cdi;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SuppressWarnings("serial")
public class EmLocator implements Serializable {
	private static final String DEFAULT_JNDI = "MAIN";
	private static EntityManagerFactory emf = null;
	
	private static EntityManager em = null;
	
	private static EntityManagerFactory getEmf() {
		if (emf == null) {
			synchronized (EmLocator.class) {
				if (emf == null) {
					emf =  Persistence.createEntityManagerFactory(DEFAULT_JNDI);
				}
			}
		}
		return emf;
	}
	
	public static EntityManager getEm() {
		if (em == null) {
			synchronized (EmLocator.class) {
				if (em == null) {
					final EntityManagerFactory emf = EmLocator.getEmf();
					em = emf.createEntityManager();
				}
			}
		}
		return em;
	}
	
	
}
