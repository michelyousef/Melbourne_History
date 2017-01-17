package com.example.sit302_team.melbourne_history;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create tab host and connect it to the tab host widget
        TabHost menuTab = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost

        // setup
        menuTab.setup();

        // tab host option 1
        // set the content
        TabHost.TabSpec tabSpec1 = menuTab.newTabSpec("Location");
        tabSpec1.setContent(R.id.LocationsTab);

        // set the title and icon
        // Reference for ic_location icon: icons8, https://icons8.com/web-app/3723/marker#filled
        View tabIndicator1 = LayoutInflater.from(this).inflate(R.layout.tab_indicator, menuTab.getTabWidget(), false);
        ((TextView) tabIndicator1.findViewById(R.id.tab_title)).setText(R.string.tab1Name);
        ((ImageView) tabIndicator1.findViewById(R.id.tab_icon)).setImageResource(R.mipmap.ic_location);

        // set the intent
        tabSpec1.setIndicator(tabIndicator1).setContent(new Intent(this, LocationListActivity.class));

        // tab host option 2
        // set the content
        TabHost.TabSpec tabSpec2 = menuTab.newTabSpec("Map");
        tabSpec2.setContent(R.id.MapTab);

        // Reference for ic_map icon: icons8, https://icons8.com/web-app/3779/map-marker
        // set the title and icon
        View tabIndicator2 = LayoutInflater.from(this).inflate(R.layout.tab_indicator, menuTab.getTabWidget(), false);
        ((TextView) tabIndicator2.findViewById(R.id.tab_title)).setText(R.string.tab2Name);
        ((ImageView) tabIndicator2.findViewById(R.id.tab_icon)).setImageResource(R.mipmap.ic_map);

        // set the intent
        tabSpec2.setIndicator(tabIndicator2).setContent(new Intent(this, MapsActivity.class));

        // tab host option 3
        // set the content
        TabHost.TabSpec tabSpec3 = menuTab.newTabSpec("About Us");
        tabSpec3.setContent(R.id.AboutUsTab);

        // set the title and icon
        // Reference for ic_about_us icon: icons8, https://icons8.com/web-app/23264/user#filled
        View tabIndicator3 = LayoutInflater.from(this).inflate(R.layout.tab_indicator, menuTab.getTabWidget(), false);
        ((TextView) tabIndicator3.findViewById(R.id.tab_title)).setText(R.string.tab3Name);
        ((ImageView) tabIndicator3.findViewById(R.id.tab_icon)).setImageResource(R.mipmap.ic_about_us);

        // set the intent
        tabSpec3.setIndicator(tabIndicator3).setContent(new Intent(this, AboutUs.class));

        // tab host option 4
        // set the content
        TabHost.TabSpec tabSpec4 = menuTab.newTabSpec("Favourites");
        tabSpec4.setContent(R.id.FaveTab);

        // set the title and icon
        // Reference for ic_about_us icon: icons8, https://icons8.com/web-app/23264/user#filled
        View tabIndicator4 = LayoutInflater.from(this).inflate(R.layout.tab_indicator, menuTab.getTabWidget(), false);
        ((TextView) tabIndicator4.findViewById(R.id.tab_title)).setText(R.string.tab4Name);
        ((ImageView) tabIndicator4.findViewById(R.id.tab_icon)).setImageResource(R.mipmap.ic_fave);

        // set the intent
        tabSpec4.setIndicator(tabIndicator4).setContent(new Intent(this, FavouritesActivity.class));


        // add all tab to the tab host
        menuTab.addTab(tabSpec1);
        menuTab.addTab(tabSpec2);
        menuTab.addTab(tabSpec3);
        menuTab.addTab(tabSpec4);

        //set map tab as the default tab
        menuTab.setCurrentTab(1);
    }

}
