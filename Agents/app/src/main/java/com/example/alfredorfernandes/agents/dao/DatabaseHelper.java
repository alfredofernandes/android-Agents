package com.example.alfredorfernandes.agents.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;
import com.example.alfredorfernandes.agents.model.Mission;
import com.example.alfredorfernandes.agents.model.MissionAgent;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AgentsDB";

    // Table Names
    private static final String TABLE_AGENCY = "agency";
    private static final String TABLE_AGENT = "agent";
    private static final String TABLE_MISSION = "mission";
    private static final String TABLE_MISSION_AGENT = "mission_agent";

    // Table Create Statements
    // Agency table create statement
    private static final String CREATE_TABLE_AGENCY = "CREATE TABLE "
            + TABLE_AGENCY + "(id INTEGER PRIMARY KEY, name TEXT, website TEXT)";

    // Agent table create statement
    private static final String CREATE_TABLE_AGENT = "CREATE TABLE "
            + TABLE_AGENT+ "(id INTEGER PRIMARY KEY, agency_id INTEGER, name TEXT,"
            +" country TEXT, phone TEXT, address TEXT, photo BLOB,"
            +" username TEXT, password TEXT, level TEXT)";

    // Mission table create statement
    private static final String CREATE_TABLE_MISSION = "CREATE TABLE "
            + TABLE_MISSION + "(id INTEGER PRIMARY KEY,"
            + " name TEXT NOT NULL, date LONG, status TEXT)";

    // Mission_Agent table create statement
    private static final String CREATE_TABLE_MISSION_AGENT = "CREATE TABLE "
            + TABLE_MISSION_AGENT + "(id INTEGER PRIMARY KEY,"
            + "mission_id INTEGER, agent_id INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Classes tables
    private AgencyDAO agencyDAO;
    private AgentDAO agentDAO;
    private MissionDAO missionDAO;
    private MissionAgentDAO missionAgentDAO;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_AGENCY);
        db.execSQL(CREATE_TABLE_AGENT);
        db.execSQL(CREATE_TABLE_MISSION);
        db.execSQL(CREATE_TABLE_MISSION_AGENT);

        agencyDAO = new AgencyDAO(db, TABLE_AGENCY);
        agentDAO = new AgentDAO(db, TABLE_AGENT);
        missionDAO = new MissionDAO(db, TABLE_MISSION);
        missionAgentDAO = new MissionAgentDAO(db, TABLE_MISSION_AGENT);

        createAgencies();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_AGENCY);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_AGENT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_MISSION);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_MISSION_AGENT);

        // create new tables
        onCreate(db);
    }

    private void createAgencies() {

        Agency agency1 = new Agency();
        agency1.setName("BAGG");
        agency1.setWebsite("http://www.bagg.com/");

        Agency agency2 = new Agency();
        agency2.setName("ADECCO");
        agency2.setWebsite("http://www.adecco.ca/en");

        Agency agency3 = new Agency();
        agency3.setName("RANDSTAD");
        agency3.setWebsite("https://www.randstad.ca/");

        agencyDAO.dbInsert(agency1);
        agencyDAO.dbInsert(agency2);
        agencyDAO.dbInsert(agency3);
    }

    // TABLE AGENCY
    public List<Agency> dbListAgencies() {
        return agencyDAO.dbListAgencies();
    }

    // TABLE AGENT
    public void dbInsert(Agent agent) {
        agentDAO.dbInsert(agent);
    }

    public List<Agent> dbListAgents() {
        return agentDAO.dbListAgents();
    }

    public Agent checkLogin(String username, String password) {
        return agentDAO.checkLogin(username, password);
    }

    // TABLE MISSION
    public void dbInsert(Mission mission) {
        missionDAO.dbInsert(mission);
    }

    public List<Mission> dbListMissions() {
        return missionDAO.dbListMissions();
    }

    // TABLE MISSION AGENT
    public void dbInsert(MissionAgent missionAgent) {
        missionAgentDAO.dbInsert(missionAgent);
    }

    public List<MissionAgent> dbListMissionsAgents() {
        return missionAgentDAO.dbListMissionsAgents();
    }

    public List<MissionAgent> dbFindPerAgent(Agent agent) {
        return missionAgentDAO.dbFindPerAgent(agent);
    }

    public List<MissionAgent> dbFindPerMission(Mission mission) {
        return missionAgentDAO.dbFindPerMission(mission);
    }
}
