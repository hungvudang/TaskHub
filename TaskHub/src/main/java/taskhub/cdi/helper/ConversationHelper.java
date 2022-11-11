package taskhub.cdi.helper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import taskhub.cdi.Instances;

@ApplicationScoped
@Named
public class ConversationHelper {
	
	@Inject
	private Conversation conversation;
	
	
	public void conversationInitialized(@Observes @Initialized(ConversationScoped.class) final ServletRequest payload) {
		final String getRequestURI = ((HttpServletRequest) payload).getRequestURI();
		if (!getRequestURI.contains(ResourceHandler.RESOURCE_IDENTIFIER)) {
			this.conversation.setTimeout(7200000L); // 2 hours
		}
	}

	public void conversationDestroyed(@Observes @Destroyed(ConversationScoped.class) final ServletRequest payload) {
		final String getRequestURI = ((HttpServletRequest) payload).getRequestURI();
		if (!getRequestURI.contains(ResourceHandler.RESOURCE_IDENTIFIER)) {
		}
	}
	
	public static void beginIfTransient() {
		final Conversation conversation = Instances.get(Conversation.class);
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public static void endIfNotTransient() {
		final Conversation conversation = Instances.get(Conversation.class);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public static boolean isTransient() {
		return Instances.get(Conversation.class).isTransient();
	}


	public String getCurrentConversationId() {
		if (this.conversation.getId() == null) {
			return "";
		}
		return this.conversation.getId();
	}
}
