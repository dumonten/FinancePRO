package com.example.anotherversion;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private long curDateMilsec,date_lim;
    private int Itype;
    Date d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getSupportActionBar().setTitle("Статистика");

        Itype = getIntent().getIntExtra("type", 0);
        db = new DbHelper(this);
        CatList = db.getCategories();
        main_layout = findViewById(R.id.LinStats);

        d = new Date();
        curDateMilsec = d.getTime() / 1000L;

        date_lim = 0;
        if (Itype == 1) {
            date_lim = 86400L;
        } else if (Itype == 2) {
            date_lim = 604800L;
        } else if (Itype == 3) {
            date_lim = 2678400L;
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

        generateStats(curDateMilsec, date_lim, allCost, earningsCost);

        DiagBut = (Button) findViewById(R.id.dBut);

        DiagBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DiagramActivity.class);
                intent.putExtra("curdata", curDateMilsec);
                intent.putExtra("limit", date_lim);
                startActivity(intent);
            }
        });

    }

    private void generateStats(long curDateSec, long date_lim, float allCost, float earningCost) {
        for (Category cur : CatList) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            params.setMargins( 20, 0, 20, 0);
            Button button = new Button(this);
            button.setBackgroundResource(R.drawable.withshadow);
            button.setLayoutParams(params);
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
            button.setTypeface(typeface);
            button.setElevation(6);
            button.setText(cur.getName());
            button.setGravity(Gravity.CENTER_HORIZONTAL);
            button.setAllCaps(false);
            button.setTextSize(16);
            button.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            button.setTextColor(Color.parseColor("#293133"));
            main_layout.addView(button);

            CatItemData = db.getCategoriesItems(cur.getId());
            params.setMargins( 40, 20, 40, 20);

            boolean isNotEmpty = false;
            LinearLayout middle_layout = new LinearLayout(this);
            middle_layout.setOrientation(LinearLayout.VERTICAL);
            middle_layout.setVisibility(View.GONE);
            for (CategoryItem key : CatItemData) {
                if (curDateSec - key.getDateSec() < date_lim) {
                    TextView txt = new TextView(this);
                    txt.setText(key.getName() + ": " + key.getCost() + "p.");
                    txt.setLayoutParams(params);
                    txt.setTextSize(16);
                    txt.setTextColor(Color.BLACK);
                    Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
                    txt.setTypeface(typeface1);
                    middle_layout.addView(txt);
                    isNotEmpty = true;
                };
            }
            if (!isNotEmpty) {
                TextView txt = new TextView(this);
                txt.setText("Эта категория пуста" + " ");
                txt.setTextSize(16);
                txt.setTextColor(Color.BLACK);
                Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
                txt.setTypeface(typeface2);
                txt.setLayoutParams(params);
                middle_layout.addView(txt);
            }
            main_layout.addView(middle_layout);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (middle_layout.getVisibility() == View.GONE) {
                        middle_layout.setVisibility(View.VISIBLE);
                        button.setBackgroundColor(Color.parseColor("#f2f2f2"));
                        button.setTextColor(Color.parseColor("#757575"));
                    } else {
                        middle_layout.setVisibility(View.GONE);
                        button.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        button.setTextColor(Color.parseColor("#293133"));
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
        txt.setPadding(0,10,0,10);
        txt.setTextSize(16);
        txt.setGravity(Gravity.CENTER);
        txt.setText(s);
        txt.setTextColor(Color.BLACK);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        txt.setTypeface(typeface);
        txt.setBackgroundResource(R.drawable.backforitem);
        txt.setTextColor(Color.BLACK);
        main_layout.addView(txt);
    }

}