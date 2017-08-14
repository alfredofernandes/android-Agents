package com.example.alfredorfernandes.agents.helper;


import android.widget.EditText;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.activities.MissionFormActivity;
import com.example.alfredorfernandes.agents.model.Mission;

import java.util.Date;

public class MissionHelper {

    private final EditText fieldName;
    private final EditText fieldDate;
    private final EditText fieldStatus;

    private Mission mission;

    public MissionHelper(MissionFormActivity activity) {

        fieldName = (EditText) activity.findViewById(R.id.form_mission_name);
        fieldDate = (EditText) activity.findViewById(R.id.form_mission_date);
        fieldStatus = (EditText) activity.findViewById(R.id.form_mission_status);

        mission = new Mission();
    }

    public Mission helperMission() {

        mission.setName(fieldName.getText().toString());
        mission.setDate(new Date(fieldDate.getText().toString()));
        mission.setStatus(fieldStatus.toString());

        return mission;
    }

    public void fillForms(Mission mission) {

        fieldName.setText(mission.getName());
        fieldDate.setText(mission.getDate().toString());
        fieldStatus.setText(mission.getStatus().toString());

        this.mission = mission;
    }
}
