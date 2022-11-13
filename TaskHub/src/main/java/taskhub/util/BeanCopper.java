package taskhub.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import taskhub.persistence.entity.Abstract_entity;

public class BeanCopper {
	private static final Logger LOG = Logger.getLogger(BeanCopper.class.getName());

	public static <O extends Abstract_entity, D extends Abstract_entity> void copy(final O orig, final D dest) {
		if (orig != null && dest != null) {
			LOG.log(Level.INFO,
					"@Begin BeanCopper  FROM " + orig.getClass().getName() + " TO " + dest.getClass().getName());
			final Class<? extends Abstract_entity> origClass = orig.getClass();
			final Class<? extends Abstract_entity> destClass = dest.getClass();

			final Field[] origfields = origClass.getDeclaredFields();

			for (final Field origField : origfields) {
				
				if (origField.getDeclaredAnnotation(Column.class) != null //
						&& !CollectionUtils.containsAny(//
								Arrays.asList(Id.class, EmbeddedId.class, Version.class, OneToMany.class, NoCopy.class), //
								Arrays.asList(origField.getDeclaredAnnotations()))) {
					try {
						
						final Method getterOrig = origClass
								.getDeclaredMethod("get" + StringUtils.capitalize(origField.getName()));
						
						
						final Method setterDest = destClass.getDeclaredMethod(
								"set" + StringUtils.capitalize(origField.getName()), getterOrig.getReturnType());
						
						setterDest.invoke(dest, getterOrig.invoke(orig));

						LOG.log(Level.INFO, "Copied " + origField.getName());

					} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException
							| SecurityException | InvocationTargetException e) {
					}
				}
			}
			LOG.log(Level.INFO,
					"@After BeanCopper  FROM " + orig.getClass().getName() + " TO " + dest.getClass().getName());
		}
	}

}
