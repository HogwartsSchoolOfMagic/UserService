package io.github.hogwartsschoolofmagic.user.controller.controllers.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for an entity representing user preferences for the client interface.
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.6.3
 */
@Getter
@Setter
public class UserSettingDto {

  // User setting id.
  private Long id;

  // User setting key name.
  private String name;

  // User setting value.
  private String value;
}