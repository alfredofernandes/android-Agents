package com.example.alfredorfernandes.agents.model;

/**
 * Created by juliana on 2017-08-01.
 */

public class Agency {

    private String name;
    private String website;

    public Agency(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
