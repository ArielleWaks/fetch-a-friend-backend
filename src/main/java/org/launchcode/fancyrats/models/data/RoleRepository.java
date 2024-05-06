package org.launchcode.fancyrats.models.data;

import java.util.Optional;

import org.launchcode.fancyrats.models.ERole;
import org.launchcode.fancyrats.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
