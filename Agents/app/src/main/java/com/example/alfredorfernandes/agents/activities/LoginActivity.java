package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText loginField = (EditText) findViewById(R.id.login_field);
        EditText passwordField = (EditText) findViewById(R.id.password_field);
        Button loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMenuActivity = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(goToMenuActivity);
                finish();
            }
        });

    }
}