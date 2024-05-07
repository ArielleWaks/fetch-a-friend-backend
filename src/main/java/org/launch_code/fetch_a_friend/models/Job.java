package org.launch_code.fetch_a_friend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Job {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Zip code required")
    @Digits(integer = 5, fraction = 0, message = "Zip Code must be 5 digits")
    @Min(value = 10000, message = "Zip Code must be 5 digits")
    @Positive(message = "Zip Code must be a positive number")
    private Integer zipCode;

    @NotNull(message = "Start date required")
    @FutureOrPresent(message = "Start date must be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date required")
    @FutureOrPresent(message = "End date must be in the future")
    private LocalDate endDate;

    private LocalDate createdDate;

    @NotNull(message = "Pay rate required")
    @Positive(message = "Pay rate must be greater than zero")
    private double payRate;

    @NotNull(message = "Number of hours required")
    @Positive(message = "Number of hours must be greater than zero")
    private double totalHours;

    @NotNull(message = "Description required")
    private String description;

    private String petName;

    private PetType petType;

    private Integer petNumber;

    @ManyToOne
    private User user;

    @ManyToOne
    private User sitter;

    private JobStatus jobStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "job_user_bookmark",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersWhoBookmarked = new ArrayList<>();

    public Job(Integer zipCode, LocalDate startDate, LocalDate endDate, LocalDate createdDate, double payRate,
               double totalHours, String description, String petName, PetType petType, Integer petNumber, User user, User sitter, JobStatus jobStatus) {
        this.zipCode = zipCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.payRate = payRate;
        this.totalHours = totalHours;
        this.description = description;
        this.petName = petName;
        this.petType = petType;
        this.petNumber = petNumber;
        this.user = user;
        this.sitter = sitter;
        this.jobStatus = jobStatus;
    }

    public Job() {}

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public void setPetNumber(Integer petNumber) {
        this.petNumber = petNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSitter(User sitter) {
        this.sitter = sitter;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public void addUserWhoBookmarked(User user){ this.usersWhoBookmarked.add(user);}

    public void removeUserWhoBookmarked (User user){ this.getUsersWhoBookmarked().remove(user);}
}
