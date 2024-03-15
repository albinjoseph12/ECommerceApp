package com.eshopthis.finds.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshopthis.finds.R;
import com.eshopthis.finds.db.CategoryItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<CategoryItem> categoryItems;
    private OnCategoryClickListener onCategoryClickListener;

    public CategoryAdapter(Context context, List<CategoryItem> categoryItems, OnCategoryClickListener onCategoryClickListener) {
        this.context = context;
        this.categoryItems = categoryItems;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CategoryItem item = categoryItems.get(position);
        holder.categoryName.setText(item.getName());
        holder.categoryImage.setImageResource(item.getImageResourceId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCategoryClickListener.onCategoryClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }

    public interface OnCategoryClickListener {
        void onCategoryClick(CategoryItem categoryItem);
    }
}
