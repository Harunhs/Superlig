package com.example.harunsofuoglu.superlig.network;

import com.example.harunsofuoglu.superlig.data.LeagueStage;
import com.example.harunsofuoglu.superlig.data.LeagueTable;
import com.example.harunsofuoglu.superlig.data.Leagues;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by harunsofuoglu on 26.10.2018.
 */

public interface ApiService {

    @GET("/iseAlim/league.json")
    Call<Leagues> getLeague();


}
