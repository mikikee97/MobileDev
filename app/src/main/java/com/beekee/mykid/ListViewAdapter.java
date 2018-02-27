package com.beekee.mykid;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Owner on 2/26/2018.
 */

public class ListViewAdapter extends ArrayAdapter<Details> {

    private Activity activity;

    public ListViewAdapter(@NonNull Activity activity, @LayoutRes int resource, @NonNull List<Details> details) {
        super(activity, resource,details);
        this.activity = activity;
    }

    private static class ViewHolder {
        private TextView listviewActivityName;
        private TextView listviewLocation;
        private TextView listviewReporterName;

        public ViewHolder(View v) {
            listviewActivityName = (TextView) v.findViewById(R.id.listview_activity_name);
            listviewLocation = (TextView) v.findViewById(R.id.listview_location);
            listviewReporterName = (TextView) v.findViewById(R.id.listview_reporter_name);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.detail_item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        Details detail = getItem(position);

        holder.listviewActivityName.setText(detail.getmActivityName());
        holder.listviewLocation.setText(detail.getmLocation());
        holder.listviewReporterName.setText(detail.getmReporterName());

        return convertView;
    }


}
