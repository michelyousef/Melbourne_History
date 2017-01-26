package com.example.sit302_team.melbourne_history;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import java.util.ArrayList;

public class LocationListActivity extends ListActivity {

    private EditText etSearch;
    private ArrayList<Location> locationArrayList = new ArrayList<Location>();
    private LocationsCustomAdapter locationsCustomAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu
        MenuInflater inflater = getMenuInflater();
        // Set the tool bar menu
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        // add new location name, description and icon to the location array list
        locationArrayList.add(new Location("Cooks Cottage","Built in 1755, the oldest building in Australia",R.mipmap.icon_cooks));
        locationArrayList.add(new Location("Government House","The Governor of Victoria uses Government House as their office and official residence",R.mipmap.icon_gov_house));
        locationArrayList.add(new Location("Immigration Museum","Full of real stories of people that migrated to Victoria, has 2 levels of galleries",R.mipmap.icon_immi_museum));
        locationArrayList.add(new Location("Luna Park","Located on Port Phillip Bay, full of heritage listed attracts and new rides",R.mipmap.icon_luna));
        locationArrayList.add(new Location("Melbourne Museum","Experience Victoria's natural environment and history",R.mipmap.icon_melb_museum));
        locationArrayList.add(new Location("Melbourne Museum of Printing","See numerous components of typesetting and printing, including: Gutenberg's invention of movable types",R.mipmap.icon_printing));
        locationArrayList.add(new Location("Melbourne Chinese Museum","Located in Chinatown, has five floors of artworks, artefacts, fashion, books, photographs and jade",R.mipmap.icon_chinese_museum));
        locationArrayList.add(new Location("Melbourne Town Hall","Opened in 1870, it hosts theatre, weddings, receptions, exhibitons and cultural activity",R.mipmap.icon_town_hall));
        locationArrayList.add(new Location("Old Melbourne Gaol","Looks can be deceiving, one of Melbourne's best historical places and truly worth a visit",R.mipmap.icon_gaol));
        locationArrayList.add(new Location("Parliament House","Has been the seat of the Parliament of Victoria since 1855",R.mipmap.icon_parliament));
        locationArrayList.add(new Location("Polly Woodside Tall Ship","Belfast-built, three-mast, iron-hulled barque and is the central feature of the Melbourne South Wharf precinct",R.mipmap.icon_polly));
        locationArrayList.add(new Location("Rippon Lea House & Gardens","One of Australia's last grand suburban estates located 20 minutes out of the CBD",R.mipmap.icon_rippon));
        locationArrayList.add(new Location("Royal Arcade","The first arcade built and longest-standing arcade in Australia",R.mipmap.icon_royal));
        locationArrayList.add(new Location("Shrine of Remembrance","Built in 1934, it is a memorial to all of the Australians who served in war and peacekeeping operation throughout Australia's history",R.mipmap.icon_shrine));
        locationArrayList.add(new Location("The Block Arcade","Heritage shopping arcade located in the centre of Melbourne",R.mipmap.icon_block_arcade));

        // Create new location custom adapter
        locationsCustomAdapter = new LocationsCustomAdapter(LocationListActivity.this, locationArrayList);

        // Initialise Search Bar
        etSearch = (EditText) findViewById(R.id.etSearch);

        // Add Text Change Listener to Search Bar
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // When text is changed
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                locationsCustomAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // set the adapter
        setListAdapter(locationsCustomAdapter);
    }

}
