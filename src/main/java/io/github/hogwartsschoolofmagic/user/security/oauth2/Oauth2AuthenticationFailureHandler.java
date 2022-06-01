package io.github.hogwartsschoolofmagic.user.security.oauth2;

import io.github.hogwartsschoolofmagic.user.util.CookieUtils;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * <p> Class used by Spring Security when user authentication fails. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Component
public class Oauth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  private final HttpCookieOauth2AuthorizationRequestRepository
      httpCookieOauth2AuthorizationRequestRepository;

  /**
   * Method called when user authentication fails and redirects to client.
   *
   * @param request  request.
   * @param response response.
   * @throws IOException server redirect error.
   */
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException {
    var targetUrl = CookieUtils.getCookie(request,
            HttpCookieOauth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
        .map(Cookie::getValue)
        .orElse(("/"));
    targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
        .queryParam("error", exception.getLocalizedMessage())
        .build().toUriString();
    httpCookieOauth2AuthorizationRequestRepository
        .removeAuthorizationRequestCookies(request, response);
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}