package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class CategoriesActivity extends AppCompatActivity {

    Button btnAdd;
    Dialog confirm;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Категории");

        db = new DbHelper(this);

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
                confirm.show();
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

                if (cat_name.equals(""))
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
                }
            }
        });
    }
}