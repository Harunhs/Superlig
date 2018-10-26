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
import com.example.harunsofuoglu.superlig.data.LeagueStage;
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
    @BindView(R.id.resultTwo)
    public TextView resultTwo;
    @BindView(R.id.resultThree)
    public TextView resultThree;

    @BindView(R.id.playMatches)
    public Button playMatches;


    public ArrayList<LeagueTable> arrayList;
    public ArrayList<String> matches ;
    public ArrayList<String> secondMatches;
    public ArrayList<String> scoresFirst;



    public static MatchFragment newInstance() {
        return new MatchFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        ButterKnife.bind(this, view);

        arrayList = new ArrayList<>();

        ApiClient.createService(getContext()).getLeague().enqueue(new Callback<Leagues>() {
            @Override
            public void onResponse(Call<Leagues> call, Response<Leagues> response) {
                int teamSize = response.body().getLeagueStage().get(0).getLeagueTable().size();


                for (int i = 0; i < teamSize; i++) {
                    arrayList.add(response.body().getLeagueStage().get(0).getLeagueTable().get(i));

                }


            }

            @Override
            public void onFailure(Call<Leagues> call, Throwable t) {

                Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


    @OnClick(R.id.playMatches)
    public void play() {
        generateFixture(arrayList.size(),arrayList);

        matchOne.setText(matches.get(0));
        matchTwo.setText(matches.get(1));
        matchThree.setText(matches.get(2));

        resultOne.setText(scoresFirst.get(0));
        resultTwo.setText(scoresFirst.get(1));
        resultThree.setText(scoresFirst.get(2));

    }




    private void generateFixture(int teamSize, ArrayList<LeagueTable> teamList) {

        matches = new ArrayList<>();
        secondMatches = new ArrayList<>();
        scoresFirst = new ArrayList<>();




        // Kaç round sonrası lig tamamlanacak
        int roundCount = teamSize - 1;
        // Bir round'da ne kadar maç oynanır
        int matchCountPerRound = teamSize / 2;

        List<LeagueTable> list = new ArrayList<>();

        // Takim listesini oluşturuyoruz.
        //0. takımdan (teamSize-1). takima kadar.

        for (int i = 0; i < teamSize; i++) {

            list.add(teamList.get(i));
        }


        for (int i = 0; i < roundCount; i++) {


            for (int j = 0; j < matchCountPerRound; j++) {


                int secondIndex = (teamSize - 1) - j;


                String match = list.get(j).getName()
                        + "-" + list.get(secondIndex).getName();


                String matchDep = list.get(secondIndex).getName() + " - " + list.get(j).getName();

                score(list,scoresFirst,j,secondIndex);

                matches.add(match);
                secondMatches.add(matchDep);


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

        /*
         BURAYI HALLETMEM LAZIM BUTONLA BIRLIKTE CALISACAKLAR
         */


    }

    public static void score(List<LeagueTable> leagueTable,ArrayList<String> scores,int i ,int j){
        int homeScore;
        int depScore;


        Random random = new Random();
        if(leagueTable.get(i).getOverall()>leagueTable.get(j).getOverall()){
            homeScore = random.nextInt(4);
            depScore = random.nextInt(2);
        }else {
            homeScore = random.nextInt(2);
            depScore = random.nextInt(4);
        }

        scores.add(String.valueOf(homeScore) + " - " + String.valueOf(depScore));
    }





}

