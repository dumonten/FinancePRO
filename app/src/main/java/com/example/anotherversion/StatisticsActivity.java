package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getSupportActionBar().setTitle("Статистика");
    }
}