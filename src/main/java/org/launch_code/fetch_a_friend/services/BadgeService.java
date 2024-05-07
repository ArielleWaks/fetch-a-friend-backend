package org.launch_code.fetch_a_friend.services;

import org.launch_code.fetch_a_friend.models.Badge;
import org.launch_code.fetch_a_friend.models.BadgeType;
import org.launch_code.fetch_a_friend.models.PetType;
import org.launch_code.fetch_a_friend.models.User;
import org.launch_code.fetch_a_friend.models.data.BadgeRepository;
import org.launch_code.fetch_a_friend.models.data.JobRepository;
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

        return EnumSet.allOf(PetType.class).stream().map(petType -> {
            int completedSitterJobs = jobRepository.countCompletedJobBySitterAndPetType(sitter, petType);
            return badgeRepository.findBadgesByBadgeTypeAndNumberOfJobsAndPetType(BadgeType.SPECIALTY, completedSitterJobs, petType);
        }).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
