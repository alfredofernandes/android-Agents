package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.MissionDAO;
import com.example.alfredorfernandes.agents.helper.MissionHelper;
import com.example.alfredorfernandes.agents.model.Mission;

public class MissionFormActivity extends AppCompatActivity {

    private MissionHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_form);

        setTitle("MISSION DETAIL");

        helper = new MissionHelper(this);

        Intent intent = getIntent();
        Mission mission = (Mission) intent.getSerializableExtra("mission");

        if (mission != null) {
            helper.fillForms(mission);
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
                Mission mission = helper.helperMission();
                MissionDAO dao = new MissionDAO();
                dao.dbInsert(mission);
                Toast.makeText(MissionFormActivity.this, "Mission " + mission.getName() + " was saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
