package io.github.hogwartsschoolofmagic.user.validation.impl;

import io.github.hogwartsschoolofmagic.user.validation.annotation.ValidPassword;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.AlphabeticalCharacterRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.LowercaseCharacterRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Validating class for annotation - validation of the entered password by the user during
 * registration. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.4.5
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

  private static final Logger logger = LoggerFactory.getLogger(PasswordConstraintValidator.class);

  @Override
  public void initialize(ValidPassword arg0) {
    // Do nothing
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    var validator = new PasswordValidator(getMessageResolver(), Arrays.asList(
        new LengthRule(8, 16),
        new UppercaseCharacterRule(1),
        new LowercaseCharacterRule(1),
        new DigitCharacterRule(1),
        new SpecialCharacterRule(1),
        new AlphabeticalCharacterRule(1),
        new WhitespaceRule())
    );

    var result = validator.validate(new PasswordData(password));
    if (result.isValid()) {
      return true;
    }

    context.disableDefaultConstraintViolation();
    validator.getMessages(result)
        .forEach(m -> context.buildConstraintViolationWithTemplate(m).addConstraintViolation());
    return false;
  }

  /**
   * Receiving custom messages for password validation.
   *
   * @return list of messages to be used for validation error messages.
   */
  private MessageResolver getMessageResolver() {
    var props = new Properties();
    var inputStream = getClass().getClassLoader()
        .getResourceAsStream("user-service/src/main/resources/password.properties");

    try {
      props.load(inputStream);
    } catch (IOException e) {
      logger.error("Error load password properties file", e);
    }

    return new PropertiesMessageResolver(props);
  }
}