package org.launchcode.fancyrats.controllers;

import org.launchcode.fancyrats.models.Badge;
import org.launchcode.fancyrats.models.BadgeType;
import org.launchcode.fancyrats.models.User;
import org.launchcode.fancyrats.models.data.BadgeRepository;
import org.launchcode.fancyrats.models.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/badges")
public class BadgesController {

    private final UserRepository userRepository;

    private final BadgeRepository badgeRepository;

    public BadgesController(UserRepository userRepository, BadgeRepository badgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
    }

    @GetMapping
    public List<Badge> getBadges() {
        return badgeRepository.findAll();
    }

    @GetMapping("/mybadges")
    public List<Badge> getSitterBadges(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Stream<Badge> badgesJobs = user.getBadges().stream()
                .filter(b -> b.getBadgeType() == BadgeType.JOB)
                .sorted(Comparator.comparingInt(b -> - b.getNumberOfJobs()));
        Stream<Badge> badgesAnimal = user.getBadges().stream()
                .filter(b -> b.getBadgeType() == BadgeType.SPECIALTY)
                .sorted(Comparator.comparingInt(b -> b.getPetType().ordinal()));
        return Stream.concat(badgesJobs, badgesAnimal).toList();
    }



}
