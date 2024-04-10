package org.launchcode.fancyrats.models.data;

import org.launchcode.fancyrats.models.Job;
import org.launchcode.fancyrats.models.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    @Modifying
    @Query("update Job j set j.jobStatus = 2 where j.jobStatus = 0 and j.startDate < :date")
    void closeExpiredJobs(@Param("date") LocalDate date);

    @Modifying
    @Query("update Job j set j.jobStatus = 3 where j.jobStatus = 1 and j.endDate < :date")
    void completedClaimedJobs(@Param("date") LocalDate date);

}
