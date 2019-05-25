package com.example.gupshup;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btRegister;
    private TextView tvLogin;

    private FirebaseUser currentUser;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btRegister = findViewById(R.id.btRegister);
        tvLogin = findViewById(R.id.tvLogin);
        btRegister.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v == btRegister) {
            Intent intent = new Intent(LoginActivity.this, Register.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(tvLogin, "tvLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
            startActivity(intent, activityOptions.toBundle());
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null)
        {
            SendUserToMainActivity();
        }
    }

    private void SendUserToMainActivity()
    {
        Intent homeIntent = new Intent(LoginActivity.this, HomeScreen.class);
        startActivity(homeIntent);
    }
}

