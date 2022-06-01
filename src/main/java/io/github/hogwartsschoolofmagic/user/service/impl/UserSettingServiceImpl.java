package io.github.hogwartsschoolofmagic.user.service.impl;

import io.github.hogwartsschoolofmagic.user.persistence.dao.UserSettingRepository;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.User;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.UserSetting;
import io.github.hogwartsschoolofmagic.user.service.MessageService;
import io.github.hogwartsschoolofmagic.user.service.UserSettingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

/**
 * <p> Service class (implementation) for working with user setting entity. </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.5.8
 */
@RequiredArgsConstructor
@Slf4j
@SessionScope
@Transactional
@Service
public class UserSettingServiceImpl implements UserSettingService {

  private final UserSettingRepository userSettingRepo;
  private final MessageService messageService;

  @Override
  public List<UserSetting> getAll(User user) {
    return userSettingRepo.findAllByUser(user);
  }

  @Override
  public UserSetting create(UserSetting userSetting, User user) {
    userSetting.setUser(user);
    configuringUserLanguage(userSetting);
    return userSettingRepo.saveAndFlush(userSetting);
  }

  @Override
  public UserSetting update(UserSetting userSettingFromDb, UserSetting userSetting) {
    userSettingFromDb.setValue(userSetting.getValue());
    configuringUserLanguage(userSettingFromDb);
    return userSettingRepo.saveAndFlush(userSettingFromDb);
  }

  private void configuringUserLanguage(UserSetting userSetting) {
    if (userSetting.getName().equals("locale")) {
      messageService.setLocale(userSetting.getValue());
    }
  }
}