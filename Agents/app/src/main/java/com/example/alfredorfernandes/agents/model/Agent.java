package com.example.alfredorfernandes.agents.model;

import java.util.ArrayList;

public class Agent
{
    private String name;
    private int level;
    private Agency agency;
    private String country;
    private String phone;
    private String address;
    private ArrayList<Mission> missions;

    public Agent(String name, int level, Agency agency, String country, String phone, String address, ArrayList<Mission> missions) {
        this.name = name;
        this.level = level;
        this.agency = agency;
        this.country = country;
        this.phone = phone;
        this.address = address;
        this.missions = missions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Mission> getMissions() {
        return missions;
    }

    public void setMissions(ArrayList<Mission> missions) {
        this.missions = missions;
    }
}
