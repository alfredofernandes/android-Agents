package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;

public class CreateAgentActivity extends AppCompatActivity {

    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_agent);

        EditText name = (EditText) findViewById(R.id.create_agent_name);
        Spinner level = (Spinner) findViewById(R.id.create_agent_level);
        Spinner agency = (Spinner) findViewById(R.id.create_agent_agency);
        Spinner country = (Spinner) findViewById(R.id.create_agent_country);
        EditText phoneNumber = (EditText) findViewById(R.id.create_agent_phone_number);
        EditText address = (EditText) findViewById(R.id.create_agent_address);
        Button cancel = (Button) findViewById(R.id.create_agent_cancel);
        Button save = (Button) findViewById(R.id.create_agent_save);

        // Agent Level Spinner
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_agent_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(adapter);

        // Load Agency from database
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_agent_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agency.setAdapter(adapter);

        // Agent Country Spinner
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_agent_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agency.setAdapter(adapter);


        // Getting the user's input
//        String agentName = name.getText().toString();
//        String agentAddress = address.getText().toString();
//        String agentPhoneNumber = phoneNumber.getText().toString();
//        String agentLevel = level.getSelectedItem().toString();
//        String agentAgency = agency.getSelectedItem().toString();
//        String agentCountry = country.getSelectedItem().toString();

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
                Toast.makeText(CreateAgentActivity.this, "Save Button was clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}