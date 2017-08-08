package com.example.alfredorfernandes.agents.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alfredorfernandes.agents.model.Agent;
import com.example.alfredorfernandes.agents.model.Mission;
import com.example.alfredorfernandes.agents.model.MissionAgent;

import java.util.ArrayList;
import java.util.List;


public class MissionAgentDAO {

    private SQLiteDatabase dataBase;
    private String tableName;

    public MissionAgentDAO(SQLiteDatabase db, String table) {
        this.dataBase = db;
        this.tableName = table;
    }

    public void dbInsert(MissionAgent missionAgent) {

        ContentValues missionAgentData = new ContentValues();
        missionAgentData.put("mission_id", missionAgent.getMissionId());
        missionAgentData.put("agent_id", missionAgent.getAgentId());

        dataBase.insert(tableName, null, missionAgentData);
    }

    public List<MissionAgent> dbListMissionsAgents() {
        String sql = "SELECT * FROM "+ tableName;
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbFindPerAgent(Agent agent) {
        String sql = "SELECT * FROM "+ tableName +" WHERE agent_id = " + agent.getId();
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbFindPerMission(Mission mission) {
        String sql = "SELECT * FROM "+ tableName +" WHERE mission_id = " + mission.getId();
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbSQLStatement(String sql) {
        Cursor c = dataBase.rawQuery(sql, null);

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
