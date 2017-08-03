package com.example.alfredorfernandes.agents.model;

/**
 * Created by alfredorfernandes on 2017-08-03.
 */

public class MissionAgent {

    private long id;
    private long missionId;
    private long agentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMissionId() {
        return missionId;
    }

    public void setMissionId(long missionId) {
        this.missionId = missionId;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }
}
