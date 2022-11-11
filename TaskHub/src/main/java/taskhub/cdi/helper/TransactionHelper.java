package taskhub.cdi.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import taskhub.cdi.EmLocator;

public class TransactionHelper {
	private static final Logger LOG = Logger.getLogger(TransactionHelper.class.getName());
	
	private static TransactionManager transactionManager;
	private static final String JNDI_LOCATION = "java:/TransactionManager";
	
	
	public static TransactionManager getTxtManager() throws NamingException {
		if (transactionManager == null) {
			synchronized (TransactionHelper.class) {
				if (transactionManager == null) {
					transactionManager = (TransactionManager) new InitialContext().lookup(JNDI_LOCATION);
				}
			}
		}
		return transactionManager;
	}
	
	
	public static void begin() throws NotSupportedException, SystemException, NamingException {
		TransactionHelper.getTxtManager().begin();
		
		if (CDIHelper.isConversationContextActive()) {
			final EntityManager em = EmLocator.getEm();
			em.joinTransaction();
		}
	}
	
	
	public static void commit() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NamingException {
		TransactionHelper.getTxtManager().commit();
		LOG.log(Level.INFO, "Transaction committed");
	}
	
	public static void rollback() {
		try {
			TransactionHelper.getTxtManager().rollback();
			LOG.log(Level.INFO, "Transaction rolled back");
		} catch (IllegalStateException | SecurityException | SystemException | NamingException e) {
			LOG.log(Level.INFO, "Exception in calling rollback()");
		}
	}
	
	public static void cleanup() throws SystemException, NamingException {
		if (TransactionHelper.getTxtManager().getStatus() == Status.STATUS_COMMITTED || TransactionHelper.getTxtManager().getStatus() == Status.STATUS_ROLLEDBACK) {
			TransactionHelper.rollback();
			TransactionHelper.getTxtManager().suspend();
		}
	}
	
	
}
