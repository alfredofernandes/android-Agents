package com.example.alfredorfernandes.agents.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alfredorfernandes.agents.R;
import com.example.alfredorfernandes.agents.model.Agency;

import java.util.List;

public class AgencyAdapter extends ArrayAdapter<Agency> {

    private Context context;
    private List<Agency> values;
    private LayoutInflater flater;

    public AgencyAdapter(Activity context, int resource, List<Agency> values) {
        super(context, resource, values);

        this.context = context;
        this.values = values;

        flater = context.getLayoutInflater();
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

        View rowview = flater.inflate(R.layout.listitens_agency,null,true);

        TextView label = (TextView) rowview.findViewById(R.id.title);
        label.setText(values.get(position).getName());

        return rowview;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View rowview = flater.inflate(R.layout.listitens_agency,null,true);

        TextView label = (TextView) rowview.findViewById(R.id.title);
        label.setText(values.get(position).getName());

        return rowview;
    }
}
