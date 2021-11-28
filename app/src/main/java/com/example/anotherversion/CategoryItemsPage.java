package com.example.anotherversion;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anotherversion.adapter.CategoryItemsAdapter;
import com.example.anotherversion.model.CategoryItem;

import java.util.Date;
import java.util.List;

public class CategoryItemsPage extends AppCompatActivity implements CategoryItemsAdapter.OnCardClickListener {
    private DbHelper db;
    Dialog confirm;
    RecyclerView categoryItemsRecycler;
    CategoryItemsAdapter categoryItemsAdapter;
    public int id;
    public String name;

    // метод, который получит события из нашего колбэка
    @Override
    public void onCardClick(View view, int position) {
        updateRecycleView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_page);
        getSupportActionBar().setTitle("Экран сумм");
        db = new DbHelper(this);
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");

        confirm = new Dialog(this);
        confirm.setContentView(R.layout.confirm_cat_add_item);
        confirm.setCancelable(true);

        updateRecycleView();

        TextView nameTextView = findViewById(R.id.textViewCategoryItems);
        RecyclerView items = findViewById(R.id.RecyclerViewCategoryItems);
        Button btnAdd = findViewById(R.id.cat_items_add);
        Button btnAddNo = confirm.findViewById(R.id.exitCatNo);
        Button btnAddYes = confirm.findViewById(R.id.exitCatYes);

        EditText item_name_dialog = confirm.findViewById(R.id.confirm_cat_enter_name);
        EditText item_cost_dialog = confirm.findViewById(R.id.confirm_cat_enter_cost);

        nameTextView.setText(name);

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
                String cat_name = item_name_dialog.getText().toString();
                String cat_cost_str = item_cost_dialog.getText().toString().replace(",",".");

                if (cat_name.equals("")) {
                    Toast.makeText(getApplicationContext(),"Вы не ввели название вложения!",Toast.LENGTH_SHORT).show();
                }
                else if (isDigit(cat_cost_str) == false) {
                    Toast.makeText(getApplicationContext(),"Неверная сумма!",Toast.LENGTH_SHORT).show();
                }
                else if (Float.parseFloat(cat_cost_str) <= 0.0) {
                    Toast.makeText(getApplicationContext(),"Неверная сумма!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Date curTime = new Date();
                    long curT = curTime.getTime() / 1000L;
                    String rnd = String.format("%.2f", Float.parseFloat(cat_cost_str));
                    rnd = rnd.replace(",",".");
                    db.addCatItem(cat_name, Float.parseFloat(rnd), id, curT);
                    Toast.makeText(getApplicationContext(), "Вложение добавлено!", Toast.LENGTH_SHORT).show();
                    updateRecycleView();
                }
            }
        });
    }

    public void updateRecycleView()
    {
        List<CategoryItem> categoryItems = db.getCategoriesItems(id);
        setCategoryRecycler(categoryItems);
    }

    private void setCategoryRecycler(List<CategoryItem> itemsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        categoryItemsRecycler = findViewById(R.id.RecyclerViewCategoryItems);
        categoryItemsRecycler.setLayoutManager(layoutManager);
        categoryItemsAdapter = new CategoryItemsAdapter(this, itemsList, id, name);
        categoryItemsAdapter.setOnCardClickListener(this);
        categoryItemsRecycler.setAdapter(categoryItemsAdapter);
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
