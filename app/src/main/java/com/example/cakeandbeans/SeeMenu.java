package com.example.cakeandbeans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cakeandbeans.adapter.OrderAdapter;
import com.example.cakeandbeans.listener.ICartLoadListener;
import com.example.cakeandbeans.listener.IOrderLoadListener;
import com.example.cakeandbeans.utils.SpaceItemDecoration;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeeMenu extends AppCompatActivity implements ICartLoadListener, IOrderLoadListener {
    @BindView(R.id.order)
    RecyclerView order;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.button_menu)
    FrameLayout button_menu;
    @BindView(R.id.menu_layout) //activity_menu
    RelativeLayout menu_layout;

    ICartLoadListener cartLoadListener;
    IOrderLoadListener orderLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View alertCustomDialog2 = LayoutInflater.from(this).inflate(R.layout.activity_see_menu, null);
        AlertDialog.Builder alert2 = new AlertDialog.Builder(SeeMenu.this);
        alert2.setView(alertCustomDialog2);
        AlertDialog dialog2 = alert2.create();


        Button order2;
        order2 = (Button) alertCustomDialog2.findViewById(R.id.button_order2);
        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
                Toast.makeText(SeeMenu.this, "Ordered Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        dialog2.getWindow().setLayout(350, 500);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.setCanceledOnTouchOutside(true);
        dialog2.show();
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
        layoutParams2.copyFrom(dialog2.getWindow().getAttributes());
        layoutParams2.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams2.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog2.getWindow().setAttributes(layoutParams2);

        init();
        loadOrdersFromFirebase();
    }
    private void loadOrdersFromFirebase() {
        List<Orders> orders = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Items")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot drinkSnapshot : snapshot.getChildren()) {
                                Orders order = drinkSnapshot.getValue(Orders.class);
                                order.setKey((drinkSnapshot.getKey()));
                                orders.add(order);
                            }
                            orderLoadListener.onOrderLoadLSuccess(orders);
                        } else
                            orderLoadListener.onOrderLoadFailed("Item not Found");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    private void init() {
        ButterKnife.bind(this);

        cartLoadListener = this;
        orderLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        order.setLayoutManager(gridLayoutManager);
        order.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onCartLoadLSuccess(List<CartModel> cartModelList) {
    }

    @Override
    public void onCartLoadFailed(String message) {
    }

    @Override
    public void onOrderLoadLSuccess(List<Orders> orderList) {
        OrderAdapter adapter = new OrderAdapter(this,orderList);
        order.setAdapter(adapter);
    }

    @Override
    public void onOrderLoadFailed(String message) {
        Snackbar.make(menu_layout, message, Snackbar.LENGTH_LONG).show();
    }

}