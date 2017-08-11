package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alfredorfernandes.agents.model.Mission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MissionDAO {

    public static final String TABLE = "Mission";

    // Labels Table Columns names
    public static final String KEY_Id = "id";
    public static final String KEY_Name = "name";
    public static final String KEY_Date = "date";
    public static final String KEY_Status = "status";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE  + "("
                + KEY_Id  + " INTEGER PRIMARY KEY, "
                + KEY_Name + " TEXT, "
                + KEY_Date + " LONG, "
                + KEY_Status + " TEXT)";

    public void dbInsert(Mission mission) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, mission.getName());
        values.put(KEY_Date, persistDate(mission.getDate()));
        values.put(KEY_Status, mission.getStatus().toString());

        // Inserting Row
        db.insert(TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void dbDelete() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        db.delete(TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void dbDeleteId(Mission mission) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String[] param = {mission.getId().toString()};
        db.delete(TABLE, "id = ?", param);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Mission> dbList() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String sql = "SELECT * FROM " + TABLE;
        Cursor c = db.rawQuery(sql, null);

        List<Mission> missionList = new ArrayList<Mission>();

        while (c.moveToNext()) {
            Mission.MissionStatus status = Mission.MissionStatus.valueOf(c.getString(c.getColumnIndex(KEY_Status)));

            Mission mission = new Mission();
            mission.setId(c.getLong(c.getColumnIndex(KEY_Id)));
            mission.setName(c.getString(c.getColumnIndex(KEY_Name)));
            mission.setDate(loadDate(c, c.getColumnIndex(KEY_Date)));
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
