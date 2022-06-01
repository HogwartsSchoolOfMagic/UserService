package io.github.hogwartsschoolofmagic.user.persistence.dao;

import io.github.hogwartsschoolofmagic.user.enumeration.Roles;
import io.github.hogwartsschoolofmagic.user.persistence.model.user.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p> Repository java class for communicating with the database and working with the role entity
 * . </p>
 *
 * @author Vladislav [SmithyVL] Kuznetsov.
 * @since 0.3.3
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  /**
   * Method for finding a role by its name in the database.
   *
   * @param value role value.
   * @return optional with found role.
   */
  Optional<Role> findByValue(Roles value);
}