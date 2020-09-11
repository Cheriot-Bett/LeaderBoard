package com.talentjoko.leadership2020;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearnerViewAdapter extends RecyclerView.Adapter<LearnerViewAdapter.RecyclerHolder> {

    List<LeaderBoardModel> models;
    Context context;


    public LearnerViewAdapter(List<LeaderBoardModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_row_learner, viewGroup, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder recyclerHolder, int position) {
        recyclerHolder.textName.setText(models.get(position).name);
        recyclerHolder.textCountry.setText(models.get(position).country);
        recyclerHolder.textVariable.setText(models.get(position).hours);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(models.get(position).getFullUrl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(recyclerHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textCountry;
        TextView textVariable;
        ImageView imageView;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.name);
            textCountry = itemView.findViewById(R.id.tvcountry);
            textVariable = itemView.findViewById(R.id.tv_variable);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
