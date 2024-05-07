package org.launch_code.fetch_a_friend.models.dto;

import jakarta.validation.constraints.NotNull;
import org.launch_code.fetch_a_friend.models.Job;
import org.launch_code.fetch_a_friend.models.User;

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
