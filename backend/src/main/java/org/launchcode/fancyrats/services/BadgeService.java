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
        if(completedSitterJobs == 1) {
            return badgeRepository.getReferenceById(1);
        }
        if(completedSitterJobs > 1 && completedSitterJobs <= 5) {
            return badgeRepository.getReferenceById(2);
        }
        return badgeRepository.getReferenceById(3);
    }

}
