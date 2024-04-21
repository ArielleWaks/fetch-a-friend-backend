package org.launchcode.fancyrats.services;

import org.launchcode.fancyrats.models.Badge;
import org.launchcode.fancyrats.models.BadgeType;
import org.launchcode.fancyrats.models.PetType;
import org.launchcode.fancyrats.models.User;
import org.launchcode.fancyrats.models.data.BadgeRepository;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.models.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    private final JobRepository jobRepository;

    private final BadgeRepository badgeRepository;

    private final UserRepository userRepository;

    public BadgeService(JobRepository jobRepository, BadgeRepository badgeRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.badgeRepository = badgeRepository;
        this.userRepository = userRepository;
    }

    private Badge findJobNumberBadgeBySitterId(Long sitterId){
        int completedSitterJobs = jobRepository.countCompletedJobBySitterId(sitterId);
        if(completedSitterJobs == 0) {
            return null;
        }
        if(completedSitterJobs < 5) {
            return badgeRepository.findBadgeByBadgeTypeAndNumberOfJobs(BadgeType.JOB, 1);
        }
        if(completedSitterJobs < 10) {
            return badgeRepository.findBadgeByBadgeTypeAndNumberOfJobs(BadgeType.JOB, 5);
        }
        if(completedSitterJobs <25) {
            return badgeRepository.findBadgeByBadgeTypeAndNumberOfJobs(BadgeType.JOB, 10);
        }
        return badgeRepository.findBadgeByBadgeTypeAndNumberOfJobs(BadgeType.JOB, 25);
    }

    public void assignJobNumberBadgeToSitter(User sitter){
        List<Badge> currentBadges = sitter.getBadges();
        if (currentBadges.contains(findJobNumberBadgeBySitterId(sitter.getId()))) {
            return;
        }
        currentBadges.add(findJobNumberBadgeBySitterId(sitter.getId()));
    }

    public Badge findCompletedJobsBadge(String username) {
        int completedSitterJobs = jobRepository.findCompletedJobsBySitterUsername(username).size();
        if(completedSitterJobs == 0) {
            return null;
        }
        if(completedSitterJobs < 5) {
            return badgeRepository.findBadgeByBadgeTypeAndNumberOfJobs(BadgeType.JOB, 1);
        }
        if(completedSitterJobs < 10) {
            return badgeRepository.getReferenceById(2);
        }
        if(completedSitterJobs <25) {
            return badgeRepository.getReferenceById(3);
        }
        return badgeRepository.getReferenceById(4);
    }

    public Badge findDifferentSpeciesBadge(String username) {
        int petTypeCount = 0;
        for (int i=0; i< PetType.values().length; i++) {
            if(!jobRepository.findJobsBySitterAndPetType(username, i).isEmpty()) {
                petTypeCount +=1;
            }
        }
        if (petTypeCount >= 5) {
            return badgeRepository.getReferenceById(22);
        }
        if (petTypeCount >= 3) {
            return badgeRepository.getReferenceById(21);
        }
        return null;
    }

    public Badge findCompletedAnamalBadge(String username, PetType petType) {
        int completedDogJobs = jobRepository.findJobsBySitterAndPetType(username, petType.ordinal()).size();
        if(completedDogJobs >=3) {
            return badgeRepository.getReferenceById(31);
        }
        return null;
    }

    public Badge findCompletedDogBadge(String username) {
        return findCompletedAnamalBadge(username, PetType.DOG);
    }

    public Badge findCompletedCatBadge(String username) {
        return findCompletedAnamalBadge(username, PetType.CAT);
    }

    public Badge findCompletedFishBadge(String username) {
        return findCompletedAnamalBadge(username, PetType.FISH);
    }

    public Badge findCompletedBirdBadge(String username) {
        return findCompletedAnamalBadge(username, PetType.BIRD);
    }

}
