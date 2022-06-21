package io.github.hogwartsschoolofmagic.user.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * <p> Meta annotation that can be used to insert the currently authenticated user principal into
 * controllers. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.0
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}