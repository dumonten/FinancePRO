package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.anotherversion.model.Category;
import com.example.anotherversion.model.CategoryItem;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DiagramActivity extends AppCompatActivity {
    private DbHelper db;
    private List<Category> CatList;
    private List<CategoryItem> CatItemData;
    private PieChart pieChart;
    private long curDateSec,date_lim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);
        getSupportActionBar().setTitle("Диаграмма");

        db = new DbHelper(this);
        CatList = db.getCategories();
        date_lim = getIntent().getLongExtra("limit",0);
        curDateSec = getIntent().getLongExtra("curdata",0);

        pieChart = findViewById(R.id.pieCha);
        loadPieChartData();
        setupPieChart();
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setVerticalScrollBarEnabled(true);
        pieChart.setEntryLabelTextSize(10);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Категории в долях");
        pieChart.setCenterTextSize(18);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(35);
        pieChart.setTransparentCircleRadius(40);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setEnabled(true);

        pieChart.getLegend().setWordWrapEnabled(true);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        long allCost = 0;
        for (Category cur : CatList) {
            CatItemData = db.getCategoriesItems(cur.getId());
            for (CategoryItem key : CatItemData) {
                if (curDateSec - key.getDateSec() < date_lim) {
                    allCost += (long)(key.getCost() * 100);
                }
            }
        }
        for (Category cur : CatList) {
            long curCost = 0;
            CatItemData = db.getCategoriesItems(cur.getId());
            for (CategoryItem key : CatItemData) {
                if (curDateSec - key.getDateSec() < date_lim) {
                    curCost += (long)(key.getCost() * 100);
                }
            }
            if (allCost != 0 && curCost != 0) entries.add(new PieEntry((float)curCost / (float)allCost, cur.getName()));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}