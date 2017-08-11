package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alfredorfernandes.agents.activities.LoginActivity;
import com.example.alfredorfernandes.agents.model.Agent;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO {

    public static final String TABLE = "Agent";

    // Labels Table Columns names
    public static final String KEY_Id = "id";
    public static final String KEY_Name = "name";
    public static final String KEY_AgencyId = "agencyId";
    public static final String KEY_Country = "country";
    public static final String KEY_Phone = "phone";
    public static final String KEY_Address = "address";
    //public static final String KEY_Photo = "photo";
    public static final String KEY_Username = "username";
    public static final String KEY_Password = "password";
    public static final String KEY_Level = "level";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE  + "("
                + KEY_Id  + " INTEGER PRIMARY KEY, "
                + KEY_AgencyId  + " INTEGER,"
                + KEY_Name + " TEXT, "
                + KEY_Country + " TEXT, "
                + KEY_Phone + " TEXT, "
                + KEY_Address + " TEXT, "
                //+ KEY_Photo + " BLOB, "
                + KEY_Username + " TEXT, "
                + KEY_Password + " TEXT, "
                + KEY_Level + " TEXT)";


    public void dbInsert(Agent agent) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, agent.getName());
        values.put(KEY_AgencyId, agent.getAgencyId());
        values.put(KEY_Country, agent.getCountry());
        values.put(KEY_Phone, agent.getPhone());
        values.put(KEY_Address, agent.getAddress());

        /*if (agent.getPhoto() != null) {
            values.put(KEY_Photo, getBytes(agent.getPhoto()));
        }*/

        values.put(KEY_Username, agent.getUsername());
        values.put(KEY_Password, agent.getPassword());
        values.put(KEY_Level, agent.getLevel());

        // Inserting Row
        db.insert(TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void dbDelete() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        db.delete(TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void dbDeleteId(Agent agent) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String[] param = {agent.getId().toString()};
        db.delete(TABLE, "id = ?", param);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Agent> dbList() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String sql = "SELECT * FROM " + TABLE;
        Cursor c = db.rawQuery(sql, null);

        List<Agent> agentsList = new ArrayList<>();

        while (c.moveToNext()) {

            Agent agent = getObject(c);
            agentsList.add(agent);
        }

        c.close();

        return agentsList;

    }

    public Agent checkLogin(String username, String password) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String sql = "SELECT * FROM "+ TABLE +" WHERE "+KEY_Username+"=? AND "+KEY_Password+"=?";
        Cursor c = db.rawQuery(sql, new String[]{username, password});

        Agent agent = new Agent();

        if (c.moveToFirst()) {
            agent = getObject(c);
        }
        c.close();

        return agent;
    }

    private Agent getObject(Cursor c) {

        Agent agent = new Agent();

        agent.setId(c.getLong(c.getColumnIndex(KEY_Id )));
        agent.setName(c.getString(c.getColumnIndex(KEY_Name)));
        agent.setAgencyId(c.getLong(c.getColumnIndex(KEY_AgencyId)));
        agent.setCountry(c.getString(c.getColumnIndex(KEY_Country)));
        agent.setPhone(c.getString(c.getColumnIndex(KEY_Phone)));
        agent.setAddress(c.getString(c.getColumnIndex(KEY_Address)));
        agent.setLevel(c.getString(c.getColumnIndex(KEY_Level)));

        /*byte[] image = c.getBlob(c.getColumnIndex(KEY_Phone));
        if (image != null) {
            agent.setPhoto(getImage(image));
        }*/

        agent.setUsername(c.getString(c.getColumnIndex(KEY_Username)));
        agent.setPassword(c.getString(c.getColumnIndex(KEY_Password)));

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
