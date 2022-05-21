package com.example.cakeandbeans;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cakeandbeans.databinding.ActivitySignUpBinding;

public class LoginForm extends AppCompatActivity {
    //View binding
    private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText etUsername, etPassword;
        Button btnsignIn, btnsignUp;
        super.onCreate(savedInstanceState);


        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnsignIn = findViewById(R.id.bt_signIn);
        btnsignUp = findViewById(R.id.bt_signUp);
        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin")) {
                    Toast.makeText(LoginForm.this, "Logged In Successfully",  Toast.LENGTH_SHORT).show();
                            Intent next= new Intent(LoginForm.this,Menu.class);
                            startActivity(next);
                            finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Use admin as username and password!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent next= new Intent(LoginForm.this,SignUp.class);
                    startActivity(next);
                    finish();
            }
        });
    }
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginForm.this);
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
}