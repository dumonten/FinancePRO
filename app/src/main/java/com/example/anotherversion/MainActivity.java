package com.example.anotherversion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The Btn stat.
     */
    Button btnStat, /**
     * The Btn cat.
     */
    btnCat, /**
     * The Btn exit.
     */
    btnExit, /**
     * The Btn day.
     */
    btnDay, /**
     * The Btn week.
     */
    btnWeek, /**
     * The Btn month.
     */
    btnMonth, /**
     * The Btn ex yes.
     */
    btnExYes, /**
     * The Btn ex no.
     */
    btnExNo;
    /**
     * The Dialog.
     */
    Dialog dialog, /**
     * The Confirm.
     */
    confirm;
    /**
     * The Im stat.
     */
    ImageView imStat, /**
     * The Im categr.
     */
    imCategr, /**
     * The Im ext.
     */
    imExt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(MainActivity.this);
        confirm = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_intervals);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogback));
        confirm.setContentView(R.layout.confirm_exit);
        confirm.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogback));
        confirm.setCancelable(false);
        imStat = (ImageView) findViewById(R.id.imStat);
        imCategr = (ImageView) findViewById(R.id.imCategr);
        imExt = (ImageView) findViewById(R.id.imExt);
        btnStat = (Button) findViewById(R.id.toStatistics);
        btnCat = (Button) findViewById(R.id.toCategories);
        btnExit = (Button) findViewById(R.id.toExit);
        btnDay = (Button) dialog.findViewById(R.id.bDay);
        btnWeek = (Button) dialog.findViewById(R.id.bWeek);
        btnMonth = (Button) dialog.findViewById(R.id.bMonth);
        btnExYes = (Button) confirm.findViewById(R.id.exitYes);
        btnExNo = (Button) confirm.findViewById(R.id.exitNo);

        imStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        imExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.show();
            }
        });
        imCategr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                startActivity(intent);
            }
        });

        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });

        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
            }
        });

        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.show();
            }
        });

        btnExYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        btnExNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.dismiss();
            }
        });
    }
}