package io.github.hogwartsschoolofmagic.user.service;

import io.github.hogwartsschoolofmagic.user.exception.resource.ResourceAlreadyExistException;
import io.github.hogwartsschoolofmagic.user.exception.resource.ResourceNotFoundException;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.User;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.VerificationToken;
import io.github.hogwartsschoolofmagic.user.security.UserPrincipal;
import org.springframework.security.core.AuthenticationException;

/**
 * Interface for working with user authorization/authentication.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.15
 */
public interface AuthService {

  /**
   * Method for getting data of the current authenticated user.
   *
   * @param currentUser current logged in user.
   * @return {@link User} or null.
   */
  User getCurrentUser(UserPrincipal currentUser);

  /**
   * Method for authenticating the logged in user and creating an authentication token for him.
   *
   * @param email    user authentication data: email.
   * @param password user authentication data: password.
   * @throws ResourceNotFoundException no user found by email.
   */
  String creatingTokenForAuthUser(String email, String password)
      throws AuthenticationException, ResourceNotFoundException;

  /**
   * Method for registering a new user and storing it in the database.
   *
   * @param email    user registration data: email.
   * @param username user registration data: username.
   * @param password user registration data: password.
   * @return {@link User} or throwing an exception.
   * @throws ResourceAlreadyExistException a user with this email already exists.
   * @throws ResourceNotFoundException     a role with this name was not found.
   */
  User registerNewUserAccount(String email, String username, String password)
      throws ResourceAlreadyExistException, ResourceNotFoundException;

  /**
   * Method for obtaining a verification token from the database by its token value.
   *
   * @param verificationToken email verification token.
   * @return {@link VerificationToken} or throwing an exception.
   * @throws ResourceNotFoundException no verification token found by field value - token.
   */
  VerificationToken getVerificationToken(String verificationToken)
      throws ResourceNotFoundException;

  /**
   * Method for updating the verification token with a new token value.
   *
   * @param existingVerificationToken the existing value of the verification token.
   * @return {@link VerificationToken} by existing verification token or throwing an exception.
   * @throws ResourceNotFoundException no verification token found by field value - token.
   */
  VerificationToken generateNewVerificationToken(String existingVerificationToken)
      throws ResourceNotFoundException;

  /**
   * Method for creating and saving a token for email verification.
   *
   * @param user registering user.
   * @return verification token.
   */
  VerificationToken createVerificationTokenForUser(User user);

  /**
   * Method for saving user data to database.
   *
   * @param user user data.
   */
  void saveRegisteredUser(User user);
}