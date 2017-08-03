package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alfredorfernandes.agents.model.Agent;
import com.example.alfredorfernandes.agents.model.Mission;
import com.example.alfredorfernandes.agents.model.MissionAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredorfernandes on 2017-08-03.
 */

public class MissionAgentDAO extends SQLiteOpenHelper {

    public MissionAgentDAO(Context context) {
        super(context, "AgentsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE MissionAgent (" +
                "id INTEGER PRIMARY KEY, " +
                "mission_id INTEGER, " +
                "agent_id INTEGER)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS MissionAgent";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(MissionAgent missionAgent) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues missionAgentData = new ContentValues();
        missionAgentData.put("mission_id", missionAgent.getMissionId());
        missionAgentData.put("agent_id", missionAgent.getAgentId());

        db.insert("MissionAgent", null, missionAgentData);
    }

    public List<MissionAgent> dbListMissionsAgents() {
        String sql = "SELECT * FROM MissionAgent;";
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbFindPerAgent(Agent agent) {
        String sql = "SELECT * FROM MissionAgent WHERE agent_id = " + agent.getId();
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbFindPerMission(Mission mission) {
        String sql = "SELECT * FROM MissionAgent WHERE mission_id = " + mission.getId();
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbSQLStatement(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<MissionAgent> missionAgentList = new ArrayList<MissionAgent>();

        while (c.moveToNext()) {
            MissionAgent missionAgent = new MissionAgent();
            missionAgent.setId(c.getLong(c.getColumnIndex("id")));
            missionAgent.setMissionId(c.getLong(c.getColumnIndex("mission_id")));
            missionAgent.setAgentId(c.getLong(c.getColumnIndex("agend_id")));

            missionAgentList.add(missionAgent);
        }

        c.close();
        return missionAgentList;
    }
}
