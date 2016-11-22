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
    String[] locations =  new String[] {"Cook's Cottage", "Government House", "Immigration Museum", "Luna Park", "Melbourne Museum", "Melbourne Museum of Printing", "Melbourne Town Hall", "Melbourne's Chinese Museum", "Old Melbourne Gaol", "Parliament House", "Polly Woodside Tall Ship", "Ripppon Lea House & Gardens", "Royal Arcade", "Shrine of Remembrance", "The Block Arcade"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        context = this;

        // call custom adapter with custom adapter class
        LocationsCustomAdapter cellGroupAdapter = new LocationsCustomAdapter(this, locations);

        // set list adapter
        setListAdapter(cellGroupAdapter);

    }

}
