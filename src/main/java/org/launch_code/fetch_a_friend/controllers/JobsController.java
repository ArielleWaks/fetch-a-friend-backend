package org.launch_code.fetch_a_friend.controllers;

import jakarta.validation.Valid;
import org.launch_code.fetch_a_friend.models.Job;
import org.launch_code.fetch_a_friend.models.JobStatus;
import org.launch_code.fetch_a_friend.models.User;
import org.launch_code.fetch_a_friend.models.data.JobRepository;
import org.launch_code.fetch_a_friend.models.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {

    private final JobRepository jobRepository;

    private final UserRepository userRepository;

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

    @GetMapping("/open")
    public List<Job> getOpenJobs() {
        return jobRepository
                .findAll()
                .stream()
                .filter(job -> Objects.equals(job.getJobStatus(), JobStatus.STATUS_OPEN))
                .toList();
    }

    @GetMapping("/myjobs")
    public List<Job> getMyJobs(@AuthenticationPrincipal UserDetails userDetails) {
        return jobRepository.findJobsByUserUsername(userDetails.getUsername());
    }

    @GetMapping("/mysitting")
    public List<Job> getMyJobsSitting(@AuthenticationPrincipal UserDetails userDetails) {
        return jobRepository.findJobsBySitterUsername(userDetails.getUsername());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Job currentJob = jobRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        if (Objects.equals(currentJob.getUser().getUsername(), userDetails.getUsername())) {
            return ResponseEntity.ok(currentJob);
        }
       return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/add")
    public ResponseEntity<Job> createJob(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody Job job
    ) throws URISyntaxException {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        job.setUser(user);
        job.setCreatedDate(LocalDate.now());
        job.setJobStatus(JobStatus.STATUS_OPEN);
        job.setSitter(null);
        Job savedJob = jobRepository.save(job);
        return ResponseEntity.created(new URI("/jobs/" + savedJob.getId())).body(savedJob);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id,
            @Valid @RequestBody Job job
    ) {
        Job currentJob = jobRepository.findById(id).orElseThrow(RuntimeException::new);
        if (Objects.equals(currentJob.getUser().getUsername(), userDetails.getUsername())) {
            currentJob.setDescription(job.getDescription());
            currentJob.setZipCode(job.getZipCode());
            currentJob.setPayRate(job.getPayRate());
            currentJob.setTotalHours(job.getTotalHours());
            currentJob.setStartDate(job.getStartDate());
            currentJob.setEndDate(job.getEndDate());
            currentJob.setPetName(job.getPetName());
            currentJob.setPetType(job.getPetType());
            currentJob.setPetNumber(job.getPetNumber());
            currentJob = jobRepository.save(currentJob);
            return ResponseEntity.ok(currentJob);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/sitter/{id}")
    public ResponseEntity<Job> assignSitter(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        Job currentJob = jobRepository.findById(id).orElseThrow(RuntimeException::new);
        if (Objects.isNull(currentJob.getSitter()) || currentJob.getJobStatus() == JobStatus.STATUS_OPEN) {
            if (Objects.equals(currentJob.getUser().getUsername(), userDetails.getUsername())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                User user = userRepository.findByUsername(userDetails.getUsername())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
                currentJob.setSitter(user);
                currentJob.setJobStatus(JobStatus.STATUS_CLAIMED);
                currentJob = jobRepository.save(currentJob);
                return ResponseEntity.ok(currentJob);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> deleteJob(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id) {
        Job currentJob = jobRepository.findById(id).orElseThrow(RuntimeException::new);
        if (Objects.equals(currentJob.getUser().getUsername(), userDetails.getUsername())) {
            jobRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<FieldError> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        return fieldErrors;
    }



    @PutMapping("/bookmark/{id}")
    public ResponseEntity<Job> bookmarkToggle(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Job currentJob = jobRepository.findById(id).orElseThrow(RuntimeException::new);

        if (currentJob.getUsersWhoBookmarked().contains(user)) {
            currentJob.removeUserWhoBookmarked(user);
            currentJob = jobRepository.save(currentJob);
            return ResponseEntity.ok(currentJob);
        } else {
            currentJob.addUserWhoBookmarked(user);
            currentJob = jobRepository.save(currentJob);
            return ResponseEntity.ok(currentJob);
        }
    }

    @GetMapping("/myBookmarkedJobs")
    public List<Job> getMyBookmarks(@AuthenticationPrincipal UserDetails userDetails) {
        return jobRepository.findBookmarkedJobsByUsername(userDetails.getUsername());
    }

}
