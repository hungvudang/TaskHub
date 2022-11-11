package taskhub.cdi;


import java.lang.annotation.Annotation;

import javax.enterprise.inject.Vetoed;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import taskhub.cdi.helper.CDIHelper;
import taskhub.cdi.qualifier.After;
import taskhub.cdi.qualifier.Before;

@SuppressWarnings("serial")
@Vetoed
public class PhaseEventBridge implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		handler(After.LITERAL, event);
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		handler(Before.LITERAL, event);
	}
	
	private void handler(final Annotation whenQualifier, final PhaseEvent event) {
		CDIHelper.fireEvent(event, whenQualifier);
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

	
	
}
