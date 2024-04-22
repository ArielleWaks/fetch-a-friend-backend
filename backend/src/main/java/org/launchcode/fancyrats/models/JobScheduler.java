package org.launchcode.fancyrats.models;

import jakarta.transaction.Transactional;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.services.BadgeService;
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
                .forEach(sitter -> {
                    //TODO: get list of recent jobs
                    List<Badge> jobBadges = badgeService.findJobNumberBadgeBySitter(sitter);
                    List<Badge> petBadges = badgeService.countCompletedJobBySitterAndPetType(sitter);
//                    List<Badge> wildBadges = badgeService.findJobNumberBadgeBySitter(sitter);
                    Set<Badge> sitterBadges = sitter.getBadges();
                    sitterBadges.addAll(jobBadges);
                    sitterBadges.addAll(petBadges);
                });
    }
    //TODO: make badge service method names make more sense like getAnimalBasedJobsBySitter



}
