package io.github.hogwartsschoolofmagic.user.event.listener;

import io.github.hogwartsschoolofmagic.user.event.OnRegistrationCompleteEvent;
import io.github.hogwartsschoolofmagic.user.service.AuthService;
import io.github.hogwartsschoolofmagic.user.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * <p> Listener-class for working with sending notifications to registering users about the need to
 * verify it by clicking on the link in the message. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.4.3
 */
@RequiredArgsConstructor
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

  private final AuthService userService;
  private final MailService mailService;

  @Override
  public void onApplicationEvent(@NonNull final OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  /**
   * Method for sending a message with a verification link to the mail.
   *
   * @param event user data event.
   */
  private void confirmRegistration(final OnRegistrationCompleteEvent event) {
    var user = event.getUser();
    var token = userService.createVerificationTokenForUser(user)
        .getValue();

    mailService.sendVerifiedMessage(user.getEmail(), token);
  }
}