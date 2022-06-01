package io.github.hogwartsschoolofmagic.user.controller.controllers.auth.dto;

import io.github.hogwartsschoolofmagic.user.validation.annotation.PasswordMatches;
import io.github.hogwartsschoolofmagic.user.validation.annotation.ValidEmail;
import io.github.hogwartsschoolofmagic.user.validation.annotation.ValidPassword;
import io.github.hogwartsschoolofmagic.user.validation.annotation.single.Match;
import javax.validation.constraints.NotNull;

/**
 * <p> Payload java record for parsing registration request data into rest method parameters. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov..
 * @since 0.3.0.
 */
@PasswordMatches()
public record RegisterDto(
    // Private field containing the username when registering from a client.
    @NotNull(message = "errors.invalid.empty.username")
    String name,

    // Private field containing the user's email when registering from a client.
    @NotNull(message = "errors.invalid.empty.email")
    @ValidEmail(message = "errors.invalid.email")
    String email,

    // Private field containing the user's password when registering from a client.
    @ValidPassword
    String password,

    // Private field containing re-entering the user's password when registering from a client.
    @Match(message = "errors.invalid.matchingPassword")
    String matchingPassword) {
}