package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        setTitle("SYSTEM AGENTS");

        // Inserting sample data
        insertSampleData();

        EditText loginField = (EditText) findViewById(R.id.login_field);
        EditText passwordField = (EditText) findViewById(R.id.password_field);

        // Login Button
        final Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }

    public void loginUser() {

        EditText loginField = (EditText) findViewById(R.id.login_field);
        EditText passwordField = (EditText) findViewById(R.id.password_field);

        AgentDAO dao = new AgentDAO();
        Agent agent = dao.checkLogin(loginField.getText().toString(), passwordField.getText().toString());

        if (agent.getId() != null) {

            Intent goAgentList = new Intent(LoginActivity.this, MenuActivity.class);
            goAgentList.putExtra("username", agent.getUsername());
            goAgentList.putExtra("logged", true);
            startActivity(goAgentList);

        } else {
            Toast.makeText(LoginActivity.this, "User not registered!", Toast.LENGTH_SHORT).show();
        }
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