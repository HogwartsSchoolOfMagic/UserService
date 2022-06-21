package io.github.hogwartsschoolofmagic.user.exception.resource;

/**
 * Exception class for handling 404 errors related to getting various resources. Whether it's a
 * user, a role, and so on.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.0
 */
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, String value) {
    super(String.format(message, value));
  }
}