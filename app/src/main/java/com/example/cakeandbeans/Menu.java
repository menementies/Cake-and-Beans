package com.example.cakeandbeans;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
import com.example.cakeandbeans.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();


        Button btnCake;
        Button btnCoffee;
        ImageView menu;

        ImageSlider imageSlider1;
        imageSlider1 = findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.bannerlogo, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.cakes, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.coffee, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.coffee2, ScaleTypes.CENTER_CROP));
        imageSlider1.setImageList(imageList);


        btnCake = findViewById(R.id.button_cake);
        btnCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cake();
            }
        });

        btnCoffee = findViewById(R.id.button_coffee);
        btnCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coffee();
            }
        });
        menu = findViewById(R.id.button_menu);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                funcMenu();
            }
        });


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
                Intent next= new Intent(Menu.this,Profile.class);
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
        layoutParamMenu.height =750;
        dialog3.getWindow().setAttributes(layoutParamMenu);
    }

    public void onBackPressed(){
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

    private void coffee() {
        View alertCustomDialog2 = LayoutInflater.from(Menu.this).inflate(R.layout.activity_coffee, null);
        AlertDialog.Builder alert2 = new AlertDialog.Builder(Menu.this);
        alert2.setView(alertCustomDialog2);
        AlertDialog dialog2 = alert2.create();

        ImageSlider coffee;
        coffee = alertCustomDialog2.findViewById(R.id.image_coffee);
        ArrayList<SlideModel> coffeeMenu = new ArrayList<>();
        coffeeMenu.add(new SlideModel(R.drawable.c1, ScaleTypes.CENTER_INSIDE));
        coffeeMenu.add(new SlideModel(R.drawable.c2, ScaleTypes.CENTER_INSIDE));
        coffeeMenu.add(new SlideModel(R.drawable.c4, ScaleTypes.CENTER_INSIDE));
        coffeeMenu.add(new SlideModel(R.drawable.c6, ScaleTypes.CENTER_INSIDE));
        coffeeMenu.add(new SlideModel(R.drawable.c7, ScaleTypes.CENTER_INSIDE));
        coffee.setImageList(coffeeMenu);

        Button order2;
        order2 = (Button) alertCustomDialog2.findViewById(R.id.button_order2);
        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
                Toast.makeText(Menu.this, "Ordered Successfully",  Toast.LENGTH_SHORT).show();
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

    private void cake() {
        View alertCustomDialog = LayoutInflater.from(Menu.this).inflate(R.layout.activity_cake, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(Menu.this);
        alert.setView(alertCustomDialog);
        AlertDialog dialog = alert.create();

        ImageSlider menu2;
        menu2 = alertCustomDialog.findViewById(R.id.image_cake);
        ArrayList<SlideModel> cakeMenu = new ArrayList<>();
        cakeMenu.add(new SlideModel(R.drawable.a, ScaleTypes.CENTER_INSIDE));
        cakeMenu.add(new SlideModel(R.drawable.b, ScaleTypes.CENTER_INSIDE));
        cakeMenu.add(new SlideModel(R.drawable.c, ScaleTypes.CENTER_INSIDE));
        cakeMenu.add(new SlideModel(R.drawable.d, ScaleTypes.CENTER_INSIDE));
        cakeMenu.add(new SlideModel(R.drawable.e, ScaleTypes.CENTER_INSIDE));
        cakeMenu.add(new SlideModel(R.drawable.f, ScaleTypes.CENTER_INSIDE));
        menu2.setImageList(cakeMenu);


        Button order;
        order = (Button) alertCustomDialog.findViewById(R.id.button_order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(Menu.this, "Ordered Successfully",  Toast.LENGTH_SHORT).show();
            }
        });

        dialog.getWindow().setLayout(350, 500);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);

    }
}