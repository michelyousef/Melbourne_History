package com.example.sit302_team.melbourne_history;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class LocationListActivity extends ListActivity {

    // Initialise variable
    Context context;
    String[] locations =  new String[] {"Cooks Cottage", "Government House", "Immigration Museum", "Luna Park", "Melbourne Museum", "Melbourne Museum of Printing", "Melbourne Town Hall", "Melbourne Chinese Museum", "Old Melbourne Gaol", "Parliament House", "Polly Woodside Tall Ship", "Rippon Lea House & Gardens", "Royal Arcade", "Shrine of Remembrance", "The Block Arcade"};
    int[] locationIcons = new int[] {R.mipmap.ic_about_us, R.mipmap.ic_favorites, R.mipmap.ic_location, R.mipmap.ic_map, R.mipmap.app_icon, R.mipmap.ic_about_us, R.mipmap.ic_favorites, R.mipmap.ic_location, R.mipmap.ic_map, R.mipmap.app_icon, R.mipmap.ic_about_us, R.mipmap.ic_favorites, R.mipmap.ic_location, R.mipmap.ic_map, R.mipmap.app_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        context = this;

        // call custom adapter with custom adapter class
        LocationsCustomAdapter locationList = new LocationsCustomAdapter(this, locations, locationIcons);

        // set list adapter
        setListAdapter(locationList);

    }

}
