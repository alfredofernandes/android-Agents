package com.example.alfredorfernandes.agents.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.adapter.AgentAdapter;
import com.example.alfredorfernandes.agents.dao.AgentDAO;
import com.example.alfredorfernandes.agents.model.Agent;

import java.util.List;

public class AgentListActivity extends AppCompatActivity {

    private ListView agentsList;
    private AgentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_list);

        setTitle("AGENTS LIST");

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

        // Agent Search
        EditText inputSearch = (EditText) findViewById(R.id.agent_search);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
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

            adapter = new AgentAdapter(this, agents);
            agentList.setAdapter(adapter);
        }
    }
}
