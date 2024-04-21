package org.launchcode.fancyrats.models;

import jakarta.transaction.Transactional;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.services.BadgeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JobScheduler {

    private final JobRepository jobRepository;

    private final BadgeService badgeService;

    public JobScheduler(JobRepository jobRepository, BadgeService badgeService) {
        this.jobRepository = jobRepository;
        this.badgeService = badgeService;
    }

    @Scheduled(fixedDelay = 6000000) //6000000=60 minutes
    public void updateExpiredAndCompletedJobs() {
        jobRepository.closeExpiredJobs(LocalDate.now());
        jobRepository.completedClaimedJobs(LocalDate.now());
    }


    @Scheduled(fixedDelay = 6000000)
    @Transactional
    public void assignBadges() {
        jobRepository
                .findSitterIdsByCompletedJobEndDate(LocalDate.now().minusDays(1))
                .forEach(badgeService::assignJobNumberBadgeToSitter);
    }




}
