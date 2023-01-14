package com.example.sylhetjerseyhouse.Fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.sylhetjerseyhouse.ItemView;
import com.example.sylhetjerseyhouse.R;
import com.example.sylhetjerseyhouse.db.Model;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ImageViewHolder>{


    private Context context;
    private List<Model> dataList;

    public Adapter(Context context, List<Model> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pattern, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        final Model temp = dataList.get(position);

        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.tvPrice.setText(dataList.get(position).getPrice()+" tk");
        Glide.with(context).load(dataList.get(position).getImage()).into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(context, ItemView.class);
                    intent.putExtra("title", temp.getTitle());
                    intent.putExtra("price", temp.getPrice());
                    intent.putExtra("description", temp.getDescription());
                    intent.putExtra("imageData", temp.getImage());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvPrice, tvDescription;
        ImageView imageView;
        ConstraintLayout constraintLayout;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);


            tvTitle = itemView.findViewById(R.id.id_itemTitle);
            tvPrice = itemView.findViewById(R.id.id_itemPrice);
            imageView = itemView.findViewById(R.id.id_itemImage);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutItem);

        }
    }
}
