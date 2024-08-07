package org.launch_code.fetch_a_friend.models;

import jakarta.transaction.Transactional;
import org.launch_code.fetch_a_friend.models.data.JobRepository;
import org.launch_code.fetch_a_friend.services.BadgeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class JobScheduler {

    private final JobRepository jobRepository;

    private final BadgeService badgeService;

    public JobScheduler(JobRepository jobRepository, BadgeService badgeService) {
        this.jobRepository = jobRepository;
        this.badgeService = badgeService;
    }

    @Scheduled(cron = "@midnight")
    public void updateExpiredAndCompletedJobs() {
        jobRepository.closeExpiredJobs(LocalDate.now());
        jobRepository.completedClaimedJobs(LocalDate.now());
    }


    @Scheduled(cron = "0 1 * * ?") // every day at 1 AM
    @Transactional
    public void assignBadges() {
        jobRepository
                .findSitterIdsByCompletedJobEndDate(LocalDate.now().minusDays(1))
                .forEach(sitter -> {
                    List<Badge> jobBadges = badgeService.findJobNumberBadgeBySitter(sitter);
                    List<Badge> petBadges = badgeService.countCompletedJobBySitterAndPetType(sitter);
                    Set<Badge> sitterBadges = sitter.getBadges();
                    sitterBadges.addAll(jobBadges);
                    sitterBadges.addAll(petBadges);
                });
    }


}
