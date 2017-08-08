package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alfredorfernandes.agents.model.Mission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MissionDAO {

    private SQLiteDatabase dataBase;
    private String tableName;

    public MissionDAO(SQLiteDatabase db, String table) {
        this.dataBase = db;
        this.tableName = table;
    }

    public void dbInsert(Mission mission) {

        ContentValues missionData = new ContentValues();
        missionData.put("name", mission.getName());
        missionData.put("date", persistDate(mission.getDate()));
        missionData.put("status", mission.getStatus().toString());

        dataBase.insert(tableName, null, missionData);
    }

    public List<Mission> dbListMissions() {
        String sql = "SELECT * FROM "+ tableName;
        return dbSQLStatement(sql);
    }

    public List<Mission> dbSQLStatement(String sql) {

        Cursor c = dataBase.rawQuery(sql, null);

        List<Mission> missionList = new ArrayList<Mission>();

        while (c.moveToNext()) {
            Mission.MissionStatus status = Mission.MissionStatus.valueOf(c.getString(c.getColumnIndex("status")));

            Mission mission = new Mission();
            mission.setId(c.getLong(c.getColumnIndex("id")));
            mission.setName(c.getString(c.getColumnIndex("name")));
            mission.setDate(loadDate(c, c.getColumnIndex("date")));
            mission.setStatus(status);

            missionList.add(mission);
        }

        c.close();
        return missionList;
    }

    public static Long persistDate(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    public static Date loadDate(Cursor cursor, int index) {
        if (cursor.isNull(index)) {
            return null;
        }
        return new Date(cursor.getLong(index));
    }

}
