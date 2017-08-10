package com.example.alfredorfernandes.agents.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.AgencyDAO;
import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;

public class AgentDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView profileImage;
    private TextView profileName, profileLevel, profileAgency, profileAgencyWeb, profileCountry, profilePhone, profileAddress;
    private Button buttonInfo, buttonBack;
    private Agent currentAgent;
    private Agency currentAgency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_detail);

        setTitle("AGENT PROFILE");

        profileImage = (ImageView) findViewById(R.id.profile_image);
        profileName = (TextView) findViewById(R.id.profile_name);
        profileLevel = (TextView) findViewById(R.id.profile_level);
        profileAgency = (TextView) findViewById(R.id.profile_agency);
        profileAgencyWeb = (TextView) findViewById(R.id.profile_web_agency);
        profileCountry = (TextView) findViewById(R.id.profile_country);
        profilePhone = (TextView) findViewById(R.id.profile_phone);
        profileAddress = (TextView) findViewById(R.id.profile_address);

        buttonInfo = (Button) findViewById(R.id.profile_btn_info);
        buttonBack = (Button) findViewById(R.id.profile_btn_back);

        buttonInfo.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

        Intent intent = getIntent();
        currentAgent = (Agent) intent.getSerializableExtra("agent");

        if (currentAgent != null) {
            loadData(currentAgent);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agent, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_photo:
                Toast.makeText(this, "Photo button was clicked.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_sms:

                String sms = "sms:" + currentAgent.getPhone();
                openMenu(sms);
                return true;

            case R.id.menu_call:

                if (ActivityCompat.checkSelfPermission(AgentDetailActivity.this, Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(AgentDetailActivity.this, new String[] {Manifest.permission.CALL_PHONE}, 123);

                } else {

                    String call = "tel:" + currentAgent.getPhone();
                    openMenu(call);
                }

                return true;

            case R.id.menu_web:

                String site = currentAgency.getWebsite();
                if (!site.startsWith("http://")) { site = "http://" + site;}
                openMenu(site);
                return true;

            case R.id.menu_map:

                String map = "geo:0,0?q=" + currentAgent.getAddress();
                openMenu(map);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadData(Agent agent) {

        profileName.setText("Name: " + agent.getName().toString());
        profileLevel.setText("Level: " + agent.getLevel());
        profileAddress.setText("Address: " + agent.getAddress());
        profilePhone.setText("Phone: " + agent.getPhone());
        profileCountry.setText("Country: " + agent.getCountry());

        currentAgency = getAgency(agent.getAgencyId().toString());
        profileAgency.setText("Agency name: " +currentAgency.getName());
        profileAgencyWeb.setText("Agency website: " +currentAgency.getWebsite());

    }

    private Agency getAgency(String agencyId) {
        AgencyDAO dao = new AgencyDAO();
        return dao.dbFindAgency(agencyId);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.profile_btn_info:
                Toast.makeText(this, "MISSION button was clicked.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.profile_btn_back:
                finish();
                break;
        }
    }

    private void openMenu(String url) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        startActivity(intent);

    }
}
