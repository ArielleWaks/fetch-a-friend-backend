package org.launchcode.fancyrats.services;

import org.launchcode.fancyrats.models.Badge;
import org.launchcode.fancyrats.models.PetType;
import org.launchcode.fancyrats.models.data.BadgeRepository;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {

    private final JobRepository jobRepository;

    private final BadgeRepository badgeRepository;

    public BadgeService(JobRepository jobRepository, BadgeRepository badgeRepository) {
        this.jobRepository = jobRepository;
        this.badgeRepository = badgeRepository;
    }

    public Badge checkCompletedJobsBadge(String username) {
        int completedSitterJobs = jobRepository.findCompletedJobsBySitterUsername(username).size();
        if(completedSitterJobs == 0) {
            return null;
        }
        if(completedSitterJobs < 5) {
            return badgeRepository.getReferenceById(1);
        }
        if(completedSitterJobs < 10) {
            return badgeRepository.getReferenceById(2);
        }
        if(completedSitterJobs <25) {
            return badgeRepository.getReferenceById(3);
        }
        return badgeRepository.getReferenceById(4);
    }

    public Badge checkDifferentSpeciesBadge(String username) {
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

    public Badge checkCompletedAnamalBadge(String username, PetType petType) {
        int completedDogJobs = jobRepository.findJobsBySitterAndPetType(username, petType.ordinal()).size();
        if(completedDogJobs >=3) {
            return badgeRepository.getReferenceById(31);
        }
        return null;
    }

    public Badge checkCompletedDogBadge(String username) {
        return checkCompletedAnamalBadge(username, PetType.DOG);
    }

    public Badge checkCompletedCatBadge(String username) {
        return checkCompletedAnamalBadge(username, PetType.CAT);
    }

    public Badge checkCompletedFishBadge(String username) {
        return checkCompletedAnamalBadge(username, PetType.FISH);
    }

    public Badge checkCompletedBirdBadge(String username) {
        return checkCompletedAnamalBadge(username, PetType.BIRD);
    }

}
