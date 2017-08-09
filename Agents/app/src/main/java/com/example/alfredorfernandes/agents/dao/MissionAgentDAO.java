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


    public ContentValues dbInsert(MissionAgent missionAgent) {

        ContentValues missionAgentData = new ContentValues();
        missionAgentData.put("mission_id", missionAgent.getMissionId());
        missionAgentData.put("agent_id", missionAgent.getAgentId());

        return missionAgentData;
    }

    /*public List<MissionAgent> dbListMissionsAgents(Cursor c) {
        String sql = "SELECT * FROM "+ tableName;
        return dbSQLStatement(Cursor c);
    }

    public List<MissionAgent> dbFindPerAgent(Agent agent) {
        String sql = "SELECT * FROM "+ tableName +" WHERE agent_id = " + agent.getId();
        return dbSQLStatement(sql);
    }

    public List<MissionAgent> dbFindPerMission(Mission mission) {
        String sql = "SELECT * FROM "+ tableName +" WHERE mission_id = " + mission.getId();
        return dbSQLStatement(sql);
    }
*/
    public List<MissionAgent> dbList(Cursor c) {
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
