package io.github.hogwartsschoolofmagic.user;

import static java.lang.System.nanoTime;
import static org.springframework.boot.SpringApplication.run;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p> A class to run the entire spring application. To run, just use the command - mvn spring-boot:
 * run. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.0.0
 */
@Slf4j
@SpringBootApplication
@ComponentScan({"io.github.hogwartsschoolofmagic"})
@EntityScan("io.github.hogwartsschoolofmagic")
@EnableJpaRepositories("io.github.hogwartsschoolofmagic")
@ConfigurationPropertiesScan("io.github.hogwartsschoolofmagic.user.config.properties")
public class UserApp {
  /**
   * The Main method that launches the Spring context of the application - and outputs the duration
   * of the launch to the log.
   *
   * @param args launch arguments.
   */
  public static void main(String[] args) {
    var startTime = nanoTime();
    run(UserApp.class, args);
    log.info("UserApplication started for {} ms!", nanoTime() - startTime);
  }
}