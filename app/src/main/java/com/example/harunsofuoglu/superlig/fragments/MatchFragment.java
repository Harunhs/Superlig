package com.example.harunsofuoglu.superlig.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harunsofuoglu.superlig.R;
import com.example.harunsofuoglu.superlig.adapters.ScoreTableAdapter;
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
    @BindView(R.id.playNext)
    public Button playNext;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;


    public static ArrayList<LeagueTable> arrayList;
    public ArrayList<String> matches;

    public static List<Team> teamList;
    public static ArrayList<Integer> scoreList;
    public static List<Team> matchList;



    public static int counter = 0;
    public static int counterTwo = 0;
    public static int counterThree = 0;
    public static int counterFour = 0;

    public static int matchCounter = 0;
    public static int matchCounterTwo = 1;

    public static int resultCounter = 0;
    public static int resultCounterTwo = 0;
    public static int anInt = 0;





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
                    baseTeamList();

                }

                generateFixture(arrayList.size(), arrayList);

            }

            @Override
            public void onFailure(Call<Leagues> call, Throwable t) {

                Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }


    @OnClick(R.id.playNext)
    public void setPlayNext() {

        if (counter < arrayList.size() - 1 && counter == counterThree) {


            matchFixtures(matchCounter);
            counter++;
            matchCounter = matchCounter + 6;

        } else if (counter == 5 && counterTwo < arrayList.size() - 1 && counterTwo == counterFour) {

            matchFixtures(matchCounterTwo);
            counterTwo++;
            matchCounterTwo = matchCounterTwo + 6;

        } else if (counterTwo == arrayList.size() - 1) {


            Toast.makeText(getContext(), "ALL MATCHES PLAYED", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getContext(), "PRESS THE PLAY MATCHES", Toast.LENGTH_LONG).show();


        }
        setUpScoreTableAdapter();

    }


    public void matchFixtures(int counter) {

        matchOne.setText(matches.get(counter));
        matchTwo.setText(matches.get(counter + 2));
        matchThree.setText(matches.get(counter + 4));


    }

    public void scoreMatches(int counterScores) {



        String firstMatch = String.valueOf(scoreList.get(counterScores)) + " - " + String.valueOf(scoreList.get(counterScores + 1));
        String secondMatch = String.valueOf(scoreList.get(counterScores + 2)) + " - " + String.valueOf(scoreList.get(counterScores + 3));
        String thirdMatch = String.valueOf(scoreList.get(counterScores + 4)) + " - " + String.valueOf(scoreList.get(counterScores + 5));

        resultOne.setText(firstMatch);
        resultTwo.setText(secondMatch);
        resultThree.setText(thirdMatch);


            for (int i = 0; i < teamList.size(); i++) {

                if(anInt == 30) {
                    anInt = anInt - 2;
                }
                winDrawLoose(teamList.get(i), scoreList.get(anInt), scoreList.get(anInt + 1));

                anInt = anInt + 2;
            }



    }

    @OnClick(R.id.playMatches)
    public void play() {

        if (counterThree < arrayList.size() - 1 && counterThree < counter) {

            scoreMatches(resultCounter);
            counterThree++;
            resultCounter = resultCounter + 6;
        } else if (counterThree == arrayList.size() - 1 && counterFour < arrayList.size() - 1 && counterFour < counterTwo) {

            scoreMatches(resultCounterTwo);
            counterFour++;
            resultCounterTwo = resultCounterTwo + 6;

        } else if (counterFour == arrayList.size() - 1 && resultCounterTwo == scoreList.size() - 1) {

            Toast.makeText(getContext(), "ALL MATCHES PLAYED", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "PRESS THE PLAY NEXT MATCHES", Toast.LENGTH_LONG).show();
        }


    }

    void setUpScoreTableAdapter() {

        ScoreTableAdapter settingsAdapter = new ScoreTableAdapter(sortList(teamList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(settingsAdapter);
    }

    private void generateFixture(int teamSize, ArrayList<LeagueTable> teamListt) {

        matches = new ArrayList<>();
        scoreList = new ArrayList<>();
        matchList = new ArrayList<>();


        int roundCount = teamSize - 1;
        int matchCountPerRound = teamSize / 2;

        List<LeagueTable> list = new ArrayList<>();

        for (int i = 0; i < teamSize; i++) {

            list.add(teamListt.get(i));
        }

        for (int i = 0; i < roundCount; i++) {


            for (int j = 0; j < matchCountPerRound; j++) {


                int secondIndex = (teamSize - 1) - j;


                String match = list.get(j).getName()
                        + "-" + list.get(secondIndex).getName();


                String matchDep = list.get(secondIndex).getName() + " - " + list.get(j).getName();

                score(list, j, secondIndex);


                matches.add(match);
                matches.add(matchDep);
                Team a = new Team(list.get(j).getName(), 0, 0, 0, 0, 0);
                Team b = new Team(list.get(secondIndex).getName(), 0, 0, 0, 0, 0);
                matchList.add(a);
                matchList.add(b);


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

    public static void score(List<LeagueTable> leagueTable, int i, int j) {
        int homeScore;
        int depScore;

        Random random = new Random();
        if (leagueTable.get(i).getOverall() > leagueTable.get(j).getOverall()) {
            homeScore = random.nextInt(6);
            depScore = random.nextInt(3);

        } else {
            homeScore = random.nextInt(3);
            depScore = random.nextInt(6);
        }

        scoreList.add(homeScore);
        scoreList.add(depScore);
    }


    public static List<Team> baseTeamList() {

        teamList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            Team t = new Team(arrayList.get(i).getName(), 0, 0, 0, 0, 0);
            teamList.add(t);
        }

        return teamList;
    }


    public static void winDrawLoose(Team team, int scoreOne, int scoreTwo) {


        if (scoreOne > scoreTwo) {
            team.setPoint(team.getPoint() + 3);
            team.setWin(team.getWin() + 1);
            team.setLoose(team.getLoose());
            team.setDraw(team.getDraw());

        } else if (scoreOne == scoreTwo) {

            team.setPoint(team.getPoint() + 1);
            team.setDraw(team.getDraw() + 1);
            team.setWin(team.getWin());
            team.setLoose(team.getLoose());

        } else if (scoreOne < scoreTwo) {
            team.setLoose(team.getLoose() + 1);
            team.setPoint(team.getPoint());
            team.setWin(team.getWin());
            team.setDraw(team.getDraw());

        }
        team.setPlayedMatches(team.getPlayedMatches() + 1);
        for (int i = 0;i<teamList.size();i++){
            if(teamList.get(i).getName().equals(team.getName())){
                teamList.set(i,team);
            }
        }



    }


    public static List<Team> sortList(List<Team> list) {

        for (int i = 0; i < list.size(); i++) {

            int frst = list.get(i).getPoint();
            int temp = i;

            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getPoint() > frst) {

                    frst = list.get(j).getPoint();
                    temp = j;
                }
            }

            if (temp != i) {

                list.get(temp).setPoint(list.get(i).getPoint());
                list.get(i).setPoint(frst);

            }

        }

        return list;
    }

}

