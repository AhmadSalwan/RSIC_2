package com.example.jobfit;

public class Company {
    private String title;
    private String description;

    public Company(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
