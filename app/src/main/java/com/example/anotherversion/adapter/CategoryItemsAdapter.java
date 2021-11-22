package com.example.anotherversion.adapter;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anotherversion.CategoryItemsPage;
import com.example.anotherversion.DbHelper;
import com.example.anotherversion.R;
import com.example.anotherversion.model.Category;
import com.example.anotherversion.model.CategoryItem;

import java.util.List;

public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.CategoryItemsViewHolder>{

    Context context;
    List<CategoryItem> items;
    int id;
    String name;

    public CategoryItemsAdapter(Context context, List<CategoryItem> items, int id, String name) {
        this.context = context;
        this.items = items;
        this.id = id;
        this.name = name;
    }

    @NonNull
    @Override
    public CategoryItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item_item, parent, false);
        return new CategoryItemsViewHolder((categoryItems));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemsViewHolder holder, int position) {
        holder.Btn.setText(items.get(holder.getAdapterPosition()).getName() + " " + items.get(holder.getAdapterPosition()).getCost() + "р.");
        DbHelper db = new DbHelper(holder.Btn.getContext());

        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.DeleteBtn.setEnabled(false);
                int id_delete = items.get(holder.getAdapterPosition()).getId();
                items.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount() - holder.getAdapterPosition());
                db.deleteCatItem(id_delete);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class CategoryItemsViewHolder extends RecyclerView.ViewHolder {

        Button Btn;
        Button DeleteBtn;

        public CategoryItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            Btn = itemView.findViewById(R.id.cat_item_btn);
            DeleteBtn = itemView.findViewById(R.id.cat_item_btn_delete);
        }
    }

}
