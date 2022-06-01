package io.github.hogwartsschoolofmagic.user.config;

import static java.lang.Boolean.TRUE;
import static java.nio.charset.StandardCharsets.UTF_8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Configuration java class for declaring and configuring a bean for localization of regular and
 * validation messages.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.4.4
 */
@Configuration
public class MessagesConfig {

  /**
   * <p> Getting and configuring a ResourceBundle to localize messages by key. </p>
   *
   * @return configuration object with settings for receiving messages.
   */
  @Bean
  public ResourceBundleMessageSource messages() {
    var source = new ResourceBundleMessageSource();
    source.setBasenames("messages/labels");
    source.setDefaultEncoding(UTF_8.name());
    source.setUseCodeAsDefaultMessage(TRUE);

    return source;
  }
}