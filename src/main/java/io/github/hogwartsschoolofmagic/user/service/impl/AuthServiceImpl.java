package io.github.hogwartsschoolofmagic.user.service.impl;

import io.github.hogwartsschoolofmagic.user.enumeration.AuthProvider;
import io.github.hogwartsschoolofmagic.user.enumeration.Roles;
import io.github.hogwartsschoolofmagic.user.exception.resource.ResourceAlreadyExistException;
import io.github.hogwartsschoolofmagic.user.exception.resource.ResourceNotFoundException;
import io.github.hogwartsschoolofmagic.user.persistence.dao.RoleRepository;
import io.github.hogwartsschoolofmagic.user.persistence.dao.UserRepository;
import io.github.hogwartsschoolofmagic.user.persistence.dao.VerificationTokenRepository;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.User;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.VerificationToken;
import io.github.hogwartsschoolofmagic.user.security.TokenProvider;
import io.github.hogwartsschoolofmagic.user.security.UserPrincipal;
import io.github.hogwartsschoolofmagic.user.service.AuthService;
import io.github.hogwartsschoolofmagic.user.service.MessageService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class (implementation) for working with user entity and its authorization/authentication.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.15
 */
@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final VerificationTokenRepository tokenRepository;
  private final MessageService messageService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final TokenProvider tokenProvider;

  @Override
  public User getCurrentUser(UserPrincipal currentUser) {
    if (Objects.isNull(currentUser)) {
      return null;
    }

    return currentUser.getUser();
  }

  @Override
  public String creatingTokenForAuthUser(String email, String password) {
    var authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email, password)
    );

    var user = userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
            "user.error.not.found.by.email",
            new Object[] {email}
        )));

    user.setLastVisit(LocalDateTime.now());
    userRepository.save(user);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return tokenProvider.createToken(authentication);
  }

  @Override
  public User registerNewUserAccount(String email, String username, String password) {
    if (userRepository.existsByEmail(email)) {
      throw new ResourceAlreadyExistException(messageService.getMessageWithArgs(
          "user.error.exist.email",
          new Object[] {email}
      ));
    }

    var user = new User();
    user.setFullname(username);
    user.setEmailVerified(false);
    user.setEmail(email);
    user.setAuthProvider(AuthProvider.LOCAL);
    user.setPassword(passwordEncoder.encode(password));
    var userRole = roleRepository.findByValue(Roles.ROLE_USER)
        .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
            "role.error.not.found.by.name", new Object[] {Roles.ROLE_USER.getName()}
        )));
    user.setRoles(Stream.of(userRole).collect(Collectors.toCollection(ArrayList::new)));
    return userRepository.save(user);
  }

  @Override
  public VerificationToken getVerificationToken(String verificationToken) {
    return tokenRepository.findByValue(verificationToken)
        .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
            "token.error.not.found.by.token", new Object[] {verificationToken}
        )));
  }

  @Override
  public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
    var token = getVerificationToken(existingVerificationToken);
    token.setValue(UUID.randomUUID().toString());
    token.setExpiryDate(LocalDateTime.now().plusDays(1));
    return tokenRepository.save(token);
  }

  @Override
  public VerificationToken createVerificationTokenForUser(User user) {
    var myToken = new VerificationToken();
    myToken.setValue(UUID.randomUUID().toString());
    myToken.setUser(user);
    myToken.setExpiryDate(LocalDateTime.now().plusDays(1));
    return tokenRepository.save(myToken);
  }

  @Override
  public void saveRegisteredUser(User user) {
    userRepository.save(user);
  }
}