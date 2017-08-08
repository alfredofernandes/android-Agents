package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;
import com.example.alfredorfernandes.agents.model.Mission;
import com.example.alfredorfernandes.agents.model.MissionAgent;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AgentsDB";

    // Table Names
    private static final String TABLE_AGENCY = "agency";
    private static final String TABLE_AGENT = "agent";
    private static final String TABLE_MISSION = "mission";
    private static final String TABLE_MISSION_AGENT = "mission_agent";

    // Table Create Statements
    // Agency table create statement
    private static final String CREATE_TABLE_AGENCY = "CREATE TABLE "
            + TABLE_AGENCY + "(id INTEGER PRIMARY KEY, name TEXT, website TEXT)";

    // Agent table create statement
    private static final String CREATE_TABLE_AGENT = "CREATE TABLE "
            + TABLE_AGENT + "(id INTEGER PRIMARY KEY, agency_id INTEGER, name TEXT,"
            +" country TEXT, phone TEXT, address TEXT, photo BLOB,"
            +" username TEXT, password TEXT, level TEXT)";

    // Mission table create statement
    private static final String CREATE_TABLE_MISSION = "CREATE TABLE "
            + TABLE_MISSION + "(id INTEGER PRIMARY KEY,"
            + " name TEXT NOT NULL, date LONG, status TEXT)";

    // Mission_Agent table create statement
    private static final String CREATE_TABLE_MISSION_AGENT = "CREATE TABLE "
            + TABLE_MISSION_AGENT + "(id INTEGER PRIMARY KEY,"
            + "mission_id INTEGER, agent_id INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_AGENCY);
        db.execSQL(CREATE_TABLE_AGENT);
        db.execSQL(CREATE_TABLE_MISSION);
        db.execSQL(CREATE_TABLE_MISSION_AGENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENCY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MISSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MISSION_AGENT);

        // create new tables
        onCreate(db);
    }

    // TABLE AGENCY
    public void dbInsertAgency(Agency agency) {

        SQLiteDatabase db = getReadableDatabase();

        ContentValues agencyData = new ContentValues();

        agencyData.put("name", agency.getName());
        agencyData.put("website", agency.getWebsite());

        db.insert(TABLE_AGENCY, null, agencyData);
    }

    // TABLE AGENT
    public void dbInsertAgent(Agent agent) {

        SQLiteDatabase db = getReadableDatabase();

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

        db.insert(TABLE_AGENT, null, agentData);
    }

    public List<Agent> dbListAgents() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+ TABLE_AGENT;

        Cursor c = db.rawQuery(sql, null);

        List<Agent> agentsList = new ArrayList<>();

        Log.i("LIST AGENTS", "" +c.getCount());

        while (c.moveToNext()) {

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

    public Agent checkLogin(String username, String password) {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+ TABLE_AGENT +" WHERE username=? AND password=?";
        Cursor c = db.rawQuery(sql, new String[]{username, password});

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
