package com.talentjoko.leadership2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mRecyclerView = findViewById(R.id.rvMain);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //RETROFIT @GET REQUEST
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance()
                .create(RetrofitInterface.class);
        Call<List<LeaderBoardModel>> listCall = retrofitInterface.getAllLeaders();
        listCall.enqueue(new Callback<List<LeaderBoardModel>>() {
            @Override
            public void onResponse(Call<List<LeaderBoardModel>> call, Response<List<LeaderBoardModel>> response) {
                parseData(response.body());

            }

            @Override
            public void onFailure(Call<List<LeaderBoardModel>> call, Throwable t) {

            }
        });
    }

    private void parseData(List<LeaderBoardModel> body) {
        LearnerViewAdapter learnerViewAdapter = new LearnerViewAdapter(body, getApplicationContext());
        mRecyclerView.setAdapter(learnerViewAdapter);
    }

    public void gotoTabs(View view) {
        Intent myIntent = new Intent(this, TabbedActivity.class);
        startActivity(myIntent);
    }
}
