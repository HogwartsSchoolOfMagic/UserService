package io.github.hogwartsschoolofmagic.user.config;

import io.github.hogwartsschoolofmagic.user.security.CustomPermissionEvaluator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Configuration java class for using a custom class that defines methods that can be called from
 * PreAuthorize or PostAuthorize annotations.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.6
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

  /**
   * Method for creating and configuring a handler, which is called when using validation
   * expressions.
   *
   * @return customized handler with custom service class inside: @PreAuthorize or @PostAuthorize.
   */
  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    var expressionHandler = new DefaultMethodSecurityExpressionHandler();
    expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
    return expressionHandler;
  }
}