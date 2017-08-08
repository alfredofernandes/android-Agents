package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alfredorfernandes.agents.model.Agency;

import java.util.ArrayList;
import java.util.List;

public class AgencyDAO {

    private SQLiteDatabase dataBase;
    private String tableName;

    public AgencyDAO(SQLiteDatabase db, String table) {
        this.dataBase = db;
        this.tableName = table;
    }

    public void dbInsert(Agency agency) {
        ContentValues agencyData = new ContentValues();

        agencyData.put("name", agency.getName());
        agencyData.put("website", agency.getWebsite());

        dataBase.insert(tableName, null, agencyData);
    }

    public List<Agency> dbListAgencies() {

        String sql = "SELECT * FROM "+ tableName;
        Cursor c = dataBase.rawQuery(sql, null);

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