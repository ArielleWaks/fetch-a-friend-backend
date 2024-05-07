package org.launch_code.fetch_a_friend.models.data;

import org.launch_code.fetch_a_friend.models.Badge;
import org.launch_code.fetch_a_friend.models.BadgeType;
import org.launch_code.fetch_a_friend.models.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {

    @Query("select b from Badge b where b.badgeType = :badgeType and b.numberOfJobs <= :numberOfJobs")
    List<Badge> findBadgeByBadgeTypeAndNumberOfJobs(@Param("badgeType") BadgeType badgeType, @Param("numberOfJobs") int numberOfJobs);

    @Query("select b from Badge b where b.badgeType = :badgeType and b.numberOfJobs <= :numberOfJobs and b.petType = :petType")
    List<Badge> findBadgesByBadgeTypeAndNumberOfJobsAndPetType(@Param("badgeType") BadgeType badgeType, @Param("numberOfJobs") int numberOfJobs, @Param("petType") PetType petType);


}
