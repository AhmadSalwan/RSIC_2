package com.example.jobfit.model;

import com.google.gson.annotations.SerializedName;

public class JobData {

    @SerializedName("Job_Role")  // Adjust field names to match JSON keys
    private String roleName;

    @SerializedName("Company")
    private String companyName;


    // Getters and Setters
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

}
