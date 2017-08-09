package com.example.alfredorfernandes.agents;

import android.app.Application;
import android.content.Context;

import com.example.alfredorfernandes.agents.dao.DatabaseHelper;
import com.example.alfredorfernandes.agents.dao.DatabaseManager;


public class App extends Application {

    private static Context context;
    private static DatabaseHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DatabaseHelper();
        DatabaseManager.initializeInstance(dbHelper);

    }

    public static Context getContext(){
        return context;
    }

}
