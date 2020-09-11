package com.talentjoko.leadership2020.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talentjoko.leadership2020.R;
import com.talentjoko.leadership2020.RetrofitInstance;
import com.talentjoko.leadership2020.RetrofitInterface;
import com.talentjoko.leadership2020.SkillIQModel;
import com.talentjoko.leadership2020.SkillIQViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkilIQFragment extends Fragment {


    RecyclerView recyclerView;

    public static SkilIQFragment newInstance(int index) {
        SkilIQFragment fragment = new SkilIQFragment();
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
        View root = inflater.inflate(R.layout.fragment_skill, container, false);
        recyclerView=root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        Call<List<SkillIQModel>> listCall = retrofitInterface.getAllSkills();
        listCall.enqueue(new Callback<List<SkillIQModel>>() {
            @Override
            public void onResponse(Call<List<SkillIQModel>> call, Response<List<SkillIQModel>> response) {
                parseData(response.body());

            }

            @Override
            public void onFailure(Call<List<SkillIQModel>> call, Throwable t) {

            }
        });

        return root;
    }

    private void parseData(List<SkillIQModel> body) {
        SkillIQViewAdapter recycerViewAdapter = new SkillIQViewAdapter(body, getContext());
        recyclerView.setAdapter(recycerViewAdapter);
    }
}