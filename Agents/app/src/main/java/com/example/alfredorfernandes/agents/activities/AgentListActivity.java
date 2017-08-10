package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.dao.AgentDAO;
import com.example.alfredorfernandes.agents.model.Agent;

import java.util.List;

public class AgentListActivity extends AppCompatActivity {

    private ListView agentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_list);

        // Agent List
        agentsList = (ListView) findViewById(R.id.agent_list);
        registerForContextMenu(agentsList);

        agentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Agent agent = (Agent) agentsList.getItemAtPosition(position);

                Intent intentGoToFrom = new Intent(AgentListActivity.this, AgentDetailActivity.class);
                intentGoToFrom.putExtra("agent", agent);
                startActivity(intentGoToFrom);
            }
        });

        // Agent List Back
        Button returnButton = (Button) findViewById(R.id.agent_button_back);
        returnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataList();
    }

    private void loadDataList() {

        AgentDAO agentDAO = new AgentDAO();
        final List<Agent> agents = agentDAO.dbList();

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
        }
    }
}
