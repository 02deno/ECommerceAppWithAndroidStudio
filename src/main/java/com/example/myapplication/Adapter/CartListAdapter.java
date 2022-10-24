package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.ItemDomain;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.R;
import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private ArrayList<ItemDomain> itemDomains;

    public CartListAdapter(ArrayList<ItemDomain> itemDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.itemDomains = itemDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;
    @NonNull
    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(itemDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(itemDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((itemDomains.get(position).getNumberInCart() * itemDomains.get(position).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(itemDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(itemDomains.get(position).getPic(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                managementCart.plusNumberItem(itemDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });


        holder.minusItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                managementCart.minusNumberItem(itemDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return itemDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem,num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCart);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberCart);
            plusItem  = itemView.findViewById(R.id.plusCart);
            minusItem = itemView.findViewById(R.id.minusCart);
        }
    }
}
