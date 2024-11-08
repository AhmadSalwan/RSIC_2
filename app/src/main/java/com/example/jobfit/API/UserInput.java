package com.example.jobfit.API;
public class UserInput {
    private String skills;
    private int experience;
    private String company;
    private String job_role;

    public UserInput() {
        this.skills = skills;
        this.experience = experience;
    }

    public UserInput(String skills, int experience, String company, String job_role) {
        this.skills = skills;
        this.experience = experience;
        this.company = company;
        this.job_role = job_role;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }
}
