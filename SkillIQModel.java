package com.talentjoko.leadership2020;

import com.google.gson.annotations.SerializedName;

public class SkillIQModel {

    @SerializedName("name")
    String tvName;
    @SerializedName("badgeUrl")
    String badgeUrl;
    @SerializedName("country")
    String tvCountry;
    @SerializedName("score")
    String tvScore;

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getTvCountry() {
        return tvCountry;
    }

    public void setTvCountry(String tvCountry) {
        this.tvCountry = tvCountry;
    }

    public String getTvScore() {
        return tvScore;
    }

    public void setTvScore(String tvScore) {
        this.tvScore = tvScore;
    }
}
