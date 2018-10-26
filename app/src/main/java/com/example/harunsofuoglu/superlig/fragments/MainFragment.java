package com.example.harunsofuoglu.superlig.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harunsofuoglu.superlig.R;
import com.example.harunsofuoglu.superlig.activities.MatchActivity;
import com.example.harunsofuoglu.superlig.data.Leagues;
import com.example.harunsofuoglu.superlig.network.ApiClient;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harunsofuoglu on 26.10.2018.
 */

public class MainFragment extends Fragment {


    @BindView(R.id.superLigName)
    public TextView superLigName;

    @BindView(R.id.superligIcon)
    public ImageView superLigIcon;

    @BindView(R.id.startLeague)
    public Button startButton;


    public static MainFragment newInstance() {
        return new MainFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        ApiClient.createService(getContext()).getLeague().enqueue(new Callback<Leagues>() {
            @Override
            public void onResponse(Call<Leagues> call, Response<Leagues> response) {

                Picasso.with(getContext()).load(response.body().getLeagueStage().get(0).getLogo()).into(superLigIcon);

                superLigName.setText(response.body().getLeagueStage().get(0).getTournamentName());

            }

            @Override
            public void onFailure(Call<Leagues> call, Throwable t) {

                Toast.makeText(getContext(), "FAİL", Toast.LENGTH_LONG).show();

            }
        });


        return view;

    }


    @OnClick(R.id.startLeague)
    public void setStartButton() {

        Intent intent = new Intent(getActivity(), MatchActivity.class);
        startActivity(intent);

    }


}
