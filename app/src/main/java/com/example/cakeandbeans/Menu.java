package com.example.cakeandbeans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//from ryan
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.RelativeLayout;

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

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu extends AppCompatActivity implements ICartLoadListener, IOrderLoadListener {
    @BindView(R.id.order)
    RecyclerView order;
    @BindView(R.id.menu_layout)
    RelativeLayout menu_layout;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.button_menu)
    FrameLayout button_menu;

    ICartLoadListener cartLoadListener;
    IOrderLoadListener orderLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        Button btnCake;
        Button btnSeeMenu;
        ImageView menu;

        ImageSlider imageSlider1;
        imageSlider1 = findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.bannerlogo, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.cakes, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.coffee, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.coffee2, ScaleTypes.CENTER_CROP));
        imageSlider1.setImageList(imageList);


        btnSeeMenu = findViewById(R.id.button_see_menu);
        btnSeeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeMenu();
            }
        });
        menu = findViewById(R.id.button_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcMenu();
            }
        });

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

    private void funcMenu() {
        View alertCustomDialog3 = LayoutInflater.from(Menu.this).inflate(R.layout.activity_menupopup, null);
        AlertDialog.Builder alert3 = new AlertDialog.Builder(Menu.this, R.style.my_dialog);
        alert3.setView(alertCustomDialog3);
        AlertDialog dialog3 = alert3.create();

        Button profile;
        profile = (Button) alertCustomDialog3.findViewById(R.id.button_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(Menu.this, Profile.class);
                startActivity(next);
                finish();
            }
        });
        dialog3.getWindow().setLayout(150, 250);
        dialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog3.setCanceledOnTouchOutside(true);
        dialog3.show();
        WindowManager.LayoutParams layoutParamMenu = new WindowManager.LayoutParams();
        layoutParamMenu.copyFrom(dialog3.getWindow().getAttributes());
        layoutParamMenu.gravity = Gravity.TOP | Gravity.RIGHT;
        layoutParamMenu.width = 500;
        layoutParamMenu.height = 750;
        dialog3.getWindow().setAttributes(layoutParamMenu);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Quit App?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void seeMenu() {
        View alertCustomDialog2 = LayoutInflater.from(Menu.this).inflate(R.layout.activity_see_menu, null);
        AlertDialog.Builder alert2 = new AlertDialog.Builder(Menu.this);
        alert2.setView(alertCustomDialog2);
        AlertDialog dialog2 = alert2.create();


        Button order2;
        order2 = (Button) alertCustomDialog2.findViewById(R.id.button_order2);
        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
                Toast.makeText(Menu.this, "Ordered Successfully", Toast.LENGTH_SHORT).show();
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