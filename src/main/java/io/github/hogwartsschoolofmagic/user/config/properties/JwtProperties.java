package io.github.hogwartsschoolofmagic.user.config.properties;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p> Java class containing settings for generating JWT tokens. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.5.5
 */
@Getter
@ConfigurationProperties("app")
public class JwtProperties {

  /**
   * Private field that stores information required to generate Json Web tokens.
   */
  private final Auth auth = new Auth();

  /**
   * Private field that stores information about redirect links after oauth2 authorization.
   */
  private final Oauth2 oauth2 = new Oauth2();

  /**
   * Data for generating JWT tokens.
   */
  @Getter
  @Setter
  public static class Auth {

    /**
     * Private field, which stores information about the secret of the token for its
     * generation.
     */
    private String tokenSecret;

    /**
     * Private field that stores the duration of the token.
     */
    private long tokenExpirationMsec;
  }

  /**
   * Data for authorization oAuth2.
   */
  @Getter
  @Setter
  public static final class Oauth2 {

    /**
     * Private field that stores a list of redirect links used after oauth2 authorization.
     */
    private List<String> authorizedRedirectUris;
  }
}