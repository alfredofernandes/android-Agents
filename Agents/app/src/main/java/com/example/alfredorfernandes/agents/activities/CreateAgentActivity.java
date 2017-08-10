package com.example.alfredorfernandes.agents.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.adapter.AgencyAdapter;
import com.example.alfredorfernandes.agents.dao.AgencyDAO;
import com.example.alfredorfernandes.agents.dao.AgentDAO;
import com.example.alfredorfernandes.agents.helper.AgentHelper;
import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;

import java.util.List;

public class CreateAgentActivity extends AppCompatActivity {

    private AgentHelper helper;
    private ArrayAdapter<CharSequence> adapter;
    private AgencyAdapter adapterAgency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_agent);

        setTitle("CREATE AGENT");

        helper = new AgentHelper(this);

        Spinner level = (Spinner) findViewById(R.id.create_agent_level);
        Spinner agency = (Spinner) findViewById(R.id.create_agent_agency);
        Spinner country = (Spinner) findViewById(R.id.create_agent_country);
        Button cancel = (Button) findViewById(R.id.create_agent_cancel);
        Button save = (Button) findViewById(R.id.create_agent_save);

        // Agent Level Spinner
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_agent_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(adapter);

        // Agency Spinner
        List<Agency> agencies = getAgencies();
        adapterAgency = new AgencyAdapter(this, android.R.layout.simple_spinner_item, agencies);
        agency.setAdapter(adapterAgency);

        // Agent Country Spinner
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_agent_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter);

        // Cancel Button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Save Button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agent agent = helper.helperAgent();
                AgentDAO dao = new AgentDAO();
                dao.dbInsert(agent);
                Toast.makeText(CreateAgentActivity.this, "Agent " + agent.getName() + " was saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private List<Agency> getAgencies() {

        AgencyDAO dao = new AgencyDAO();
        return dao.dbList();

    }
}