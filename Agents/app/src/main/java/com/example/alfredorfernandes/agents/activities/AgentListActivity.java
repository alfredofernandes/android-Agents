package com.example.alfredorfernandes.agents.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.DatabaseHelper;
import com.example.alfredorfernandes.agents.model.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AgentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_list);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataList();
    }

    private void loadDataList() {

        /*DatabaseHelper databaseHelper = new DatabaseHelper(this);
        final List<Agent> agents = databaseHelper.dbListAgents();

        if (agents.size() > 0) {

            ListView agentList = (ListView) findViewById(R.id.agent_list);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, agents) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    Agent agent = agents.get(position);

                    text1.setText(agent.getName());
                    text2.setText(agent.getLevel().toString());

                    return view;
                }
            };
            agentList.setAdapter(adapter);
        }*/
    }
}
