package org.launchcode.fancyrats.models.dto;

import jakarta.validation.constraints.NotNull;
import org.launchcode.fancyrats.models.Job;
import org.launchcode.fancyrats.models.User;

public class UserJobDTO {

    @NotNull
    private User user;

    @NotNull
    private Job job;

    public UserJobDTO() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
