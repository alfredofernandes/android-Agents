package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.MissionDAO;
import com.example.alfredorfernandes.agents.model.Mission;

import java.util.ArrayList;
import java.util.List;

public class MissionListActivity extends AppCompatActivity {

    private ListView missionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_list);

        setTitle("MISSION LIST");

        // History List
        missionsList = (ListView) findViewById(R.id.mission_list);
        registerForContextMenu(missionsList);

        missionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Mission mission = (Mission) missionsList.getItemAtPosition(position);

                Intent intentGoToFrom = new Intent(MissionListActivity.this, MissionFormActivity.class);
                intentGoToFrom.putExtra("mission", String.valueOf(mission));
                startActivity(intentGoToFrom);
            }
        });

        // Mission List Back
        Button returnButton = (Button) findViewById(R.id.mission_button_back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataList();
    }

    private void loadDataList() {

        MissionDAO missionDAO = new MissionDAO();
        final List<Mission> missions = missionDAO.dbList();

        if (missions.size() > 0) {

            ListView missionList = (ListView) findViewById(R.id.mission_list);

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, missions) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    Mission mission = missions.get(position);

                    text1.setText(mission.getName()  + " (" + mission.getStatus().toString() + ")");
                    text2.setText(mission.getDate().toString());

                    return view;
                }
            };
            missionList.setAdapter(adapter);
        }
    }
}
