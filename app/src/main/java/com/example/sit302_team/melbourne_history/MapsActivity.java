package com.example.sit302_team.melbourne_history;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public Button userTrackerButton;
    public String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        checkPlayServices();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setIndoorEnabled(true);



        // connecting button widget
        userTrackerButton = (Button) findViewById(R.id.userTrackerButton);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // set camera bounds and constrain the camera target to the camera bounds
        LatLngBounds Bounds = new LatLngBounds(new LatLng(-37.881824, 144.878735), new LatLng(-37.803969, 145.001916));
        mMap.setLatLngBoundsForCameraTarget(Bounds);

        // set minimum zoom and maximum zoom
        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(20.0f);

        // set historic place location
        final LatLng defaultUserLocation = new LatLng(-37.867, 145.229);
        final LatLng melbourneCBD_location = new LatLng(-37.8136, 144.9631);
        LatLng cooksCottage_location = new LatLng(-37.8154, 144.9776);
        LatLng governmentHouse_location = new LatLng(-37.8268162, 144.9737636);
        LatLng immigrationMuseum_location = new LatLng(-37.8191392, 144.9603662);
        LatLng lunaPark_location = new LatLng(-37.868, 144.9767);
        LatLng melbourneMuseum_location = new LatLng(-37.8037, 144.9723);
        LatLng melbourneMuseumOfPrinting_location = new LatLng(-37.8052259, 144.8806473);
        LatLng melbourneTownHall_location = new LatLng(-37.8148118, 144.9665076);
        LatLng melbourneChineseMuseum_location = new LatLng(-37.8107583, 144.9691694);
        LatLng oldMelbourneGaol_location = new LatLng(-37.8077, 144.9656);
        LatLng parliamentHouse_location = new LatLng(-37.8118, 144.9732);
        LatLng pollyWoodsideTallShip_location = new LatLng(-37.8236, 144.9559);
        LatLng ripponLeaHouseGarden_location = new LatLng(-37.8795948, 144.9988726);
        LatLng royalArcade_location = new LatLng(-37.8143, 144.9637);
        LatLng shrineOfRemembrance_location = new LatLng(-37.8312, 144.9768);
        LatLng theBlockArcade_location = new LatLng(-37.815795, 144.9646665);

        // create a marker for each historic places
        Marker cooksCottage_marker = mMap.addMarker(new MarkerOptions()
                .position(cooksCottage_location)
                .title("Cook's Cottage"));

        Marker govermentHouse_marker = mMap.addMarker(new MarkerOptions()
                .position(governmentHouse_location)
                .title("Government House"));

        Marker immigrationMuseum_marker = mMap.addMarker(new MarkerOptions()
                .position(immigrationMuseum_location)
                .title("Immigration Museum"));

        Marker lunaPark_marker = mMap.addMarker(new MarkerOptions()
                .position(lunaPark_location)
                .title("Luna Park"));

        Marker melbourneMuseum_marker = mMap.addMarker(new MarkerOptions()
                .position(melbourneMuseum_location)
                .title("Melbourne Museum"));

        Marker melbourneMuseumOfPrinting_marker = mMap.addMarker(new MarkerOptions()
                .position(melbourneMuseumOfPrinting_location)
                .title("Melbourne Museum of Printing"));

        Marker melbourneTownHall_marker = mMap.addMarker(new MarkerOptions()
                .position(melbourneTownHall_location)
                .title("Melbourne Town Hall"));

        Marker melbourneChineseMuseum_marker = mMap.addMarker(new MarkerOptions()
                .position(melbourneChineseMuseum_location)
                .title("Melbourne's Chinese Museum"));

        Marker oldMelbourneGaol_marker = mMap.addMarker(new MarkerOptions()
                .position(oldMelbourneGaol_location)
                .title("Old Melbourne Gaol"));

        Marker parliamentHouse_marker = mMap.addMarker(new MarkerOptions()
                .position(parliamentHouse_location)
                .title("Parliament House"));

        Marker pollyWoodsideTallShip_marker = mMap.addMarker(new MarkerOptions()
                .position(pollyWoodsideTallShip_location)
                .title("Polly Woodside Tall Ship"));

        Marker ripponLeaHouseGarden_marker = mMap.addMarker(new MarkerOptions()
                .position(ripponLeaHouseGarden_location)
                .title("Rippon Lea House & Gardens"));

        Marker royalArcade_marker = mMap.addMarker(new MarkerOptions()
                .position(royalArcade_location)
                .title("Royal Arcade"));

        Marker shrineOfRemembrance_marker = mMap.addMarker(new MarkerOptions()
                .position(shrineOfRemembrance_location)
                .title("Shrine of Remembrance"));

        Marker theBlockArcade_marker = mMap.addMarker(new MarkerOptions()
                .position(theBlockArcade_location)
                .title("The Block Arcade"));


        // Zoom in to Melbourne CBD
        goToMelbourneCBD(melbourneCBD_location);

        // when marker title is click by user
        // - open location details page according to the title of the marker (using switch)
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                switch(marker.getTitle()){

                    case "Cook's Cottage":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Government House":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Immigration Museum":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Luna Park":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Melbourne Museum":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Melbourne Museum of Printing":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Melbourne Town Hall":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Melbourne's Chinese Museum":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Old Melbourne Gaol":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Parliament House":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Polly Woodside Tall Ship":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Rippon Lea House & Gardens":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Royal Arcade":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "Shrine of Remembrance":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;
                    case "The Block Arcade":
                        Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        GoToLocationDetailsPage(marker.getTitle());
                        break;

                }
            }
        });

        // when user tracker button is pressed
        userTrackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if camera focuses on Melbourne CBD, go to user location
                if (userTrackerButton.getText() == "Go To User") {

                    goToUser(defaultUserLocation);


                    // else if camera already focuses on user location, brings back to Melbourne CBD
                } else {

                    goToMelbourneCBD(melbourneCBD_location);

                }
            }
        });

    }
    // Stack Overflow available at http://stackoverflow.com/questions/31016722/googleplayservicesutil-vs-googleapiavailability
    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
                Toast.makeText(this, "please download the up to date google play service and try again", Toast.LENGTH_SHORT).show();
            }

            return false;
        }

        return true;
    }

    // inflate the tool bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // option in the menu bar is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // getting the item selected
        int itemId = item.getItemId();

        // using switch to determine the action
        switch (itemId){

            // menu option1
            case R.id.main_menu_option1:
                Toast.makeText(this, "create favorite list activity", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // Function to focus the camera to Melbourne CBD
    public void goToMelbourneCBD(LatLng melbourneCBDLocation){

        // set camera to Melbourne CBD
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(melbourneCBDLocation,15));

        // set button text to user location (user default position in burwood)
        text = "Go To User";
        userTrackerButton.setText(text);

    }

    // Function to focus the camera to User Location
    public void goToUser(LatLng userLocation){

        // set camera to user location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));

        // set button text to melbourne CBD
        text = "Go To Melbourne CBD";
        userTrackerButton.setText(text);

        /*

                // I can't get my current location using location.getLongitude() & location.getLatitude()
                    or even using the mMap.setMyLocationEnabled(true). my Emulator location is on already, i don't know why.
                    I doesn't have any android device so i cannot try whether it works or not. So for now, i'm going to set a default user location in burwood

                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                double myLongitude = location.getLongitude();
                double myLatitude = location.getLatitude();
                Toast.makeText(MapsActivity.this, myLatitude+", "+myLongitude, Toast.LENGTH_SHORT).show();

                LatLng latLng = new LatLng(myLatitude, myLongitude);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                myLocation = true;

        */
    }

    // Function to bring user to location details activity
    public void GoToLocationDetailsPage(String locationName){
        Intent intent = new Intent(MapsActivity.this, LocationDetailsActivity.class);

        // Send the marker title (the one that the user choose) to the location details activity
        Bundle myBundle = new Bundle();
        myBundle.putString("name", locationName);
        intent.putExtras(myBundle);

        startActivity(intent);
    }

}
