package com.example.harunsofuoglu.superlig.data;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by harunsofuoglu on 26.10.2018.
 */
@Getter
@Setter
public class Team {

    String name;
    int overall;
    int point;
    int win;
    int loose;
    int draw;
    int playedMatches;

    public Team(String name, int overall, int point, int win, int loose, int draw, int playedMatches) {
        this.name = name;
        this.overall = overall;
        this.point = point;
        this.win = win;
        this.loose = loose;
        this.draw = draw;
        this.playedMatches = playedMatches;
    }





}
