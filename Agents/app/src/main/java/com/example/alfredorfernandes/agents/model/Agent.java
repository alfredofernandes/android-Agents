package com.example.alfredorfernandes.agents.model;

import android.graphics.Bitmap;

import java.sql.Blob;
import java.util.ArrayList;

public class Agent
{
    public enum LevelStatus {
        L001, L002, L003, L004, L005, L006, L007
    }

    private String name;
    private Agency agency;
    private String country;
    private String phone;
    private String address;
    private Bitmap photo;
    private ArrayList<Mission> missions;
    private String username;
    private String password;
    private LevelStatus level;

    public LevelStatus getLevel() {
        return level;
    }

    public void setLevel(LevelStatus level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public ArrayList<Mission> getMissions() {
        return missions;
    }

    public void setMissions(ArrayList<Mission> missions) {
        this.missions = missions;
    }
}
