package com.talentjoko.leadership2020;

import com.google.gson.annotations.SerializedName;

public class LeaderBoardModel {
    @SerializedName("name")
    String name;
    @SerializedName("hours")
    String hours;
    @SerializedName("country")
    String country;

    @SerializedName("badgeUrl")
    String fullUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
