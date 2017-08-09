package com.example.alfredorfernandes.agents.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AgentsDB";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*db.execSQL(CREATE_TABLE_AGENCY);
        db.execSQL(CREATE_TABLE_AGENT);
        db.execSQL(CREATE_TABLE_MISSION);
        db.execSQL(CREATE_TABLE_MISSION_AGENT);

        createData(db);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENCY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MISSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MISSION_AGENT);

        // create new tables
        onCreate(db);*/
    }

}
