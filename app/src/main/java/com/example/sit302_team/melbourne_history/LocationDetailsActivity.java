package com.example.sit302_team.melbourne_history;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class LocationDetailsActivity extends AppCompatActivity {
    // creating variables to represent the UI widgets
    TextView myTextView;
    TextView websiteTextView;
    ImageView myImageView;

    // creating MySQLite open helper object
    MySQLiteHelper mySQLiteOpenHelper;


    String locationPassed;
    // declaring string variables to store the values of the returned cursor object after reading the database
    String selectedLocation, address, phone, website, hours, description, photo;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        /*assigning and linking the local variable myTextView  in the java code
            to our TextView widget*/
        websiteTextView = (TextView) findViewById(R.id.websiteDetails);
        myTextView = (TextView) findViewById(R.id.textViewDetails);

        /*assigning and linking the local variable myImageView  in the java code
            to our ImageView widget*/
        myImageView = (ImageView) findViewById(R.id.imageView);


        // take the marker title passed from the maps (the location name)
        Bundle myBundle = getIntent().getExtras();
        locationPassed = myBundle.getString("name", "");

            /*How to set the title bar programmatically, Stack overflow, avaialable at:
            * http://stackoverflow.com/questions/12372485/how-to-set-the-titlebar-in-every-activity-programmatically
            * Accessed 6 Dec 16*/
        setTitle(locationPassed); // This is to change the Activity title to match the selected location


        // get the returned cursor and use it to fetch the required data
        Cursor myCursor = getReading();
        while (myCursor.moveToNext()) {
            // storing the contents of the cursor in string variables
            selectedLocation = myCursor.getString(myCursor.getColumnIndex("location"));
            address = myCursor.getString(myCursor.getColumnIndex("address"));
            phone = myCursor.getString(myCursor.getColumnIndex("phone"));
            website = myCursor.getString(myCursor.getColumnIndex("website"));
            hours = myCursor.getString(myCursor.getColumnIndex("hours"));
            description = myCursor.getString(myCursor.getColumnIndex("description"));
            photo = myCursor.getString(myCursor.getColumnIndex("photo"));
        }
        myCursor.close();

        //using html tags to format the text
        /* Styling with HTML markup, available at:
        https://developer.android.com/guide/topics/resources/string-resource.html
        accessed 7 Dec 16*/

        // when website text view is clicked, open the website from the phone browser
        websiteTextView.setText(Html.fromHtml("<html><body style=\"font-family:georgia,Times,arial;\"><h3>Website: </h3><u>" + website + "</u></body></html>"));
        websiteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create the browser intent and start activity (opening phone browser)
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                startActivity(browserIntent);

            }
        });

        myTextView.setText(Html.fromHtml("<html><body style=\"font-family:georgia,Times,arial;\"><h2>Address: </h2>" + address
                + "<h3>Phone: </h3>" + phone
                + "<h3>Opening Hours: </h3>" + hours
                + "<h3>Description: </h3>" + description
                + "</body></html>"));

        /*How to pass a variable to R.drawable.variableName, Stack Overflow, available at:
        * http://stackoverflow.com/questions/5760751/android-variable-passed-for-r-drawable-variablevalue
        * Accessed 7 Dec 16*/
        myImageView.setImageResource(getResources().getIdentifier(photo, "drawable", getPackageName()));


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    Cursor getReading() {
        // create SQLiteOpen helper object
        mySQLiteOpenHelper = new MySQLiteHelper(LocationDetailsActivity.this);
        // creating a database object using our mySQLiteOpenHelper class
        // by accessing its getReadableDatabase() method
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        /* Android Database Best Practices, Safari books online available at:
        * http://proquestcombo.safaribooksonline.com.ezproxy-b.deakin.edu.au/book/databases/9780134438092/5dot-working-with-databases-in-android/ch05lev1sec3_html?query=((rawquery+android))#snippet
        * Accessed 6 Dec 16
        *
        * */
        // Declaring a cursor to step through the data by querying the database using query method
        Cursor cursor = db.rawQuery("SELECT * FROM " + MySQLiteHelper.TABLE_NAME
                        + " WHERE " + MySQLiteHelper.COLUMN_LOCATION + "= ?",
                new String[]{locationPassed});


        return cursor;


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LocationDetails Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.sit302_team.melbourne_history/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LocationDetails Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.sit302_team.melbourne_history/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
        mClient.disconnect();
    }

    // inflate the tool bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        Bundle myBundle = getIntent().getExtras();
        locationPassed = myBundle.getString("name", "");

        //Check if the location is in favourites. If not, change the icon white
        if (!mySQLiteOpenHelper.isFav(locationPassed)) {
            menu.findItem(R.id.main_menu_option1).setIcon(R.mipmap.ic_favorite_border_black_48dp);
        }
        return true;
    }

    // option in the menu bar is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // getting the item selected
        int itemId = item.getItemId();

        // using switch to determine the action
        switch (itemId) {

            case R.id.main_menu_option1:

                //Get the location name
                Bundle myBundle = getIntent().getExtras();
                locationPassed = myBundle.getString("name", "");

                //Check if the location is in favourites
                if (mySQLiteOpenHelper.isFav(locationPassed)) {
                    //If so, remove it from favourites and change the icon white
                    if (mySQLiteOpenHelper.updatedata(locationPassed, 0)) {
                        item.setIcon(R.mipmap.ic_favorite_border_black_48dp);
                    }
                } else {
                    //If not, add it to favourites and change the icon black
                    if (mySQLiteOpenHelper.updatedata(locationPassed, 1)) {
                        item.setIcon(R.mipmap.ic_favorite_black_48dp);
                    }
                    break;
                }
        }

        return super.onOptionsItemSelected(item);
    }
}
