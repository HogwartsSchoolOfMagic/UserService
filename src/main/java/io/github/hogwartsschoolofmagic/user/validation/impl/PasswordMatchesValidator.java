package io.github.hogwartsschoolofmagic.user.validation.impl;

import io.github.hogwartsschoolofmagic.user.controller.controllers.auth.dto.RegisterDto;
import io.github.hogwartsschoolofmagic.user.validation.annotation.PasswordMatches;
import io.github.hogwartsschoolofmagic.user.validation.annotation.single.Match;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p> Validating class for annotation - validation of entered passwords during user registration
 * . </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.15
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
    // Do nothing
  }

  /**
   * Method-validator of passwords entered by the user for a match.
   *
   * @param obj     registration object for validation.
   * @param context validator context.
   * @return true if the passwords match, otherwise false.
   */
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context) {
    var registerRequest = (RegisterDto) obj;
    var result = registerRequest.password()
        .equals(registerRequest.matchingPassword());
    if (!result) {
      for (var field : obj.getClass().getDeclaredFields()) {
        if (field.isAnnotationPresent(Match.class)) {
          context.disableDefaultConstraintViolation();
          context.buildConstraintViolationWithTemplate(
              field.getAnnotation(Match.class).message()
          ).addPropertyNode(field.getName()).addConstraintViolation();
          break;
        }
      }
    }

    return result;
  }
}