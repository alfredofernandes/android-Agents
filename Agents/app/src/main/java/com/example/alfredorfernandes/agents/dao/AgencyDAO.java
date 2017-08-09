package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alfredorfernandes.agents.model.Agency;

import java.util.ArrayList;
import java.util.List;

public class AgencyDAO {

    public static final String TABLE = "Agency";

    // Labels Table Columns names
    public static final String KEY_Id = "id";
    public static final String KEY_Name = "name";
    public static final String KEY_Website = "website";

    private AgencyDAO agencyDAO;

    public AgencyDAO(){
        agencyDAO = new AgencyDAO();
    }

    public static String createTable(){
        return "CREATE TABLE " + TABLE  + "("
                + KEY_Id  + " INTEGER PRIMARY KEY,"
                + KEY_Name + " TEXT, "
                + KEY_Website + " TEXT)";
    }

    public void dbInsert(Agency agency) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, agency.getName());
        values.put(KEY_Website, agency.getWebsite());

        // Inserting Row
        db.insert(TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void dbDelete( ) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        db.delete(TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Agency> dbList() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String sql = "SELECT * FROM " + TABLE;
        Cursor c = db.rawQuery(sql, null);

        List<Agency> agencyList = new ArrayList<>();

        while (c.moveToNext()) {
            Agency agency = new Agency();

            agency.setId(c.getLong(c.getColumnIndex(KEY_Id)));
            agency.setName(c.getString(c.getColumnIndex(KEY_Name)));
            agency.setWebsite(c.getString(c.getColumnIndex(KEY_Website)));

            agencyList.add(agency);
        }
        c.close();

        return agencyList;
    }

}