package org.launchcode.fancyrats.models.data;

import org.launchcode.fancyrats.models.Badge;
import org.launchcode.fancyrats.models.BadgeType;
import org.launchcode.fancyrats.models.Job;
import org.launchcode.fancyrats.models.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {

    @Query("select b from Badge b where b.badgeType = :badgeType and b.numberOfJobs <= :numberOfJobs")
    List<Badge> findBadgeByBadgeTypeAndNumberOfJobs(@Param("badgeType") BadgeType badgeType, @Param("numberOfJobs") int numberOfJobs);

//    @Query("select b from Badge b where b.badgeType = :badgeType and b.petType = :petType")
//    Badge findBadgeByBadgeTypeAndPetType(@Param("badgeType") int badgeType, @Param("petType") int petType);
//
//    @Query("select b from Badge b where b.badgeType = 'SPECIALTY' and b.petType = :petType")
//    Badge findSpecialtyBadgeByPetType(@Param("petType") int petType);
//
//    @Query("select b from Badge b where b.badgeType = :badgeType and b.numberOfJobs = :numberOfJobs and b.petType = :petType")
//    Badge findBadgeByBadgeTypeAndNumberOfJobsAndPetType(@Param("badgeType") int badgeType, @Param("numberOfJobs") int numberOfJobs, @Param("petType") int petType);
//
    @Query("select b from Badge b where b.badgeType = :badgeType and b.numberOfJobs <= :numberOfJobs and b.petType = :petType")
    List<Badge> findBadgesByBadgeTypeAndNumberOfJobsAndPetType(@Param("badgeType") BadgeType badgeType, @Param("numberOfJobs") int numberOfJobs, @Param("petType") PetType petType);


}
