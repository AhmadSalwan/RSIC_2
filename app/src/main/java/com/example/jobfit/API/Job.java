package com.example.jobfit.API;

public class Job {
    private String Job_Role;
    private String Company;
    private String Location;
    private double Skill_Similarity;
    private double Experience_Diff;

    public String getJob_Role() {
        return Job_Role;
    }

    public void setJob_Role(String job_Role) {
        Job_Role = job_Role;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public double getSkill_Similarity() {
        return Skill_Similarity;
    }

    public void setSkill_Similarity(double skill_Similarity) {
        Skill_Similarity = skill_Similarity;
    }

    public double getExperience_Diff() {
        return Experience_Diff;
    }

    public void setExperience_Diff(double experience_Diff) {
        Experience_Diff = experience_Diff;
    }
}
