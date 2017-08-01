package com.example.alfredorfernandes.agents.model;

import java.util.Date;

/**
 * Created by juliana on 2017-08-01.
 */

public class Mission {

    public enum MissionStatus {
        DONE, CANCELLED, GOING
    }

    private String missionName;
    private Date date;
    private MissionStatus status;

    public Mission(String missionName, Date date, MissionStatus status) {
        this.missionName = missionName;
        this.date = date;
        this.status = status;
    }

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
