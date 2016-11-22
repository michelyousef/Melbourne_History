package com.example.sit302_team.melbourne_history;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LocationDetailsActivity extends AppCompatActivity {

    EditText locationEditText;
    String locationPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        // take the marker title passed from the maps (the location name)
        Bundle myBundle = getIntent().getExtras();
        locationPassed = myBundle.getString("name", "");

        locationEditText = (EditText)findViewById(R.id.locationName);

        if (locationPassed == ""){

            assert locationEditText != null;
            locationEditText.setText("Error");

        } else{

            assert locationEditText != null;
            locationEditText.setText(locationPassed);

        }

    }
}
