package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button agentList = (Button) findViewById(R.id.agent_list_button);
        Button searchAgent = (Button) findViewById(R.id.search_agent_button);
        Button addAgent = (Button) findViewById(R.id.add_agent_button);
        Button logout = (Button) findViewById(R.id.logout_button);

        agentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this, "Button 'List of Agents' clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        searchAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this, "Button 'Search Agents' Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        addAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateAgentActivity = new Intent(MenuActivity.this, CreateAgentActivity.class);
                startActivity(goToCreateAgentActivity);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginActivity = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(goToLoginActivity);
                finish();
            }
        });
    }
}