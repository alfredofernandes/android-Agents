package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.adapter.AgentAdapter;
import com.example.alfredorfernandes.agents.adapter.MissionAgentAdapter;
import com.example.alfredorfernandes.agents.dao.AgentDAO;
import com.example.alfredorfernandes.agents.dao.MissionAgentDAO;
import com.example.alfredorfernandes.agents.dao.MissionDAO;
import com.example.alfredorfernandes.agents.helper.MissionHelper;
import com.example.alfredorfernandes.agents.model.Agent;
import com.example.alfredorfernandes.agents.model.Mission;
import com.example.alfredorfernandes.agents.model.MissionAgent;

import java.util.List;

public class MissionFormActivity extends AppCompatActivity {

    private MissionHelper helper;

    private ListView agentsList;
    private MissionAgentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_form);

        setTitle("CREATE MISSION");

        helper = new MissionHelper(this);

        Intent intent = getIntent();
        Mission mission = (Mission) intent.getSerializableExtra("mission");

        // Agent List
        agentsList = (ListView) findViewById(R.id.form_mission_agents);

        AgentDAO agentDAO = new AgentDAO();
        final List<Agent> agents = agentDAO.dbList();

        if (agents.size() > 0) {
            adapter = new MissionAgentAdapter(this, agents);
            agentsList.setAdapter(adapter);
        }

        // Cancel Button
        Button cancel = (Button) findViewById(R.id.mission_button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Save Button
        Button save = (Button) findViewById(R.id.mission_button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMission();
            }
        });
    }

    private void saveMission() {

        Mission mission = helper.helperMission();
        MissionDAO dao = new MissionDAO();
        long idMission = dao.dbInsert(mission);

        MissionAgentDAO missionAgent = new MissionAgentDAO();
        List<Agent> agents = adapter.agentsSelected();

        for (Agent agent:agents) {
            MissionAgent missAgent = new MissionAgent();
            missAgent.setMissionId(idMission);
            missAgent.setAgentId(agent.getId());

            missionAgent.dbInsert(missAgent);
        }

        Toast.makeText(MissionFormActivity.this, "Mission " + mission.getName() + " was saved!", Toast.LENGTH_SHORT).show();
        finish();

    }
}
