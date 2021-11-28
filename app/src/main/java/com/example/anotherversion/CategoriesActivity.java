package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anotherversion.adapter.CategoryAdapter;
import com.example.anotherversion.model.Category;

import java.util.List;
import java.util.Objects;

public class CategoriesActivity extends AppCompatActivity implements CategoryAdapter.OnCardClickListener {

    Button btnAdd;
    Dialog confirm;
    private DbHelper db;

    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;

    // метод, который получит события из нашего колбэка
    @Override
    public void onCardClick(View view, int position) {
        updateRecycleView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        db = new DbHelper(this);

        updateRecycleView();

        Objects.requireNonNull(getSupportActionBar()).setTitle("Категории");

        confirm = new Dialog(this);
        confirm.setContentView(R.layout.confirm_cat_add);
        confirm.setCancelable(true);

        Button btnAdd = findViewById(R.id.cat_add);
        Button btnAddNo = confirm.findViewById(R.id.exitCatNo);
        Button btnAddYes = confirm.findViewById(R.id.exitCatYes);

        EditText cat_name_obj = confirm.findViewById(R.id.confirm_cat_enter_name);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Category> cat = db.getCategories();
                if (cat.size() < 8) {
                    confirm.show();
                } else {
                    Toast.makeText(getApplicationContext(),"Достигнуто максимальное количество категорий!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.dismiss();
            }
        });

        btnAddYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat_name = cat_name_obj.getText().toString();
                List<Category> cat = db.getCategories();
                if (cat.size() >= 8) {
                    Toast.makeText(getApplicationContext(),"Достигнуто максимальное количество категорий!",Toast.LENGTH_SHORT).show();
                }
                else if (cat_name.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Вы не ввели название категории!",Toast.LENGTH_SHORT).show();
                }
                else if (db.checkExistsCat(cat_name))
                {
                    Toast.makeText(getApplicationContext(),"Такая категория уже создана!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.addCat(cat_name);
                    Toast.makeText(getApplicationContext(), "Категория добавлена!", Toast.LENGTH_SHORT).show();

                    updateRecycleView();
                }
            }
        });
    }

    public void updateRecycleView()
    {
        List<Category> categoryList = db.getCategories();
        setCategoryRecycler(categoryList);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        categoryRecycler = findViewById(R.id.CatView);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryAdapter.setOnCardClickListener(this);
        categoryRecycler.setAdapter(categoryAdapter);

    }
}