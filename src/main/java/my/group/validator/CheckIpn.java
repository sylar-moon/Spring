package my.group.validator;





import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,METHOD,PARAMETER,ANNOTATION_TYPE,TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckIpnValidator.class)
@Documented
public @interface CheckIpn {
    String message() default "{org.hibernate.validator.reference guide.capter06,CheckCase."+"message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

