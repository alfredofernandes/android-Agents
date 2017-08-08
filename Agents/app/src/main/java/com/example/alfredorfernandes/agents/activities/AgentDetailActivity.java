package com.example.alfredorfernandes.agents.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;

public class AgentDetailActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName, profileLevel, profileAgency, profileAgencyWeb, profileCountry, profilePhone, profileAddress;
    private Button buttonInfo, buttonSms, buttonMap, buttonCall, buttonPhoto, buttonWeb;

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
        buttonCall = (Button) findViewById(R.id.profile_btn_call);
        buttonMap = (Button) findViewById(R.id.profile_btn_map);
        buttonWeb = (Button) findViewById(R.id.profile_btn_web);
        buttonSms = (Button) findViewById(R.id.profile_btn_sms);
        buttonPhoto = (Button) findViewById(R.id.profile_btn_photo);

    }
}
