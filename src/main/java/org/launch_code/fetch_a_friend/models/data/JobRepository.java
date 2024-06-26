package org.launch_code.fetch_a_friend.models.data;

import jakarta.transaction.Transactional;
import org.launch_code.fetch_a_friend.models.Job;
import org.launch_code.fetch_a_friend.models.PetType;

import org.launch_code.fetch_a_friend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Integer> {

    @Modifying
    @Query("update Job j set j.jobStatus = 2 where j.jobStatus = 0 and j.startDate < :date")
    void closeExpiredJobs(@Param("date") LocalDate date);

    @Modifying
    @Query("update Job j set j.jobStatus = 3 where j.jobStatus = 1 and j.endDate < :date")
    void completedClaimedJobs(@Param("date") LocalDate date);

    @Query("select j.sitter from Job j where j.jobStatus = 3 and j.endDate <= :date")
    List<User> findSitterIdsByCompletedJobEndDate(@Param("date") LocalDate date);

    @Query("select j from Job j, User u where j.sitter = u and u.username = :sitterUsername")
    List<Job> findJobsBySitterUsername(@Param("sitterUsername") String sitterUsername);

    @Query("select count(*) from Job j where j.jobStatus = org.launch_code.fetch_a_friend.models.JobStatus.STATUS_COMPLETED and j.sitter = :sitter")
    Integer countCompletedJobBySitter(@Param("sitter") User sitter);

    @Query("select count(*) from Job j where j.jobStatus = org.launch_code.fetch_a_friend.models.JobStatus.STATUS_COMPLETED and j.sitter = :sitter and j.petType = :petType")
    Integer countCompletedJobBySitterAndPetType(@Param("sitter") User sitter, @Param("petType") PetType petType);

    @Query("select j from Job j, User u where j.user = u and u.username = :userUsername")
    List<Job> findJobsByUserUsername(@Param("userUsername") String userUsername);

    @Query("select j from Job j, User u where j.sitter = u and j.jobStatus = 3 and u.username = :sitterUsername and j.petType = :petType")
    List<Job> findJobsBySitterAndPetType(@Param("sitterUsername") String sitterUsername, @Param("petType") int petType);

    @Query("select j from Job j join j.usersWhoBookmarked u where u.username = :username")
    List<Job> findBookmarkedJobsByUsername(@Param("username") String username);

}
