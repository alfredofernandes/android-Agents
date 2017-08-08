package com.example.alfredorfernandes.agents.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by juliana on 2017-08-08.
 */

public class ManagerDAO extends SQLiteOpenHelper {


    public ManagerDAO(Context context) {
        super(context, "AgentsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
