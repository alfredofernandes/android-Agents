package com.example.alfredorfernandes.agents.helper;


import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.activities.MissionFormActivity;
import com.example.alfredorfernandes.agents.model.Mission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MissionHelper {

    private final EditText fieldName;
    private final EditText fieldDate;
    private final Spinner fieldStatus;

    private Mission mission;

    public MissionHelper(MissionFormActivity activity) {

        fieldName = (EditText) activity.findViewById(R.id.form_mission_name);
        fieldDate = (EditText) activity.findViewById(R.id.form_mission_date);
        fieldStatus = (Spinner) activity.findViewById(R.id.form_mission_status);

        // Status Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.spinner_mission_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldStatus.setAdapter(adapter);

        mission = new Mission();
    }

    public Mission helperMission() {

        mission.setName(fieldName.getText().toString());

        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = simpleDate.parse(fieldDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mission.setDate(date);
        mission.setStatus(fieldStatus.getSelectedItem().toString());

        return mission;
    }

}
