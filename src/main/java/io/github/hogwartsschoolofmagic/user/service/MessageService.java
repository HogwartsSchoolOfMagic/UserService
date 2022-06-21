package io.github.hogwartsschoolofmagic.user.service;


/**
 * <p> Interface for working with localization of messages on the server. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.4.4
 */
public interface MessageService {

  /**
   * Setting the user's language.
   *
   * @param language current user's language.
   */
  void setLocale(String language);

  /**
   * Getting a localized message.
   *
   * @param path message key.
   * @return localized message.
   */
  String getMessage(String path);

  /**
   * Getting localized message with parameters.
   *
   * @param path message key.
   * @param args message parameters.
   * @return localized message.
   */
  String getMessageWithArgs(String path, Object[] args);
}