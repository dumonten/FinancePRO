package com.example.anotherversion.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.Window;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.anotherversion.CategoryItemsPage;
import com.example.anotherversion.DbHelper;
import com.example.anotherversion.R;
import com.example.anotherversion.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    Context context;
    List<Category> categories;

    public interface OnCardClickListener {
        void onCardClick(View view,  int position);
    }

    // создаем поле объекта-колбэка
    private static CategoryAdapter.OnCardClickListener mListener;

    // метод-сеттер для привязки колбэка к получателю событий
    public void setOnCardClickListener(CategoryAdapter.OnCardClickListener listener) {
        mListener = listener;
    }

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder((categoryItems));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.categoryBtn.setText(categories.get(holder.getAdapterPosition()).getName());

        Button btnAddNo;
        Button btnAddYes;
        Dialog confirm;
        confirm = new Dialog(holder.categoryBtn.getContext());
        confirm.setContentView(R.layout.confirm_delete);
        //confirm.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogback));
        confirm.setCancelable(true);
        btnAddNo = confirm.findViewById(R.id.exitCatNo);
        btnAddYes = confirm.findViewById(R.id.exitCatYes);
        DbHelper db = new DbHelper(holder.categoryBtn.getContext());

        btnAddNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.dismiss();
            }
        });

        holder.categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryItemsPage.class);

                intent.putExtra("id", categories.get(holder.getAdapterPosition()).getId());
                intent.putExtra("name", categories.get(holder.getAdapterPosition()).getName());

                context.startActivity(intent);
            }
        });

        if (categories.get(holder.getAdapterPosition()).getName().equals("Доходы")) {
            holder.categoryBtnDelete.setVisibility(View.GONE);
        }
        else
        holder.categoryBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.show();
            }
        });

        btnAddYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.categoryBtnDelete.setEnabled(false);
                int id_delete = categories.get(holder.getAdapterPosition()).getId();
                confirm.dismiss();
                db.deleteCat(id_delete);
                mListener.onCardClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {

        Button categoryBtn;
        Button categoryBtnDelete;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryBtn = itemView.findViewById(R.id.cat_item_btn);
            categoryBtnDelete = itemView.findViewById(R.id.cat_item_btn_delete);
        }


    }

}
