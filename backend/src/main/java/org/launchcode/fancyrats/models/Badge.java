package org.launchcode.fancyrats.models;

import jakarta.persistence.*;

@Entity
public class Badge {

    @Id
    @GeneratedValue
    private Integer id;

    private BadgeType badgeType;

    private String name;

    private String description;

    private String message;

    public Badge(BadgeType badgeType, String name, String description, String message) {
        this.badgeType = badgeType;
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public Badge() {
    }

    public Integer getId() {
        return id;
    }

    public BadgeType getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(BadgeType badgeType) {
        this.badgeType = badgeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
