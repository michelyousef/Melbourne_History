package com.example.sit302_team.melbourne_history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //On Click Reference Button
        Button referenceButton;
        referenceButton = (Button) findViewById(R.id.referenceButton);
        referenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get to About Activity
                Intent referenceActivity = new Intent(AboutUs.this, References.class);
                startActivity(referenceActivity);
            }
        });
    }


}
