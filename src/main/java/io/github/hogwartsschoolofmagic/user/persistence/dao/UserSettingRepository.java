package io.github.hogwartsschoolofmagic.user.persistence.dao;

import io.github.hogwartsschoolofmagic.user.persistence.model.user.User;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.UserSetting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p> Repository java class for communicating with the database and working with the user setting
 * entity. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.5.8
 */
@Repository
public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
  /**
   * Getting a complete list of user settings.
   *
   * @param user linked user.
   * @return optional with found user settings.
   */
  List<UserSetting> findAllByUser(User user);
}