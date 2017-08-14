package com.example.alfredorfernandes.agents.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.alfredorfernandes.agents.R;

public class AgentHistoryActivity extends AppCompatActivity {

    private ListView missionsAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_history);

        setTitle("AGENTS HISTORY");
    }
}
