package com.example.jobfit.model;


public class Company {
    private String name;
    private String description;

    // Constructor with parameters
    public Company(String name, String description, int logohp) {
        this.name = name;
        this.description = description;
    }

    // Default constructor (if needed)
    public Company() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Optionally, you can add setters if you want to modify these fields later
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
