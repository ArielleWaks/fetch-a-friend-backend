package org.launch_code.fetch_a_friend.models.data;

import java.util.Optional;

import org.launch_code.fetch_a_friend.models.ERole;
import org.launch_code.fetch_a_friend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
