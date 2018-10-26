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

public class Leagues {

    @SerializedName("leagueStage")
    @Expose
    private List<LeagueStage> leagueStage;
}
