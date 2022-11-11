package taskhub.cdi;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;

import taskhub.cdi.helper.TransactionHelper;
import taskhub.cdi.qualifier.After;
import taskhub.cdi.qualifier.Before;

@SuppressWarnings("serial")
@ApplicationScoped
public class TransactionObserver implements Serializable{
	private static final Logger LOG = Logger.getLogger(TransactionObserver.class.getName());
	
	public void startTransaction(@Observes @Before final PhaseEvent event) throws SystemException, NotSupportedException, NamingException {
		final PhaseId phaseId = event.getPhaseId();
		
		if (phaseId == PhaseId.RESTORE_VIEW || phaseId == PhaseId.RENDER_RESPONSE) {
			if (TransactionHelper.getTxtManager().getStatus() == Status.STATUS_MARKED_ROLLBACK) {
				LOG.log(Level.INFO, "Rolling back transaction @Before phase " + phaseId);
				TransactionHelper.rollback();
			}

			if (TransactionHelper.getTxtManager().getStatus() == Status.STATUS_COMMITTED || TransactionHelper.getTxtManager().getStatus() == Status.STATUS_ROLLEDBACK) {
				LOG.log(Level.INFO, "Transaction was already rolled back or committed before this listener process @Before PhaseEvent for phase " + phaseId); 
				TransactionHelper.cleanup();
			}

			if (TransactionHelper.getTxtManager().getStatus() == Status.STATUS_NO_TRANSACTION) {
				LOG.log(Level.INFO, "Beginning transaction @Before phase " + phaseId);
				TransactionHelper.begin();
			}

		}
	}
	
	
	public void endTransaction(@Observes @After final PhaseEvent event) throws SecurityException, SystemException, NamingException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		final PhaseId phaseId = event.getPhaseId();
		
		final boolean endTx = (phaseId == PhaseId.INVOKE_APPLICATION) || phaseId == PhaseId.RENDER_RESPONSE || event.getFacesContext().getRenderResponse()
				|| event.getFacesContext().getResponseComplete();
		
		if (endTx) {
			if (TransactionHelper.getTxtManager().getStatus() == Status.STATUS_ACTIVE) {
				try {
					TransactionHelper.commit();
					LOG.log(Level.INFO, "Committing transaction after phase " + phaseId);
				} catch (Exception e) {
					TransactionHelper.rollback();
					throw new IllegalStateException("Transaction failed to commit. This may be because precommit failed, or it was timed out, or rolled back by a different thread.");
				}
			}
			else if (TransactionHelper.getTxtManager().getStatus() == Status.STATUS_MARKED_ROLLBACK) {
				TransactionHelper.rollback();
			}
			
			if (TransactionHelper.getTxtManager().getStatus() == Status.STATUS_COMMITTED || TransactionHelper.getTxtManager().getStatus() == Status.STATUS_ROLLEDBACK) {
				if (phaseId != PhaseId.RENDER_RESPONSE) {
					TransactionHelper.cleanup();
				}
			}
		}
	}
}
