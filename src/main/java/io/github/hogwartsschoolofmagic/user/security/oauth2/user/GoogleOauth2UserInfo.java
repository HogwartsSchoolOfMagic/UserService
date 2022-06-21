package io.github.hogwartsschoolofmagic.user.security.oauth2.user;

import io.github.hogwartsschoolofmagic.user.enumeration.AuthProvider;
import java.util.Map;

/**
 * <p> Class for user data obtained through Google authorization provider. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.0
 */
public class GoogleOauth2UserInfo extends AbstractOauth2UserInfo {

  public GoogleOauth2UserInfo(AuthProvider providerId, Map<String, Object> attributes) {
    super(providerId, attributes);
  }

  @Override
  public String getId() {
    return (String) attributes.get("sub");
  }

  @Override
  public String getName() {
    return (String) attributes.get("name");
  }

  @Override
  public String getEmail() {
    return (String) attributes.get("email");
  }

  @Override
  public String getImageUrl() {
    return (String) attributes.get("picture");
  }
}