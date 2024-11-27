package com.example.jobfit.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CompanyFacts implements Parcelable {
    private String factsTitle;
    private String factsDesc;
    private int factsImg;

    protected CompanyFacts(Parcel in) {
        factsTitle = in.readString();
        factsDesc = in.readString();
        factsImg = in.readInt();
    }

    public static final Creator<CompanyFacts> CREATOR = new Creator<CompanyFacts>() {
        @Override
        public CompanyFacts createFromParcel(Parcel in) {
            return new CompanyFacts(in);
        }

        @Override
        public CompanyFacts[] newArray(int size) {
            return new CompanyFacts[size];
        }
    };

    public String getFactsTitle() {
        return factsTitle;
    }

    public void setFactsTitle(String factsTitle) {
        this.factsTitle = factsTitle;
    }

    public String getFactsDesc() {
        return factsDesc;
    }

    public void setFactsDesc(String factsDesc) {
        this.factsDesc = factsDesc;
    }

    public int getFactsImg() {
        return factsImg;
    }

    public void setFactsImg(int factsImg) {
        this.factsImg = factsImg;
    }

    public CompanyFacts(String factsTitle, String factsDesc, int factsImg) {
        this.factsTitle = factsTitle;
        this.factsDesc = factsDesc;
        this.factsImg = factsImg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(factsTitle);
        parcel.writeString(factsDesc);
        parcel.writeInt(factsImg);
    }
}
