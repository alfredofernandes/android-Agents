package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alfredorfernandes.agents.model.Mission;
import com.example.alfredorfernandes.agents.model.MissionAgent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by juliana on 2017-08-03.
 */

public class MissionDAO extends SQLiteOpenHelper {

    public MissionDAO(Context context) {
        super(context, "AgentsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Missions (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "date LONG, " +
                "status TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Missions";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(Mission mission) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues missionData = new ContentValues();
        missionData.put("name", mission.getName());
        missionData.put("date", persistDate(mission.getDate()));
        missionData.put("status", mission.getStatus().toString());

        db.insert("Missions", null, missionData);
    }

    public List<Mission> dbListMissionsAgents() {
        String sql = "SELECT * FROM Missions;";
        return dbSQLStatement(sql);
    }

    public List<Mission> dbSQLStatement(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

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
