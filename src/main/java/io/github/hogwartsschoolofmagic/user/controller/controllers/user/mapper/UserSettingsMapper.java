package io.github.hogwartsschoolofmagic.user.controller.controllers.user.mapper;

import io.github.hogwartsschoolofmagic.user.controller.controllers.user.dto.UserSettingDto;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.UserSetting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for an entity representing user preferences for the client interface. To generate DTOs
 * from an entity and back.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.6.3
 */
@Mapper
public interface UserSettingsMapper {

  /**
   * Getting an entity from dto.
   *
   * @param dto dto user setting.
   * @return user setting.
   */
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "updated", ignore = true)
  UserSetting toEntity(UserSettingDto dto);
}