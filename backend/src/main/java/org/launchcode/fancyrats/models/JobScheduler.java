package org.launchcode.fancyrats.models;

import jakarta.transaction.Transactional;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.models.data.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class JobScheduler {

    private final JobRepository jobRepository;

        public JobScheduler(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void updateExpiredAndCompletedJobs() {
        jobRepository.closeExpiredJobs(LocalDate.now());
        jobRepository.completedClaimedJobs(LocalDate.now());
    }


}
