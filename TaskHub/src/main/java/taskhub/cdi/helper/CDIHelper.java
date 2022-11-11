package taskhub.cdi.helper;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CDIHelper {
	private static BeanManager beanManager;

	public void initBeanManager(@Observes final AfterBeanDiscovery afterBeanDiscovery, final BeanManager beanManager) {
		CDIHelper.beanManager = beanManager;
	}

	public static BeanManager getBeanManager() {
		if (CDIHelper.beanManager == null) {
			synchronized (CDIHelper.class) {
				if (CDIHelper.beanManager == null) {
					try {
						CDIHelper.beanManager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
					} catch (final NamingException e) {
					}
				}
			}
		}

		return CDIHelper.beanManager;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getContextualReference(final Class<T> type, final Annotation... qualifiers) {
		final Set<Bean<?>> beans = CDIHelper.getBeanManager().getBeans(type, qualifiers);

		if (beans == null || beans.isEmpty()) {
			throw new IllegalStateException(
					"Could not find beans for Type=" + type + " and qualifiers:" + Arrays.toString(qualifiers));
		}

		final Bean<?> bean = CDIHelper.getBeanManager().resolve(beans);
		final CreationalContext<?> creationalContext = CDIHelper.getBeanManager().createCreationalContext(bean);

		return (T) CDIHelper.getBeanManager().getReference(bean, type, creationalContext);
	}

	public static void fireEvent(final Object event, final Annotation... qualifiers) {
		CDIHelper.getBeanManager().fireEvent(event, qualifiers);
	}

	public static boolean isContextActive(final Class<? extends Annotation> scopeClass) {
		try {
			return CDIHelper.getBeanManager().getContext(scopeClass).isActive();
		}

		catch (final ContextNotActiveException e) {
			return false;
		}
	}

	public static boolean isConversationContextActive() {
		return CDIHelper.isContextActive(ConversationScoped.class);
	}
}
