package com.example.alfredorfernandes.agents.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.model.Agent;

import java.util.ArrayList;
import java.util.List;

public class MissionAgentAdapter extends BaseAdapter {

    private Context context;
    private List<Agent> agents;
    private boolean[] agentselection;

    public MissionAgentAdapter(Activity context, List<Agent> values) {

        this.context = context;
        this.agents = values;

        agentselection = new boolean[agents.size()];
    }

    @Override
    public int getCount() {

        return agents.size();
    }

    @Override
    public Object getItem(int pos) {

        return agents.get(pos);
    }

    @Override
    public long getItemId(int pos) {

        return agents.indexOf(getItem(pos));
    }

    public List<Agent> agentsSelected() {

        List<Agent> selected = new ArrayList<>();

        for (int i=0; i < agentselection.length; i++)
        {
            if (agentselection[i]){
                Agent agent = agents.get(i);
                selected.add(agent);
            }
        }

        return selected;

    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem_agent_mission, null);

            holder.checkbox = (CheckBox) convertView.findViewById(R.id.agent_mission_checkBox);
            holder.textview = (TextView) convertView.findViewById(R.id.agent_mission_name);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.checkbox.setId(pos);
        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                CheckBox cb = (CheckBox) v;
                int id = cb.getId();
                if (agentselection[id]){
                    cb.setChecked(false);
                    agentselection[id] = false;
                } else {
                    cb.setChecked(true);
                    agentselection[id] = true;
                }
            }
        });

        Agent agent = agents.get(pos);

        holder.checkbox.setChecked(agentselection[pos]);
        holder.textview.setText(agent.getName());
        holder.id = pos;

        return convertView;
    }

    class ViewHolder {
        TextView textview;
        CheckBox checkbox;
        int id;
    }

}
