package taskhub.cdi.qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface After {
	@SuppressWarnings("serial")
	public static final AnnotationLiteral<After> LITERAL = new AnnotationLiteral<After>() {
	};
}
