package org.launch_code.fetch_a_friend.models;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Badge {

    @Id
    private Integer id;

    private BadgeType badgeType;

    private Integer numberOfJobs;

    private PetType petType;

    private String name;

    private String description;

    private String message;

    public Badge(Integer id, BadgeType badgeType, Integer numberOfJobs, PetType petType, String name, String description, String message) {
        this.id = id;
        this.badgeType = badgeType;
        this.numberOfJobs = numberOfJobs;
        this.petType = petType;
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public Badge() {
    }

    public void setBadgeType(BadgeType badgeType) {
        this.badgeType = badgeType;
    }

    public void setNumberOfJobs(Integer numberOfJobs) {
        this.numberOfJobs = numberOfJobs;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
