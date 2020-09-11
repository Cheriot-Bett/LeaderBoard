package com.talentjoko.leadership2020.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talentjoko.leadership2020.LeaderBoardModel;
import com.talentjoko.leadership2020.R;
import com.talentjoko.leadership2020.LearnerViewAdapter;
import com.talentjoko.leadership2020.RetrofitInstance;
import com.talentjoko.leadership2020.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class LeaderBoardFragment extends Fragment {
RecyclerView recyclerView;

    public static LeaderBoardFragment newInstance(int index) {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        Bundle bundle = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_learner, container, false);
        recyclerView=root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
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

        return root;
    }

    private void parseData(List<LeaderBoardModel> body) {
        LearnerViewAdapter learnerViewAdapter = new LearnerViewAdapter(body, getContext());
        recyclerView.setAdapter(learnerViewAdapter);
    }
}