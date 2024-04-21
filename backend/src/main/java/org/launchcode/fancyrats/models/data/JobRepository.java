package org.launchcode.fancyrats.models.data;

import jakarta.transaction.Transactional;
import org.launchcode.fancyrats.models.Job;
import org.launchcode.fancyrats.models.User;
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

    @Query("select j from Job j where j.endDate = :date")
    List<Job> findCompletedJobsByEndDate(@Param("date") LocalDate date);

    @Query("select j.sitter from Job j where j.jobStatus = 3 and j.endDate = :date")
    List<User> findSitterIdsByCompletedJobEndDate(@Param("date") LocalDate date);

    @Query("select j from Job j, User u where j.sitter = u and u.username = :sitterUsername")
    List<Job> findJobsBySitterUsername(@Param("sitterUsername") String sitterUsername);

    @Query("select count(*) from Job j where j.jobStatus = 3 and j.sitter.id = :sitterId")
    Integer countCompletedJobBySitterId(Long sitterId);

    @Query("select j from Job j, User u where j.sitter = u and j.jobStatus = 3 and u.username = :sitterUsername")
    List<Job> findCompletedJobsBySitterUsername(@Param("sitterUsername") String sitterUsername);

    @Query("select j from Job j, User u where j.user = u and u.username = :userUsername")
    List<Job> findJobsByUserUsername(@Param("userUsername") String userUsername);

    @Query("select j from Job j, User u where j.sitter = u and j.jobStatus = 3 and u.username = :sitterUsername and j.petType = :petType")
    List<Job> findJobsBySitterAndPetType(@Param("sitterUsername") String sitterUsername, @Param("petType") int petType);



}
