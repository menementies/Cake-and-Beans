package com.example.cakeandbeans.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cakeandbeans.Orders;
import com.example.cakeandbeans.R;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewholder> {
    private Context context;
    private List<Orders> ordersList;

    public OrderAdapter(Context context, List<Orders> ordersList){
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public OrderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewholder(LayoutInflater.from(context)
                .inflate(R.layout.layout_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewholder holder, int position) {
        Glide.with(context)
                .load(ordersList.get(position).getImage())
                .into(holder.imageView);
        holder.txtPrice.setText(new StringBuilder("P").append(ordersList.get(position).getPrice()));
        holder.txtName.setText(new StringBuilder().append(ordersList.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class OrderViewholder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgView)
        ImageView imageView;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtPrice)
        TextView txtPrice;

        private Unbinder unbinder;

        public OrderViewholder(@NonNull View itemView){
            super(itemView);
        }
    }
}