package io.github.hogwartsschoolofmagic.user.config;

import io.github.hogwartsschoolofmagic.user.security.CustomUserDetailsService;
import io.github.hogwartsschoolofmagic.user.security.RestAuthenticationEntryPoint;
import io.github.hogwartsschoolofmagic.user.security.filter.TokenAuthenticationFilter;
import io.github.hogwartsschoolofmagic.user.security.oauth2.CustomOauth2UserService;
import io.github.hogwartsschoolofmagic.user.security.oauth2.HttpCookieOauth2AuthorizationRequestRepository;
import io.github.hogwartsschoolofmagic.user.security.oauth2.Oauth2AuthenticationFailureHandler;
import io.github.hogwartsschoolofmagic.user.security.oauth2.Oauth2AuthenticationSuccessHandler;
import io.github.hogwartsschoolofmagic.user.security.oauth2.Oauth2LogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration java class for setting up authorization and its security.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.1.0
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomUserDetailsService customUserDetailsService;
  private final CustomOauth2UserService customOauth2UserService;
  private final Oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;
  private final Oauth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler;
  private final Oauth2LogoutSuccessHandler oauth2LogoutSuccessHandler;

  /**
   * Method for configuring authentication to use a custom UserDetailService along with password
   * encryption.
   *
   * @param authenticationManagerBuilder authBuilder for customization.
   */
  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder
        .userDetailsService(customUserDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  /**
   * Method for configuring client-server communication, permissions and connecting custom
   * services.
   *
   * @param http configuration of client-to-server connection.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .exceptionHandling(e -> e.authenticationEntryPoint(new RestAuthenticationEntryPoint()))
        .authorizeRequests(a -> a
            .antMatchers("/auth/register", "/auth/registrationConfirm",
                "/auth/resendRegistrationToken", "/auth/login", "/login/**").permitAll()
            .antMatchers("/auth/user", "/user/**").hasRole("USER")
            .anyRequest().authenticated()
        )
        .logout(l -> l
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .logoutSuccessHandler(oauth2LogoutSuccessHandler)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            .permitAll()
        )
        .oauth2Login(o -> o
            .authorizationEndpoint()
            .baseUri("/login")
            .authorizationRequestRepository(cookieAuthorizationRequestRepository())
            .and()
            .redirectionEndpoint()
            .baseUri("/login/oauth2/code/*")
            .and()
            .userInfoEndpoint()
            .userService(customOauth2UserService)
            .and()
            .successHandler(oauth2AuthenticationSuccessHandler)
            .failureHandler(oauth2AuthenticationFailureHandler)
        )
        .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() {
    return new TokenAuthenticationFilter();
  }

  @Bean
  public HttpCookieOauth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
    return new HttpCookieOauth2AuthorizationRequestRepository();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}