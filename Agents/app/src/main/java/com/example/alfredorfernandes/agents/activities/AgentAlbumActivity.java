package com.example.alfredorfernandes.agents.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfredorfernandes.agents.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AgentAlbumActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter;

    private int count;
    private ArrayList<String> photos = new ArrayList<>();
    private boolean[] photosselection;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_album);

        setTitle("ALBUM");

        Intent intent = getIntent();
        photos = intent.getStringArrayListExtra("photos");
        phoneNumber = intent.getStringExtra("phone");

        if (photos != null) {
            count = photos.size();
            photosselection = new boolean[count];
        }

        GridView imagegrid = (GridView) findViewById(R.id.grid_images);
        imageAdapter = new ImageAdapter();
        imagegrid.setAdapter(imageAdapter);

        Button btnBack = (Button) findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnSendSms = (Button) findViewById(R.id.button_sms);
        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });

    }

    private void sendSMS() {

        int cnt = 0;
        ArrayList<Uri> uris = new ArrayList<Uri>();
        String namesPhotos = "";

        for (int i=0; i < photosselection.length; i++)
        {
            if (photosselection[i]){
                cnt++;

                String path = photos.get(i);

                File fileIn = new File(path);
                Uri u = Uri.fromFile(fileIn);

                uris.add(u);
            }
        }

        if (cnt == 0){
            Toast.makeText(getApplicationContext(),
                    "Please select at least one image",
                    Toast.LENGTH_LONG).show();
        } else {

            if (uris.size() == 1) {

                Intent intentSMS = new Intent(Intent.ACTION_SEND);
                intentSMS.setData(Uri.parse("sms:" + phoneNumber));
                intentSMS.putExtra(Intent.EXTRA_STREAM, uris.get(0));
                intentSMS.setType("image/*");

                startActivity(Intent.createChooser(intentSMS, "Send..."));

            } else if (uris.size() > 1){

                Intent intentSMS = new Intent(Intent.ACTION_SEND_MULTIPLE);
                intentSMS.setData(Uri.parse("sms:" + phoneNumber));
                intentSMS.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                intentSMS.setType("image/*");

                startActivity(Intent.createChooser(intentSMS, "Send..."));

            }

        }

    }

    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ImageAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.listitem_gallery, null);

                holder.imageview = (ImageView) convertView.findViewById(R.id.thumb_photo);
                holder.checkbox = (CheckBox) convertView.findViewById(R.id.item_check);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.checkbox.setId(position);
            holder.imageview.setId(position);

            holder.checkbox.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    CheckBox cb = (CheckBox) v;
                    int id = cb.getId();
                    if (photosselection[id]){
                        cb.setChecked(false);
                        photosselection[id] = false;
                    } else {
                        cb.setChecked(true);
                        photosselection[id] = true;
                    }
                }
            });

            String path = photos.get(position);

            Bitmap bitmap = BitmapFactory.decodeFile(path);
            Bitmap lowdefbitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

            holder.imageview.setImageBitmap(lowdefbitmap);
            holder.checkbox.setChecked(photosselection[position]);
            holder.id = position;

            return convertView;
        }
    }

    class ViewHolder {
        ImageView imageview;
        CheckBox checkbox;
        int id;
    }
}
