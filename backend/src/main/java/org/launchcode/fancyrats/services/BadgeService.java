package org.launchcode.fancyrats.services;

import org.launchcode.fancyrats.models.Badge;
import org.launchcode.fancyrats.models.BadgeType;
import org.launchcode.fancyrats.models.PetType;
import org.launchcode.fancyrats.models.User;
import org.launchcode.fancyrats.models.data.BadgeRepository;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.models.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BadgeService {

    private final JobRepository jobRepository;

    private final BadgeRepository badgeRepository;


    public BadgeService(JobRepository jobRepository, BadgeRepository badgeRepository) {
        this.jobRepository = jobRepository;
        this.badgeRepository = badgeRepository;
    }

    public List<Badge> findJobNumberBadgeBySitter(User sitter) {
        int completedSitterJobs = jobRepository.countCompletedJobBySitter(sitter);
        return badgeRepository.findBadgeByBadgeTypeAndNumberOfJobs(BadgeType.JOB, completedSitterJobs);
    }

    public List<Badge> countCompletedJobBySitterAndPetType(User sitter) {
        /*
         *  TODO:
         *   jobs.stream get pet types
         *   Collect in to a set of pet types
         *   loop through types instead of all pet types
         */

        return EnumSet.allOf(PetType.class).stream().map(petType -> {
            int completedSitterJobs = jobRepository.countCompletedJobBySitterAndPetType(sitter, petType);
            return badgeRepository.findBadgesByBadgeTypeAndNumberOfJobsAndPetType(BadgeType.SPECIALTY, completedSitterJobs, petType);
        }).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
