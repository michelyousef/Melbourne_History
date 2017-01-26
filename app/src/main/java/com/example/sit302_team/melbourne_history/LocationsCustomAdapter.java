package com.example.sit302_team.melbourne_history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class LocationsCustomAdapter extends BaseAdapter implements Filterable {

    // Initializing Variable
    private ArrayList<Location> originalValues; // Original Values
    private ArrayList<Location> displayedValues; // Values to be displayed
    LayoutInflater inflater;
    Context context;

    // Taking the values that is passed by the activity that call this constructor
    public LocationsCustomAdapter(LocationListActivity locations_activity, ArrayList<Location> locationArrayList) {
        this.originalValues = locationArrayList;
        this.displayedValues = locationArrayList;
        this.context = locations_activity;

        // Prepare for rendering
        inflater = LayoutInflater.from(context);
    }

    // initialise a holder class
    public class Holder {
        LinearLayout locationRowContainer;
        TextView tvName;
        TextView tvDescription;
        ImageView ivIcon;
    }

    // show as many as the size of the display values array
    @Override
    public int getCount() {
        return displayedValues.size();
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

    // Set the information to the custom location row view
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Initializing holder
        Holder holder = null;

        // if the convertView is null
        if(convertView == null){

            // Create a new holder
            holder = new Holder();

            // Inflate the single row that is created in the custom_location_row
            convertView = inflater.inflate(R.layout.custom_location_row, parent, false);

            // Referencing to the widget inside the convertView to the holder text, description and icon
            holder.locationRowContainer = (LinearLayout)convertView.findViewById(R.id.locationRowContainer);
            holder.tvName = (TextView)convertView.findViewById(R.id.location_name);
            holder.tvDescription = (TextView)convertView.findViewById(R.id.location_description);
            holder.ivIcon = (ImageView)convertView.findViewById(R.id.location_pic);

            // set the convert view
            convertView.setTag(holder);
        }

        // if convert view is not null
        else{

            // convertView get tag
            holder = (Holder)convertView.getTag();
        }

        // Setting all the location name, descriptions and icons
        holder.tvName.setText(displayedValues.get(position).location_name);
        holder.tvDescription.setText(displayedValues.get(position).location_descriptions);
        holder.ivIcon.setImageResource(displayedValues.get(position).location_icons);

        // when an option on row view is selected
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the location details to the user
                GoToLocationDetailsPage(displayedValues.get(position).location_name);
            }
        });
        return convertView;

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

    // displaying the filtered values or searched values
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                displayedValues = (ArrayList<Location>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults(); // Holds the results of a filtering operation in values
                ArrayList<Location> FilteredArrList = new ArrayList<Location>();

                if (originalValues == null) {
                    originalValues = new ArrayList<Location>(displayedValues); // saves the original data in OriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = originalValues.size();
                    results.values = originalValues;

                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < originalValues.size(); i++) {
                        String data = originalValues.get(i).location_name;

                        // if the location name matches with the constraint)
                        if (data.toLowerCase().startsWith(constraint.toString())) {

                            // add the location row (name, desc, icon) to the array
                            FilteredArrList.add(new Location(originalValues.get(i).location_name,originalValues.get(i).location_descriptions, originalValues.get(i).location_icons));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

}
