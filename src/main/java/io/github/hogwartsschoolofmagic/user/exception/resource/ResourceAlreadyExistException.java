package io.github.hogwartsschoolofmagic.user.exception.resource;

/**
 * <p> An exception class to handle attempts to create an existing resource. Whether it's user,
 * role, and so on. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.15
 */
public final class ResourceAlreadyExistException extends RuntimeException {

  public ResourceAlreadyExistException(final String message) {
    super(message);
  }
}