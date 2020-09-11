package com.talentjoko.leadership2020;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("/api/hours")
    Call<List<LeaderBoardModel>> getAllLeaders();

    @GET("/api/skilliq")
    Call<List<SkillIQModel>> getAllSkills();

}
