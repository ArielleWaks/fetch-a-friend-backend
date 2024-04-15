package org.launchcode.fancyrats.controllers;

import org.launchcode.fancyrats.models.Badge;
import org.launchcode.fancyrats.models.data.BadgeRepository;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.models.data.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
public class BadgesController {

    private final JobRepository jobRepository;

    private final UserRepository userRepository;

    private final BadgeRepository badgeRepository;

    public BadgesController(JobRepository jobRepository, UserRepository userRepository, BadgeRepository badgeRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
    }

    @GetMapping
    public List<Badge> getBadges() {
        return badgeRepository.findAll();
    }

//    @GetMapping("/mybadges")
//    public List<Badge> getSitterBadges(@AuthenticationPrincipal UserDetails userDetails) {
//
//    }



}
