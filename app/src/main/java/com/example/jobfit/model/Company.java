package com.example.jobfit.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Company implements Parcelable {
    private String companyTitle;
    private String address;
    private String about;
    private int logo;
    private List<String> role;
    private int kantor_img;

    public Company(String companyTitle, String address, String about, int logo, List<String> role, int kantor_img) {
        this.companyTitle = companyTitle;
        this.address = address;
        this.about = about;
        this.logo = logo;
        this.role = role;
        this.kantor_img = kantor_img;
    }

    public int getKantor_img() {
        return kantor_img;
    }

    public void setKantor_img(int kantor_img) {
        this.kantor_img = kantor_img;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }


    protected Company(Parcel in) {
        companyTitle = in.readString();
        address = in.readString();
        about = in.readString();
        logo = in.readInt();
        role = in.createStringArrayList();
        kantor_img = in.readInt();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(companyTitle);
        parcel.writeString(address);
        parcel.writeString(about);
        parcel.writeInt(logo);
        parcel.writeStringList(role);
        parcel.writeInt(kantor_img);
    }
}
