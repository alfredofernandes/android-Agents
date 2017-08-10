package com.example.alfredorfernandes.agents.helper;

import android.widget.EditText;
import android.widget.Spinner;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.activities.CreateAgentActivity;
import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;

public class AgentHelper {

    private final EditText fieldName;
    private final Spinner fieldLevel;
    private final Spinner fieldAgency;
    private final Spinner fieldCountry;
    private final EditText fieldPhoneNumber;
    private final EditText fieldAddress;
    private final EditText fieldUsername;
    private final EditText fieldPassword;

    public AgentHelper (CreateAgentActivity activity) {

        fieldName = (EditText) activity.findViewById(R.id.create_agent_name);
        fieldLevel = (Spinner) activity.findViewById(R.id.create_agent_level);
        fieldAgency = (Spinner) activity.findViewById(R.id.create_agent_agency);
        fieldCountry = (Spinner) activity.findViewById(R.id.create_agent_country);
        fieldPhoneNumber = (EditText) activity.findViewById(R.id.create_agent_phone_number);
        fieldAddress = (EditText) activity.findViewById(R.id.create_agent_address);
        fieldUsername = (EditText) activity.findViewById(R.id.create_agent_username);
        fieldPassword = (EditText) activity.findViewById(R.id.create_agent_password);
    }

    public Agent helperAgent() {

        Agent agent = new Agent();

        agent.setName(fieldName.getText().toString());
        agent.setLevel(fieldLevel.getSelectedItem().toString());

        Agency agency = (Agency) fieldAgency.getSelectedItem();
        agent.setAgencyId(agency.getId());

        agent.setCountry(fieldCountry.getSelectedItem().toString());
        agent.setPhone(fieldPhoneNumber.getText().toString());
        agent.setAddress(fieldAddress.getText().toString());
        agent.setUsername(fieldUsername.getText().toString());
        agent.setPassword(fieldPassword.getText().toString());

        return agent;
    }
}
