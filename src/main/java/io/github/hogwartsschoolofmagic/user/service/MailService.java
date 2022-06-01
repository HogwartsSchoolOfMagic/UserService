package io.github.hogwartsschoolofmagic.user.service;

import io.github.hogwartsschoolofmagic.user.exception.email.FailureSendEmailException;

/**
 * <p> Interface for working with sending letters to email. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.4.3
 */
public interface MailService {

  /**
   * Method for sending verification email.
   *
   * @param toEmail where we send.
   * @param token   verification token issued to the user to verify email.
   * @throws FailureSendEmailException unsuccessful email message sending.
   */
  void sendVerifiedMessage(String toEmail, String token) throws FailureSendEmailException;
}