package com.example.alfredorfernandes.agents.model;

import java.util.Date;

public class Mission {

    public enum MissionStatus {
        DONE, CANCELLED, ONGOING
    }

    private long id;
    private String name;
    private Date date;
    private MissionStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
