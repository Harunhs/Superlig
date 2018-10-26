package com.example.harunsofuoglu.superlig.activities;


import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.harunsofuoglu.superlig.R;

import com.example.harunsofuoglu.superlig.fragments.MainFragment;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance())
                .commit();



    }

    }

