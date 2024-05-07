package org.launch_code.fetch_a_friend.models.data;

import java.util.Optional;

import org.launch_code.fetch_a_friend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

//    @Query("select count (*) ")

}
