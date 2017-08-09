package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alfredorfernandes.agents.model.Agent;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO {

    public ContentValues dbInsert(Agent agent) {

        ContentValues agentData = new ContentValues();
        agentData.put("name", agent.getName());
        agentData.put("agency_id", agent.getAgencyId());
        agentData.put("country", agent.getCountry());
        agentData.put("phone", agent.getPhone());
        agentData.put("address", agent.getAddress());

        if (agent.getPhoto() != null) {
            agentData.put("photo", getBytes(agent.getPhoto()));
        }

        agentData.put("username", agent.getUsername());
        agentData.put("password", agent.getPassword());
        agentData.put("level", agent.getLevel());

        return agentData;
    }

    public List<Agent> dbList(Cursor c) {

        List<Agent> agentsList = new ArrayList<>();

        while (c.moveToFirst()) {

            Agent agent = new Agent();

            agent.setId(c.getLong(c.getColumnIndex("id")));
            agent.setName(c.getString(c.getColumnIndex("name")));
            agent.setAgencyId(c.getLong(c.getColumnIndex("agency_id")));
            agent.setCountry(c.getString(c.getColumnIndex("country")));
            agent.setPhone(c.getString(c.getColumnIndex("phone")));
            agent.setAddress(c.getString(c.getColumnIndex("address")));
            agent.setLevel(c.getString(c.getColumnIndex("level")));

            byte[] image = c.getBlob(c.getColumnIndex("photo"));
            if (image != null) {
                agent.setPhoto(getImage(image));
            }

            agent.setUsername(c.getString(c.getColumnIndex("username")));
            agent.setPassword(c.getString(c.getColumnIndex("password")));

            agentsList.add(agent);
        }

        c.close();

        return agentsList;

    }

    public Agent checkLogin(Cursor c) {

        Agent agent = new Agent();

        if (c.moveToFirst()) {

            agent.setId(c.getLong(c.getColumnIndex("id")));
            agent.setName(c.getString(c.getColumnIndex("name")));
            agent.setAgencyId(c.getLong(c.getColumnIndex("agency_id")));
            agent.setCountry(c.getString(c.getColumnIndex("country")));
            agent.setPhone(c.getString(c.getColumnIndex("phone")));
            agent.setAddress(c.getString(c.getColumnIndex("address")));

            byte[] image = c.getBlob(c.getColumnIndex("photo"));
            if (image != null) {
                agent.setPhoto(getImage(image));
            }

            agent.setUsername(c.getString(c.getColumnIndex("username")));
            agent.setPassword(c.getString(c.getColumnIndex("password")));

            agent.setLevel(c.getString(c.getColumnIndex("level")));

        }
        c.close();

        return agent;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
