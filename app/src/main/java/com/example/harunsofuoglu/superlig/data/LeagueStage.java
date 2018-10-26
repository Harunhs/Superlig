package com.example.harunsofuoglu.superlig.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by harunsofuoglu on 26.10.2018.
 */


@Getter
@Setter
public class LeagueStage {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("tournamentName")
    @Expose
    private String tournamentName;

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("leagueTable")
    @Expose
    private List<LeagueTable> leagueTable = null;


}
