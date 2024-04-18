package org.launchcode.fancyrats.models;

import jakarta.transaction.Transactional;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JobScheduler {

    private final JobRepository jobRepository;

        public JobScheduler(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Scheduled(fixedDelay = 6000000) //6000000=60 minutes
    @Transactional
    public void updateExpiredAndCompletedJobs() {
        jobRepository.closeExpiredJobs(LocalDate.now());
        jobRepository.completedClaimedJobs(LocalDate.now());
    }


}
