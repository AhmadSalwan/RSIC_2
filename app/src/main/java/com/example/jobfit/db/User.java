package com.example.jobfit.db;
public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String gender;
    private String skills;
    private String experience;
    private String achievements;
    private String education;
    private byte[] profilePicture;

    // Constructor
    public User(String username, String email, String phoneNumber, String gender,
                String skills, String experience, String achievements,
                String education, byte[] profilePicture) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.skills = skills;
        this.experience = experience;
        this.achievements = achievements;
        this.education = education;
        this.profilePicture = profilePicture;
    }

    // Getters and setters for each field
    // Example:
    public String getUsername() {
        return username;
    }

    public String getSkills() {
        return skills;
    }

    public String getExperience() {
        return experience;
    }

    // Add the rest of the getters and setters as needed
}


