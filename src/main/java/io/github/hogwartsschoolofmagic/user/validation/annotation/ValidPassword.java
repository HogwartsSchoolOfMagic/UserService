package io.github.hogwartsschoolofmagic.user.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.github.hogwartsschoolofmagic.user.validation.impl.PasswordConstraintValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * <p> Meta-annotation with implementation, which can be used to validate the password when
 * registering to match the filling rules. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.4.5
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidPassword {

  /**
   * Configurable error localization message with default value.
   *
   * @return localization message or default value.
   */
  String message() default "Invalid Password";

  /**
   * Allows you to determine under what circumstances this check will be triggered.
   *
   * @return array groups or default value.
   */
  Class<?>[] groups() default {};

  /**
   * Allows you to define the payload that will be transmitted with verification.
   *
   * @return array payloads or default value.
   */
  Class<? extends Payload>[] payload() default {};
}