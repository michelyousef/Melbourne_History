package com.example.sit302_team.melbourne_history;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
        TabHost menuTab = (TabHost)findViewById(android.R.id.tabhost); // initiate TabHost

        // setup
        menuTab.setup();

        // tab host option 1
        // set the content
        TabHost.TabSpec tabSpec1 = menuTab.newTabSpec("Location");
        tabSpec1.setContent(R.id.LocationsTab);

        // set the title and icon
        View tabIndicator1 = LayoutInflater.from(this).inflate(R.layout.tab_indicator, menuTab.getTabWidget(), false);
        ((TextView) tabIndicator1.findViewById(R.id.tab_title)).setText(R.string.tab1Name);
        ((ImageView) tabIndicator1.findViewById(R.id.tab_icon)).setImageResource(R.mipmap.ic_location);

        // set the intent
        tabSpec1.setIndicator(tabIndicator1).setContent(new Intent(this, LocationListActivity.class));

        // tab host option 2
        // set the content
        TabHost.TabSpec tabSpec2 = menuTab.newTabSpec("Map");
        tabSpec2.setContent(R.id.MapTab);

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
        View tabIndicator3 = LayoutInflater.from(this).inflate(R.layout.tab_indicator, menuTab.getTabWidget(), false);
        ((TextView) tabIndicator3.findViewById(R.id.tab_title)).setText(R.string.tab3Name);
        ((ImageView) tabIndicator3.findViewById(R.id.tab_icon)).setImageResource(R.mipmap.ic_about_us);

        // set the intent
        tabSpec3.setIndicator(tabIndicator3).setContent(new Intent(this, AboutUs.class));

        // add all tab to the tab host
        menuTab.addTab(tabSpec1);
        menuTab.addTab(tabSpec2);
        menuTab.addTab(tabSpec3);

        //set map tab as the default tab
        menuTab.setCurrentTab(1);
    }

}
