package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.AgencyDAO;
import com.example.alfredorfernandes.agents.dao.AgentDAO;
import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText loginField = (EditText) findViewById(R.id.login_field);
        EditText passwordField = (EditText) findViewById(R.id.password_field);
        Button loginButton = (Button) findViewById(R.id.login_button);

        insertSampleData();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMenuActivity = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(goToMenuActivity);
                finish();
            }
        });

    }

    private void insertSampleData() {

        AgencyDAO agencyDAO = new AgencyDAO();
        AgentDAO agentDAO = new AgentDAO();

        //Clear/Remove tables
        agencyDAO.dbDelete();
        agentDAO.dbDelete();

        Agency agency = new Agency();

        agency.setName("BAGG");
        agency.setWebsite("http://www.bagg.com/");
        agencyDAO.dbInsert(agency);

        agency.setName("ADECCO");
        agency.setWebsite("http://www.adecco.ca/en");
        agencyDAO.dbInsert(agency);

        agency.setName("RANDSTAD");
        agency.setWebsite("https://www.randstad.ca/");
        agencyDAO.dbInsert(agency);

        Agent agent = new Agent();
        agent.setName("Juliana Lacerda");
        agent.setUsername("juliana");
        agent.setPassword("123");
        agent.setAgencyId((long)1);
        agent.setAddress("160 Erskine Ave");
        agent.setCountry("Brazil");
        agent.setLevel("002");
        agent.setPhone("(647) 9388639");
        //agent.setPhoto(null);

        agentDAO.dbInsert(agent);

    }
}