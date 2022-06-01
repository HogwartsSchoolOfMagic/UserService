package io.github.hogwartsschoolofmagic.user.exception.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for handling 400 error.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

  public BadRequestException(String message) {
    super(message);
  }
}