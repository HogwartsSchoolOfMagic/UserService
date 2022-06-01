package io.github.hogwartsschoolofmagic.user.exception.email;

/**
 * <p> An exception class for handling unsuccessful email messages. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.5.7
 */
public final class FailureSendEmailException extends RuntimeException {

  public FailureSendEmailException(final String message) {
    super(message);
  }
}