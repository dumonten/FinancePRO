package com.example.anotherversion.adapter;

import static androidx.core.content.ContextCompat.getDrawable;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.anotherversion.CategoryItemsPage;
import com.example.anotherversion.DbHelper;
import com.example.anotherversion.R;
import com.example.anotherversion.model.Category;

import java.util.List;

/**
 * The type Category adapter.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    /**
     * The Context.
     */
    Context context;
    /**
     * The Categories.
     */
    List<Category> categories;
    /**
     * The Confirm.
     */
    Dialog confirm;

    /**
     * The interface On card click listener.
     */
    public interface OnCardClickListener {
        /**
         * On card click.
         *
         * @param view     the view
         * @param position the position
         */
        void onCardClick(View view,  int position);
    }

    // создаем поле объекта-колбэка
    private static CategoryAdapter.OnCardClickListener mListener;

    /**
     * Sets on card click listener.
     *
     * @param listener the listener
     */
// метод-сеттер для привязки колбэка к получателю событий
    public void setOnCardClickListener(CategoryAdapter.OnCardClickListener listener) {
        mListener = listener;
    }

    /**
     * Instantiates a new Category adapter.
     *
     * @param context    the context
     * @param categories the categories
     */
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
        confirm = new Dialog(holder.categoryBtn.getContext());
        confirm.setContentView(R.layout.confirm_delete);
        confirm.getWindow().setBackgroundDrawable(getDrawable(confirm.getContext(), R.drawable.dialogback));
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

    /**
     * The type Category view holder.
     */
    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Category btn.
         */
        Button categoryBtn;
        /**
         * The Category btn delete.
         */
        Button categoryBtnDelete;

        /**
         * Instantiates a new Category view holder.
         *
         * @param itemView the item view
         */
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryBtn = itemView.findViewById(R.id.cat_item_btn);
            categoryBtnDelete = itemView.findViewById(R.id.cat_item_btn_delete);
        }


    }

    /**
     * The Item touch helper call back.
     */
    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT| ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            categories.remove(viewHolder.getAdapterPosition());
            viewHolder.notify();
        }
    };

}
