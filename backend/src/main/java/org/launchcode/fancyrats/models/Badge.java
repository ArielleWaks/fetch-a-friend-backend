package org.launchcode.fancyrats.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Badge {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String description;

    private String message;

    public Badge(String name, String description, String message) {
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public Badge() {
    }

    public Integer getId() {
        return id;
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
