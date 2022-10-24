package com.example.myapplication.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.ShowDetailsActivity;
import com.example.myapplication.Domain.ItemDomain;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {

    ArrayList<ItemDomain> categoryItems;

    public PopularAdaptor(ArrayList<ItemDomain> categoryItems) {
        this.categoryItems = categoryItems;
    }



    @Override
    public PopularAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdaptor.ViewHolder holder, int position) {
        holder.title.setText(categoryItems.get(position).getTitle());
        holder.fee.setText(String.valueOf(categoryItems.get(position).getFee()));


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(categoryItems.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                intent.putExtra("object",categoryItems.get(position));
                holder.itemView.getContext().startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,fee;
        ImageView pic;
        TextView addBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.popularTitle);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
