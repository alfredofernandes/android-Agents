package com.example.alfredorfernandes.agents.activities;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.BuildConfig;
import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.AgencyDAO;
import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;

import java.io.File;
import java.util.ArrayList;

public class AgentDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView profileImage;
    private TextView profileName, profileLevel, profileAgency, profileAgencyWeb, profileCountry, profilePhone, profileAddress;
    private Button buttonInfo, buttonBack;
    private Agent currentAgent;
    private Agency currentAgency;

    private static final int CAMERA_CODE = 990;
    private String dirAppPhoto;
    private ArrayList<String> photos = new ArrayList<>();

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

                openCamera();
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

        if (agent.getPhotoPath() != null) {
            profileImage.setImageBitmap(agent.getImagePhoto());
            profileImage.setScaleType((ImageView.ScaleType.FIT_XY));
        } else {
            profileImage.setImageResource(R.drawable.ic_add_photo);
        }

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

    private void openCamera() {

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        dirAppPhoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
        File filePhoto = new File(dirAppPhoto);

        if (android.os.Build.VERSION.SDK_INT >= 24){
            Uri photoURI = FileProvider.getUriForFile(AgentDetailActivity.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    filePhoto);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        } else{
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePhoto));
        }

        startActivityForResult(intentCamera, CAMERA_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_CODE) {
                photos.add(dirAppPhoto);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Would you like to take more photos?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                openCamera();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                Intent goAlbumList = new Intent(AgentDetailActivity.this, AgentAlbumActivity.class);
                                goAlbumList.putExtra("photos", photos);
                                goAlbumList.putExtra("phone", currentAgent.getPhone());
                                startActivity(goAlbumList);

                                Log.e("PHOTOS", ""+photos.size());
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.profile_btn_info:

                Intent goMissionList = new Intent(AgentDetailActivity.this, AgentHistoryActivity.class);
                goMissionList.putExtra("agent", currentAgent.getId().toString());
                startActivity(goMissionList);

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
