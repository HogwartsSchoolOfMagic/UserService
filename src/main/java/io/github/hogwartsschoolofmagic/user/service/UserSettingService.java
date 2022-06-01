package io.github.hogwartsschoolofmagic.user.service;

import io.github.hogwartsschoolofmagic.user.persistence.model.user.User;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.UserSetting;
import java.util.List;

/**
 * <p> Interface for working with user setting entity. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.5.8
 */
public interface UserSettingService {

  /**
   * Saving the new user application setting.
   *
   * @param user setting author.
   * @return settings for a specific user application.
   */
  List<UserSetting> getAll(User user);

  /**
   * Saving the new user application setting.
   *
   * @param userSetting setting data.
   * @param user        setting author.
   * @return saved setting for a specific user application.
   */
  UserSetting create(UserSetting userSetting, User user);

  /**
   * Saving the new user application setting.
   *
   * @param userSettingFromDb setting old data.
   * @param userSetting       setting new data.
   * @return updated setting for a specific user application.
   */
  UserSetting update(UserSetting userSettingFromDb, UserSetting userSetting);
}