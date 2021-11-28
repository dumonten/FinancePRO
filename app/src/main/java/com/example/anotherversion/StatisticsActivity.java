package com.example.anotherversion;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anotherversion.model.Category;
import com.example.anotherversion.model.CategoryItem;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {
    private DbHelper db;
    private List<Category> CatList;
    private List<CategoryItem> CatItemData;
    private LinearLayout main_layout;
    private Button DiagBut;
    public int Itype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getSupportActionBar().setTitle("Статистика");

        Itype = getIntent().getIntExtra("type", 0);
        db = new DbHelper(this);
        CatList = db.getCategories();
        main_layout = findViewById(R.id.LinStats);

        Date d = new Date();
        long curDateSec = (long)d.getTime();

        long date_lim = 0;
        if (Itype == 1) {
            date_lim = 86400000;
        } else if (Itype == 2) {
            date_lim = 604800000;
        } else if (Itype == 3) {
            date_lim = 2678400000L;
        }

        long allCost = 0;
        long earningsCost = 0;
        for (Category cur : CatList) {
            CatItemData = db.getCategoriesItems(cur.getId());
            for (CategoryItem key : CatItemData) {
                allCost += (long)(key.getCost() * 100);
                if (cur.getId() == 1) {
                    earningsCost += (long)(key.getCost() * 100);
                }
            }
        }

        generateStats(curDateSec, date_lim, allCost, earningsCost);

        DiagBut = (Button) findViewById(R.id.dBut);

        DiagBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DiagramActivity.class);
                startActivity(intent);
            }
        });

    }

    private void generateStats(long curDateSec, long date_lim, float allCost, float earningCost) {
        for (Category cur : CatList) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            params.setMargins( 12, 15, 12, 15);
            Button button = new Button(this);
            button.setLayoutParams(params);
            button.setText(cur.getName());
            button.setHeight(50);
            button.setBackgroundColor(Color.parseColor("#fbad00"));
            button.setTextColor(Color.parseColor("#FF000000"));
            main_layout.addView(button);

            CatItemData = db.getCategoriesItems(cur.getId());
            params.setMargins( 20, 5, 12, 5);

            boolean isNotEmpty = false;
            LinearLayout middle_layout = new LinearLayout(this);
            middle_layout.setOrientation(LinearLayout.VERTICAL);
            middle_layout.setVisibility(View.GONE);
            for (CategoryItem key : CatItemData) {
                if (curDateSec - 1000L * key.getDateSec() < date_lim) {
                    TextView txt = new TextView(this);
                    txt.setText(key.getName() + ": " + key.getCost() + "p.");
                    txt.setLayoutParams(params);
                    middle_layout.addView(txt);
                    isNotEmpty = true;
                };
            }
            if (!isNotEmpty) {
                TextView txt = new TextView(this);
                txt.setText("Эта категория пуста" + " ");
                txt.setLayoutParams(params);
                middle_layout.addView(txt);
            }
            main_layout.addView(middle_layout);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (middle_layout.getVisibility() == View.GONE) {
                        middle_layout.setVisibility(View.VISIBLE);
                        button.setBackgroundColor(Color.parseColor("#fdd67f"));
                        button.setTextColor(Color.parseColor("#FFFFFFFF"));
                    } else {
                        middle_layout.setVisibility(View.GONE);
                        button.setBackgroundColor(Color.parseColor("#fbad00"));
                        button.setTextColor(Color.parseColor("#FF000000"));
                    }
                }
            });
        }

        addTextView("Ваши доходы: " + Float.toString((float)(earningCost) / 100) + "p.");
        addTextView("Ваши общие расходы: " + Float.toString((float)(allCost - earningCost) / 100) + "p.");
        addTextView("Баланс: " + Float.toString((float)(earningCost - allCost + earningCost) / 100) + "p.");
    }

    private void addTextView(String s) {
        TextView txt = new TextView(this);
        txt.setTextSize(18);
        txt.setText(s);
        txt.setTextColor(Color.BLACK);
        main_layout.addView(txt);
    }

}