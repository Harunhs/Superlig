package com.example.harunsofuoglu.superlig.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.harunsofuoglu.superlig.R;
import com.example.harunsofuoglu.superlig.fragments.MainFragment;
import com.example.harunsofuoglu.superlig.fragments.MatchFragment;

import butterknife.ButterKnife;

/**
 * Created by harunsofuoglu on 26.10.2018.
 */

public class MatchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        ButterKnife.bind(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.match_container, MatchFragment.newInstance())
                .commit();


    }


}
