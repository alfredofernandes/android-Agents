package com.example.alfredorfernandes.agents.model;

import java.util.Date;

public class Mission {

    public enum MissionStatus {
        DONE, CANCELLED, ONGOING
    }

    private Long id;
    private String name;
    private Date date;
    private MissionStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
