package com.example.sit302_team.melbourne_history;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class LocationListActivity extends ListActivity {

    // Initialise variable
    Context context;
    String[] locations = new String[]{"Cooks Cottage", "Government House", "Immigration Museum", "Luna Park", "Melbourne Museum", "Melbourne Museum of Printing", "Melbourne Chinese Museum", "Melbourne Town Hall" , "Old Melbourne Gaol", "Parliament House", "Polly Woodside Tall Ship", "Rippon Lea House & Gardens", "Royal Arcade", "Shrine of Remembrance", "The Block Arcade"};
    String[] descriptions = new String[]{"Built in 1755, the oldest building in Australia", "The Governor of Victoria uses Government House as their office and official residence", "Full of real stories of people that migrated to Victoria, has 2 levels of galleries", "Located on Port Phillip Bay, full of heritage listed attracts and new rides", "Experience Victoria's natural environment and history", "See numerous components of typesetting and printing, including: Gutenberg's invention of movable types", "Located in Chinatown, has five floors of artworks, artefacts, fashion, books, photographs and jade", "Opened in 1870, it hosts theatre, weddings, receptions, exhibitons and cultural activity" , "Looks can be deceiving, one of Melbourne's best historical places and truly worth a visit", "Has been the seat of the Parliament of Victoria since 1855", "Belfast-built, three-mast, iron-hulled barque and is the central feature of the Melbourne South Wharf precinct", "One of Australia's last grand suburban estates located 20 minutes out of the CBD", "The first arcade built and longest-standing arcade in Australia", "Built in 1934, it is a memorial to all of the Australians who served in war and peacekeeping operation throughout Australia's history'", "Heritage shopping arcade located in the centre of Melbourne"};
    int[] locationIcons = new int[]{R.mipmap.icon_cooks, R.mipmap.icon_gov_house, R.mipmap.icon_immi_museum, R.mipmap.icon_luna,
            R.mipmap.icon_melb_museum, R.mipmap.icon_printing,R.mipmap.icon_chinese_museum , R.mipmap.icon_town_hall, R.mipmap.icon_gaol, R.mipmap.icon_parliament,
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
        LocationsCustomAdapter locationList = new LocationsCustomAdapter(this, locations, descriptions, locationIcons);

        // set list adapter
        setListAdapter(locationList);

    }

}
