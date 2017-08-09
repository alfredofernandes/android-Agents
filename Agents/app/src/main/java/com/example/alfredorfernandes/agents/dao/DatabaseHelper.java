package com.example.alfredorfernandes.agents.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alfredorfernandes.agents.model.Agency;
import com.example.alfredorfernandes.agents.model.Agent;
import com.example.alfredorfernandes.agents.model.Mission;
import com.example.alfredorfernandes.agents.model.MissionAgent;

public class DatabaseHelper  extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AgentsDB";
    private static final String TAG = DatabaseHelper.class.getSimpleName().toString();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //All necessary tables you like to create will create here
        db.execSQL(AgencyDAO.createTable());
        db.execSQL(AgentDAO.createTable());
        db.execSQL(MissionDAO.createTable());
        db.execSQL(MissionAgentDAO.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + AgencyDAO.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + AgentDAO.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MissionDAO.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MissionAgentDAO.TABLE);

        // create new tables
        onCreate(db);
    }
}
