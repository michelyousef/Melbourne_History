package com.example.sit302_team.melbourne_history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class LocationsCustomAdapter extends BaseAdapter {

    // Custom adapter for location list activity

    String[] places;
    Context context;

    private static LayoutInflater inflater = null;

        // Taking the values that is passed by the activity that call this constructor
        public LocationsCustomAdapter(LocationListActivity locations_activity, String[] location_names) {
            places = location_names;
            context = locations_activity;

            // Prepare for rendering
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

    // show as many as the length of the names array
    @Override
    public int getCount() {
        return places.length;
    }

    // getting the item position
    @Override
    public Object getItem(int position) {
        return position;
    }

    // getting item id position
    @Override
    public long getItemId(int position) {
        return position;
    }

    // initialise a holder class
    public class Holder{

        TextView text;
    }

    // For all the string that is passed, show it in the layout that is prepared inside the custom_team_row
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // create a holder and view
        Holder holder = new Holder();
        View rowView;

        // Inflate the single row that is created in the custom_location_row
        rowView = inflater.inflate(R.layout.custom_location_row, parent, false);

        // Referencing to the widget inside the custom_team_row to the holder text
        holder.text = (TextView) rowView.findViewById(R.id.location_name);

        // Printing all the location name
        holder.text.setText(places[position]);

        // when an option on row view is selected
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the location details to the user
                GoToLocationDetailsPage(places[position]);
            }
        });
        return rowView;

    }

    // Function to bring user to location details activity
    public void GoToLocationDetailsPage(String locationName){
        Intent intent = new Intent(context, LocationDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // use flag activity in order to call a new activity from an adapter activity

        // Send the location title (the one that the user choose) to the location details activity
        Bundle myBundle = new Bundle();
        myBundle.putString("name", locationName);
        intent.putExtras(myBundle);

        // start intent
        context.getApplicationContext().startActivity(intent);
    }

}
