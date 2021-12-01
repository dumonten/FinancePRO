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

/**
 * The type Category items adapter.
 */
public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.CategoryItemsViewHolder>{

    /**
     * The Context.
     */
    Context context;
    /**
     * The Items.
     */
    List<CategoryItem> items;
    /**
     * The Id.
     */
    int id;
    /**
     * The Name.
     */
    String name;

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
    private static OnCardClickListener mListener;

    /**
     * Sets on card click listener.
     *
     * @param listener the listener
     */
// метод-сеттер для привязки колбэка к получателю событий
    public void setOnCardClickListener(OnCardClickListener listener) {
        mListener = listener;
    }

    /**
     * Instantiates a new Category items adapter.
     *
     * @param context the context
     * @param items   the items
     * @param id      the id
     * @param name    the name
     */
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
                db.deleteCatItem(id_delete);
                mListener.onCardClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * The type Category items view holder.
     */
    public static final class CategoryItemsViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Btn.
         */
        Button Btn;
        /**
         * The Delete btn.
         */
        Button DeleteBtn;

        /**
         * Instantiates a new Category items view holder.
         *
         * @param itemView the item view
         */
        public CategoryItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            Btn = itemView.findViewById(R.id.cat_item_btn);
            DeleteBtn = itemView.findViewById(R.id.cat_item_btn_delete);
        }
    }

}
