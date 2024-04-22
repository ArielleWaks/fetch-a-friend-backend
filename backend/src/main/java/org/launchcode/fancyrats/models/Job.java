package org.launchcode.fancyrats.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

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

    @NotNull
    private String chosenAnimalType;

    @ManyToOne
    private User user;

    @ManyToOne
    private User sitter;

    private JobStatus jobStatus;

    public Job(Integer zipCode, LocalDate startDate, LocalDate endDate, LocalDate createdDate, double payRate,
               double totalHours, String description, String petName, PetType petType, Integer petNumber, User user, User sitter, JobStatus jobStatus, String chosenAnimalType) {
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
        this.chosenAnimalType = chosenAnimalType;
    }

    public Job() {}

    public Integer getId() {
        return id;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Integer getPetNumber() {
        return petNumber;
    }

    public void setPetNumber(Integer petNumber) {
        this.petNumber = petNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSitter() {
        return sitter;
    }

    public void setSitter(User sitter) {
        this.sitter = sitter;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getChosenAnimalType(){return this.chosenAnimalType;}

    public void setChosenAnimalType(String newChosenAnimal){this.chosenAnimalType = newChosenAnimal;}
}
