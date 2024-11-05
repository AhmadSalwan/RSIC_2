package com.example.jobfit.API;
public class UserInput {
    private String skills;
    private int experience;

    public UserInput() {
        this.skills = skills;
        this.experience = experience;
    }

    public UserInput(String skills, int experience) {
        this.skills = skills;
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
