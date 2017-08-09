package com.example.alfredorfernandes.agents.model;

/**
 * Created by alfredorfernandes on 2017-08-03.
 */

public class MissionAgent {

    private Long id;
    private Long missionId;
    private Long agentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }
}
