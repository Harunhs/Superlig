package com.example.harunsofuoglu.superlig.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harunsofuoglu.superlig.R;
import com.example.harunsofuoglu.superlig.data.LeagueTable;
import com.example.harunsofuoglu.superlig.data.Leagues;
import com.example.harunsofuoglu.superlig.data.Team;
import com.example.harunsofuoglu.superlig.network.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harunsofuoglu on 26.10.2018.
 */

public class MatchFragment extends Fragment {



    @BindView(R.id.matchOne)
    public TextView matchOne;
    @BindView(R.id.matchTwo)
    public TextView matchTwo;
    @BindView(R.id.matchThree)
    public TextView matchThree;

    @BindView(R.id.resultOne)
    public TextView resultOne;

    @BindView(R.id.playMatches)
    public Button playMatches;



    public static ArrayList<LeagueTable> arrayList;


    public static MatchFragment newInstance(){
        return new MatchFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_match,container,false);
        ButterKnife.bind(this,view);
        return view;
    }



    @OnClick(R.id.playMatches)
    public void play(){

        ApiClient.createService(getContext()).getLeague().enqueue(new Callback<Leagues>() {
            @Override
            public void onResponse(Call<Leagues> call, Response<Leagues> response) {
                int teamSize = response.body().getLeagueStage().get(0).getLeagueTable().size();
                arrayList = new ArrayList<>();

                for(int i = 0 ;i<teamSize;i++){
                    arrayList.add(response.body().getLeagueStage().get(0).getLeagueTable().get(i));

                }

                generateFixture(arrayList.size(),arrayList);

            }

            @Override
            public void onFailure(Call<Leagues> call, Throwable t) {

                Toast.makeText(getContext(),"ASDADASDf",Toast.LENGTH_LONG).show();
            }
        });

    }


    private  void generateFixture(int teamSize,ArrayList<LeagueTable> teamList){


        // Kaç round sonrası lig tamamlanacak
        int roundCount=teamSize-1;
        // Bir round'da ne kadar maç oynanır
        int matchCountPerRound=teamSize/2;

        List<LeagueTable> list=new ArrayList<>();

        // Takim listesini oluşturuyoruz.
        //0. takımdan (teamSize-1). takima kadar.

        for (int i = 0; i < teamSize; i++) {

            list.add(teamList.get(i));
        }


        for (int i = 0;i<roundCount;i++) {

            for (int j = 0; j < matchCountPerRound; j++) {

                int firstIndex = j;
                int secondIndex = (teamSize - 1) - j;

                String match = list.get(firstIndex).getName()
                        + "-" + list.get(secondIndex).getName();

                if (j == 0) {
                    matchOne.setText(match);


                } else if (j == 1) {
                    matchTwo.setText(match);

                } else
                    matchThree.setText(match);

            }

            // İlk eleman sabit olacak şekilde elamanları kaydırıyoruz
            List<LeagueTable> newList = new ArrayList<>();

            // İlk eleman sabit
            newList.add(list.get(0));

            // Son eleman ikinci eleman yapıyoruz.
            newList.add(list.get(list.size() - 1));

            for (int k = 1; k < list.size() - 1; k++) {
                newList.add(list.get(k));
            }

            // Keydırılan liste yeni liste oluyor.
            list = newList;
        }
    }


        public static void winOrLoose(Team teamOne,Team teamTwo){


            int winScore = (int)(Math.random()*(4-1)+1) ;
            int looseScore = (int)(Math.random() +1);
            String score = String.valueOf(winScore) + " - " + String.valueOf(looseScore);

            if (teamOne.getOverall()>teamTwo.getOverall()){

                teamOne.setPoint(3);
                teamOne.setWin(1);
                teamOne.setPlayedMatches(1);


            }



        }


    }

