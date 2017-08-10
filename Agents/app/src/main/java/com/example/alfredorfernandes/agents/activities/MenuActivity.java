package com.example.alfredorfernandes.agents.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.DatabaseHelper;
import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;

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
                openActivity(AgentListActivity.class);
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
                openActivity(CreateAgentActivity.class);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openActivity(Class activity) {

        Intent goToActivity = new Intent(MenuActivity.this, activity);
        startActivity(goToActivity);
    }
}
