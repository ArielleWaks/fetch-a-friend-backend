package org.launchcode.fancyrats.controllers;

import org.launchcode.fancyrats.models.Badge;
import org.launchcode.fancyrats.models.User;
import org.launchcode.fancyrats.models.data.BadgeRepository;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.models.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/mybadges")
    public Set<Badge> getSitterBadges(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return user.getBadges();
    }



}
