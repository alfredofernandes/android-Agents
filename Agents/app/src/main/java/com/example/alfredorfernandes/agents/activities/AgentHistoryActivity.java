package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.MissionAgentDAO;
import com.example.alfredorfernandes.agents.dao.MissionDAO;
import com.example.alfredorfernandes.agents.model.Mission;
import com.example.alfredorfernandes.agents.model.MissionAgent;

import java.util.ArrayList;
import java.util.List;

public class AgentHistoryActivity extends AppCompatActivity {

    private ListView missionsList;
    private String currentAgentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_history);

        setTitle("AGENTS HISTORY");

        Intent intent = getIntent();
        currentAgentID =  intent.getStringExtra("agent");
        Log.d("ID", currentAgentID);

        // History List
        missionsList = (ListView) findViewById(R.id.agent_mission_list);
        loadDataList();

        // Mission List Back
        Button returnButton = (Button) findViewById(R.id.agent_mission_button_back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadDataList() {

        MissionAgentDAO missAgentDAO = new MissionAgentDAO();
        List<MissionAgent> listMissionAgent = missAgentDAO.dbFindPerAgent(currentAgentID);
        Log.d("ID", currentAgentID);

        MissionDAO missionDAO = new MissionDAO();
        final List<Mission> missions = new ArrayList<>();

        for (MissionAgent missAgent:listMissionAgent) {
            Mission m = missionDAO.dbSearchById(missAgent.getMissionId().toString());
            missions.add(m);
        }

        Log.d("MISSIONS", "" + missions.size());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, missions) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                Mission mission = missions.get(position);

                text1.setText(mission.getName().toString());
                text2.setText("Date: " + mission.getDateString() + " | Status: " + mission.getStatus());

                return view;
            }
        };

        missionsList.setAdapter(adapter);
    }
}
