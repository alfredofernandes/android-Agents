package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alfredorfernandes.agents.model.MissionAgent;

import java.util.ArrayList;
import java.util.List;


public class MissionAgentDAO {

    public static final String TABLE = "MissionAgent";

    // Labels Table Columns names
    public static final String KEY_Id = "id";
    public static final String KEY_MissionId = "missionId";
    public static final String KEY_AgentId = "agentId";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE  + "("
                + KEY_Id  + " INTEGER PRIMARY KEY, "
                + KEY_MissionId + " INTEGER, "
                + KEY_AgentId + " INTEGER)";

    public void dbInsert(MissionAgent missionAgent) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MissionId, missionAgent.getMissionId());
        values.put(KEY_AgentId, missionAgent.getAgentId());

        // Inserting Row
        db.insert(TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void dbDelete() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        db.delete(TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void dbDeleteId(MissionAgent missionAgent) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String[] param = {missionAgent.getId().toString()};
        db.delete(TABLE, "id = ?", param);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<MissionAgent> dbList() {
        String sql = "SELECT * FROM "+ TABLE;
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbFindPerAgent(String agentID) {
        String sql = "SELECT * FROM "+ TABLE +" WHERE "+KEY_AgentId+" = " + agentID;
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbFindPerMission(String missionID) {
        String sql = "SELECT * FROM "+ TABLE +" WHERE "+KEY_MissionId+" = " + missionID;
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbSQLStatement(String sql) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<MissionAgent> missionAgentList = new ArrayList<MissionAgent>();

        while (c.moveToNext()) {
            MissionAgent missionAgent = new MissionAgent();
            missionAgent.setId(c.getLong(c.getColumnIndex(KEY_Id)));
            missionAgent.setMissionId(c.getLong(c.getColumnIndex(KEY_MissionId)));
            missionAgent.setAgentId(c.getLong(c.getColumnIndex(KEY_AgentId)));

            missionAgentList.add(missionAgent);
        }

        c.close();
        return missionAgentList;
    }
}
