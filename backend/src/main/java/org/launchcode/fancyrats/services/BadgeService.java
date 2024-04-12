package org.launchcode.fancyrats.services;

import org.launchcode.fancyrats.models.Badge;
import org.launchcode.fancyrats.models.data.BadgeRepository;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.models.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    private JobRepository jobRepository;

    private UserRepository userRepository;

    private BadgeRepository badgeRepository;

    public Badge checkCompletedJobsBadge(String username) {
        int completedSitterJobs = jobRepository.findCompletedJobsBySitterUsername(username).size();
        if(completedSitterJobs == 0) {
            return null;
        }
        if(completedSitterJobs >= 1 && completedSitterJobs < 5) {
            return badgeRepository.getReferenceById(1);
        }
        if(completedSitterJobs >= 5 && completedSitterJobs < 10) {
            return badgeRepository.getReferenceById(2);
        }
        if(completedSitterJobs >= 10 && completedSitterJobs <25 ) {
            return badgeRepository.getReferenceById(3);
        }
        return badgeRepository.getReferenceById(4);
    }

//    public Badge checkDifferentSpeciesBadge(String username) {
//
//    }

    public Badge checkCompletedDogBadge(String username) {
        int completedDogJobs = jobRepository.findJobsBySitterAndPetType(username, 0).size();
        if(completedDogJobs >=3) {
            return badgeRepository.getReferenceById(31);
        }
        return null;
    }

    public Badge checkCompletedCatBadge(String username) {
        int completedCatJobs = jobRepository.findJobsBySitterAndPetType(username, 1).size();
        if(completedCatJobs >=3) {
            return badgeRepository.getReferenceById(32);
        }
        return null;
    }

    public Badge checkCompletedFishBadge(String username) {
        int completedFishJobs = jobRepository.findJobsBySitterAndPetType(username, 2).size();
        if(completedFishJobs >=3) {
            return badgeRepository.getReferenceById(33);
        }
        return null;
    }

    public Badge checkCompletedBirdBadge(String username) {
        int completedBirdJobs = jobRepository.findJobsBySitterAndPetType(username, 3).size();
        if(completedBirdJobs >=3) {
            return badgeRepository.getReferenceById(34);
        }
        return null;
    }

}
