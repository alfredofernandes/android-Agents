package com.example.alfredorfernandes.agents.model;

import java.util.Date;

public class Mission {

    public enum MissionStatus {
        DONE, CANCELLED, ONGOING
    }

    private String missionName;
    private Date date;
    private MissionStatus status;

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }
}
