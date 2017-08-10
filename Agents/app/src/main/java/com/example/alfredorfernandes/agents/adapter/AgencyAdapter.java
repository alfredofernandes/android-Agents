package com.example.alfredorfernandes.agents.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.model.Agency;

import java.util.List;

public class AgencyAdapter extends ArrayAdapter<Agency> {

    private Context context;
    private List<Agency> values;

    public AgencyAdapter(Context context, int resource, List<Agency> values) {
        super(context, resource, values);

        this.context = context;
        this.values = values;
    }

    public int getCount() {
        return values.size();
    }

    public Agency getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getName());
        

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getName());

        return label;
    }
}
