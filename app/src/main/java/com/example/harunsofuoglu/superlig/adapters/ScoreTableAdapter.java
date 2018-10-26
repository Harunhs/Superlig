package com.example.harunsofuoglu.superlig.adapters;

import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harunsofuoglu.superlig.R;
import com.example.harunsofuoglu.superlig.data.Team;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harunsofuoglu on 27.10.2018.
 */

public class ScoreTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Team> teams;


    public ScoreTableAdapter(List<Team> teams){this.teams = teams;}


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    class SettingsHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.team_name)
        TextView teamName;
        @BindView(R.id.played_matches)
        TextView playedMatches;
        @BindView(R.id.win_count)
        TextView winCount;
        @BindView(R.id.loose_count)
        TextView looseCount;
        @BindView(R.id.draw_count)
        TextView drawCount;
        @BindView(R.id.point_count)
        TextView pointCount;


        public SettingsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }




}
