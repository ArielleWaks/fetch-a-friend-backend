package org.launch_code.fetch_a_friend.controllers;

import org.launch_code.fetch_a_friend.models.Job;
import org.launch_code.fetch_a_friend.models.data.JobRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    private final JobRepository jobRepository;

    public TestController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess(@AuthenticationPrincipal UserDetails userDetails) {
        return "User Content." + userDetails.getUsername();
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('MODERATOR')")
    public String profileAccess() {
        return "";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Job> adminAccess() {
        return jobRepository.findAll();
//        return "Admin Board.";
    }


}
