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

import java.util.ArrayList;
import java.util.List;


public class FavouritesAdapter extends BaseAdapter {

    // Custom adapter for location list activity

    private static LayoutInflater inflater = null;
    String[] places;
    int[] icons;
    Context context;
    private List<String> list = new ArrayList<String>();

    // Taking the values that is passed by the activity that call this constructor
    public FavouritesAdapter(FavouritesActivity favouritesActivity, String[] location_names, int[] location_images) {
        places = location_names;
        context = favouritesActivity;
        icons = location_images;
        // Prepare for rendering
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    // show as many as the length of the names array
    @Override
    public int getCount() {
        return list.size();
    }

    // getting the item position
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    // getting item id position
    @Override
    public long getItemId(int position) {
        return position;
    }

    // For all the string that is passed, show it in the layout that is prepared inside the custom_team_row
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // create a holder and view
        Holder holder = new Holder();
        View rowView;

        // Inflate the single row that is created in the custom_location_row
        rowView = inflater.inflate(R.layout.fav_row, parent, false);

        // Referencing to the widget inside the custom_team_row to the holder text and icon
        holder.text = (TextView) rowView.findViewById(R.id.location_name);
        holder.icon = (ImageView) rowView.findViewById(R.id.location_pic);

        // Printing all the location name and setting all location icons
        holder.text.setText(places[position]);
        holder.icon.setImageResource(icons[position]);

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

    public void add() {
        list.add("1");
        notifyDataSetChanged();
    }

    // Function to bring user to location details activity
    public void GoToLocationDetailsPage(String locationName) {
        Intent intent = new Intent(context, LocationDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // use flag activity in order to call a new activity from an adapter activity

        // Send the location title (the one that the user choose) to the location details activity
        Bundle myBundle = new Bundle();
        myBundle.putString("name", locationName);
        intent.putExtras(myBundle);

        // start intent
        context.getApplicationContext().startActivity(intent);
    }

    // initialise a holder class
    public class Holder {
        TextView text;
        ImageView icon;
    }

}
