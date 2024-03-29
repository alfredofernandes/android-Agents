package com.example.alfredorfernandes.agents.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.BuildConfig;
import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.adapter.AgencyAdapter;
import com.example.alfredorfernandes.agents.dao.AgencyDAO;
import com.example.alfredorfernandes.agents.dao.AgentDAO;
import com.example.alfredorfernandes.agents.helper.AgentHelper;
import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;

import java.io.File;
import java.util.List;

public class CreateAgentActivity extends AppCompatActivity {

    private AgentHelper helper;
    private ArrayAdapter<CharSequence> adapter;
    private AgencyAdapter adapterAgency;

    private ImageButton photo;
    private String dirAppPhoto;

    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_agent);

        setTitle("CREATE AGENT");

        helper = new AgentHelper(this);

        photo = (ImageButton) findViewById(R.id.create_agent_photo);
        Spinner level = (Spinner) findViewById(R.id.create_agent_level);
        Spinner agency = (Spinner) findViewById(R.id.create_agent_agency);
        Spinner country = (Spinner) findViewById(R.id.create_agent_country);
        Button cancel = (Button) findViewById(R.id.create_agent_cancel);
        Button save = (Button) findViewById(R.id.create_agent_save);

        // Photo Image Button
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                dirAppPhoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File filePhoto = new File(dirAppPhoto);

                if (android.os.Build.VERSION.SDK_INT >= 24){
                    Uri photoURI = FileProvider.getUriForFile(CreateAgentActivity.this,
                            BuildConfig.APPLICATION_ID + ".provider",
                            filePhoto);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                } else{
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePhoto));
                }

                startActivityForResult(intentCamera, CAMERA_REQUEST);
            }
        });

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == CAMERA_REQUEST) {
                    helper.loadImage(dirAppPhoto);
                }
            }
        }
    }

    private List<Agency> getAgencies() {

        AgencyDAO dao = new AgencyDAO();
        return dao.dbList();

    }
}