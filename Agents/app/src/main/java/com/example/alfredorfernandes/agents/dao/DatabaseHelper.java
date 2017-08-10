package com.example.alfredorfernandes.agents.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alfredorfernandes.agents.App;

public class DatabaseHelper  extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AgentsDB";
    private static final String TAG = DatabaseHelper.class.getSimpleName().toString();

    public DatabaseHelper() {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //All necessary tables you like to create will create here
        db.execSQL(AgencyDAO.CREATE_TABLE);
        db.execSQL(AgentDAO.CREATE_TABLE);
        db.execSQL(MissionDAO.CREATE_TABLE);
        db.execSQL(MissionAgentDAO.CREATE_TABLE);
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
