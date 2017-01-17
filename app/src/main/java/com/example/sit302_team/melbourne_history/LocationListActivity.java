package com.example.sit302_team.melbourne_history;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class LocationListActivity extends ListActivity {

    // Initialise variable
    Context context;
    String[] locations = new String[]{"Cooks Cottage", "Government House", "Immigration Museum", "Luna Park", "Melbourne Museum", "Melbourne Museum of Printing", "Melbourne Town Hall", "Melbourne Chinese Museum", "Old Melbourne Gaol", "Parliament House", "Polly Woodside Tall Ship", "Rippon Lea House & Gardens", "Royal Arcade", "Shrine of Remembrance", "The Block Arcade"};
    int[] locationIcons = new int[]{R.mipmap.icon_cooks, R.mipmap.icon_gov_house, R.mipmap.icon_immi_museum, R.mipmap.icon_luna,
            R.mipmap.icon_melb_museum, R.mipmap.icon_printing, R.mipmap.icon_town_hall, R.mipmap.icon_chinese_museum, R.mipmap.icon_gaol, R.mipmap.icon_parliament,
            R.mipmap.icon_polly, R.mipmap.icon_rippon, R.mipmap.icon_royal, R.mipmap.icon_shrine, R.mipmap.icon_block_arcade};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
