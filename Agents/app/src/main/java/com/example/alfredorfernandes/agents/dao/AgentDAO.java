package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alfredorfernandes.agents.model.Agent;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliana on 2017-08-03.
 */

public class AgentDAO extends SQLiteOpenHelper {

    public AgentDAO(Context context) {

        super(context, "AgentsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Agent (id INTEGER PRIMARY KEY, agency_id INTEGER, name TEXT, country TEXT, phone TEXT, address TEXT, photo BLOB, username TEXT, password TEXT, level TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXIST Agent";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(Agent agent) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues agentData = new ContentValues();
        agentData.put("name", agent.getName());
        agentData.put("agency_id", agent.getAgencyId());
        agentData.put("country", agent.getCountry());
        agentData.put("phone", agent.getPhone());
        agentData.put("address", agent.getAddress());
        agentData.put("photo", getBytes(agent.getPhoto()));
        agentData.put("username", agent.getUsername());
        agentData.put("password", agent.getPassword());
        agentData.put("level", agent.getLevel().toString());

        db.insert("Agent", null, agentData);
    }

    public List<Agent> dbListAgents() {

        String sql = "SELECT * FROM Agent;";
        return dbSQLStatement(sql);
    }

    public List<Agent> dbSQLStatement(String sql) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Agent> agentsList = new ArrayList<>();

        while (c.moveToNext()) {

            Agent agent = new Agent();

            agent.setId(c.getLong(c.getColumnIndex("id")));
            agent.setName(c.getString(c.getColumnIndex("name")));
            agent.setAgencyId(c.getLong(c.getColumnIndex("agency_id")));
            agent.setCountry(c.getString(c.getColumnIndex("country")));
            agent.setPhone(c.getString(c.getColumnIndex("phone")));
            agent.setAddress(c.getString(c.getColumnIndex("address")));

            byte[] image = c.getBlob(c.getColumnIndex("photo"));
            agent.setPhoto(getImage(image));

            agent.setUsername(c.getString(c.getColumnIndex("username")));
            agent.setPassword(c.getString(c.getColumnIndex("password")));

            agentsList.add(agent);
        }

        c.close();

        return agentsList;

    }

    public Agent checkLogin(String username, String password) {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Agent WHERE username=? AND password=?";
        Cursor c = db.rawQuery(sql, new String[] { username, password });

        Agent agent = new Agent();

        if (c.moveToFirst()) {
            agent.setId(c.getLong(c.getColumnIndex("id")));
            agent.setName(c.getString(c.getColumnIndex("name")));
            agent.setAgencyId(c.getLong(c.getColumnIndex("agency_id")));
            agent.setCountry(c.getString(c.getColumnIndex("country")));
            agent.setPhone(c.getString(c.getColumnIndex("phone")));
            agent.setAddress(c.getString(c.getColumnIndex("address")));

            byte[] image = c.getBlob(c.getColumnIndex("photo"));
            agent.setPhoto(getImage(image));

            agent.setUsername(c.getString(c.getColumnIndex("username")));
            agent.setPassword(c.getString(c.getColumnIndex("password")));

            Agent.LevelStatus level = Agent.LevelStatus.valueOf(c.getString(c.getColumnIndex("level")));
            agent.setLevel(level);

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
