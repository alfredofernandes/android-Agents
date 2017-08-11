package com.example.alfredorfernandes.agents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.model.Agent;

import java.util.ArrayList;
import java.util.List;

public class AgentAdapter extends BaseAdapter implements Filterable {

    private Context c;
    private List<Agent> agents;
    private CustomFilter filter;
    private List<Agent> filterList;

    public AgentAdapter(Context ctx, List<Agent> agents) {

        this.c = ctx;
        this.agents = agents;
        this.filterList = agents;
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
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.listitem_agent, null);
        }
        TextView nameTxt = (TextView) convertView.findViewById(R.id.agent_name);
        TextView levelTxt = (TextView) convertView.findViewById(R.id.agent_level);
        ImageView img = (ImageView) convertView.findViewById(R.id.agent_image);

        //SET DATA TO THEM
        nameTxt.setText("Name: " + agents.get(pos).getName());
        levelTxt.setText("Level: "+ agents.get(pos).getLevel());
        //img.setImageResource(agents.get(pos).getPhoto());

        return convertView;
    }
    @Override
    public Filter getFilter() {

        if(filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    //INNER CLASS
    class CustomFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {

                constraint = constraint.toString().toUpperCase();
                List<Agent> filters = new ArrayList<Agent>();
                //get specific items
                for(int i=0; i<filterList.size(); i++)
                {
                    if(filterList.get(i).getName().toUpperCase().contains(constraint)) {
                        Agent p = filterList.get(i);
                        filters.add(p);
                    }
                }
                results.count = filters.size();
                results.values = filters;

            } else {
                results.count=filterList.size();
                results.values=filterList;
            }

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            agents = (ArrayList<Agent>) results.values;
            notifyDataSetChanged();
        }
    }

}
