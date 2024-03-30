package org.launchcode.fancyrats.controllers;

import jakarta.validation.Valid;
import org.launchcode.fancyrats.models.Job;
import org.launchcode.fancyrats.models.User;
import org.launchcode.fancyrats.models.data.JobRepository;
import org.launchcode.fancyrats.models.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {

    private final JobRepository jobRepository;

    private UserRepository userRepository;

    public JobsController(
            JobRepository jobRepository,
            UserRepository userRepository
    ) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/myjobs")
    public List<Job> getMyJobs(@AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> userResult = userRepository.findByUsername(userDetails.getUsername());
        if(userResult.isPresent()) {
            User user = userResult.get();
            return jobRepository
                    .findAll()
                    .stream()
                    .filter(job ->
                            Objects.equals(job.getUser().getId(), user.getId()))
                    .toList();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public Job getJob(@PathVariable Integer id) {
        return jobRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/add")
    public ResponseEntity<Job> createJob(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody Job job
    ) throws URISyntaxException {
        Optional<User> userResult = userRepository.findByUsername(userDetails.getUsername());
        if(userResult.isPresent()) {
            User user = userResult.get();
            job.setUser(user);
        }
        job.setCreatedDate(LocalDate.now());
        Job savedJob = jobRepository.save(job);
        return ResponseEntity.created(new URI("/jobs/" + savedJob.getId())).body(savedJob);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Integer id, @Valid @RequestBody Job job) {
        Job currentJob = jobRepository.findById(id).orElseThrow(RuntimeException::new);
        currentJob.setDescription(job.getDescription());
        currentJob.setZipCode(job.getZipCode());
        currentJob.setPayRate(job.getPayRate());
        currentJob.setTotalHours(job.getTotalHours());
        currentJob.setStartDate(job.getStartDate());
        currentJob.setEndDate(job.getEndDate());
        currentJob = jobRepository.save(currentJob);
        return ResponseEntity.ok(currentJob);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable Integer id) {
        jobRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<FieldError> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        return fieldErrors;
    }
}
