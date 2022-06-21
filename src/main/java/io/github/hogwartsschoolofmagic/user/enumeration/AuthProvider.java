package io.github.hogwartsschoolofmagic.user.enumeration;

/**
 * <p> Enumeration class for user authorization providers. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.13
 */
public enum AuthProvider {
  /**
   * Local provider type, which is set during standard registration.
   */
  LOCAL,

  /**
   * The type of external provider that is set during oauth2 authorization via Google.
   */
  GOOGLE
}