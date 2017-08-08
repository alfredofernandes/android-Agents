package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.alfredorfernandes.agents.model.Agency;
import java.util.ArrayList;
import java.util.List;

public class AgencyDAO extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Agency (id INTEGER PRIMARY KEY, name TEXT, website TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Agency";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void dbInsert(Agency agency) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues agencyData = new ContentValues();

        agencyData.put("name", agency.getName());
        agencyData.put("website", agency.getWebsite());

        db.insert("Agency", null, agencyData);
    }

    public List<Agency> dbListAgencies() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Agency";
        Cursor c = db.rawQuery(sql, null);

        List<Agency> agencyList = new ArrayList<>();

        while (c.moveToNext()) {
            Agency agency = new Agency();

            agency.setId(c.getLong(c.getColumnIndex("id")));
            agency.setName(c.getString(c.getColumnIndex("name")));
            agency.setWebsite(c.getString(c.getColumnIndex("website")));

            agencyList.add(agency);
        }
        c.close();

        return agencyList;
    }

}