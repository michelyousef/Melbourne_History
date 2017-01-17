package com.example.sit302_team.melbourne_history;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public Button userTrackerButton;
    public String text;
    // Declaring MySQLite open helper object
    MySQLiteHelper myOpenHelper;
    // Location data stored in separate arrays to be used in the database creation
    String[] location = {
            "Cooks Cottage",
            "Government House",
            "Immigration Museum",
            "Luna Park",
            "Melbourne Museum",
            "Melbourne Museum of Printing",
            "Melbourne Town Hall",
            "Melbourne Chinese Museum",
            "Old Melbourne Gaol",
            "Parliament House",
            "Polly Woodside Tall Ship",
            "Rippon Lea House & Gardens",
            "Royal Arcade",
            "Shrine of Remembrance",
            "The Block Arcade"
    };
    String[] address = {
            "Fitzroy Gardens, Wellington Parade, East Melbourne 3002",
            "Government House Drive,  Melbourne 3004",
            "400 Flinders Street, Melbourne 3000",
            "18 Lower Esplanade, St Kilda 3182",
            "11 Nicholson Street, Carlton 3053",
            "266 Geelong Road, West Footscray 3012",
            "90-130 Swanston Street, Melbourne 3000",
            "22 Cohen Place, Melbourne 3000 (Off Little Bourke Street)",
            "377 Russell Street, Melbourne 3000",
            "377 Russell Street, Melbourne 3000",
            "21 South Wharf Promenade, South Wharf 3006",
            "192 Hotham Street,  Elsternwick 3185",
            "335 Bourke Street Mall,  Melbourne 3000",
            "Corner St Kilda Road and Domain Road,Melbourne 3001",
            "282 Collins Street,  Melbourne 3001"};
    String[] phone = {
            "(03) 9658 9658",
            "(03) 9655 4211",
            "13 11 02",
            "(03) 9525 5033",
            "13 11 02",
            "(03) 9689 7555",
            "(03) 9658 9658",
            "(03) 9662 2888",
            "(03) 8663 7228",
            "(03) 9651 8911",
            "(03) 9699 9760",
            "(03) 9523 6095",
            "0438 891 212",
            "(03) 9661 8100",
            "(03) 9654 5244"
    };
    String[] website = {
            "http://www.onlymelbourne.com.au/cooks-cottage-681#.WEkqePl97b0",
            "http://www.governor.vic.gov.au/government-house",
            "https://museumvictoria.com.au/immigrationmuseum/",
            "https://lunapark.com.au/",
            "https://museumvictoria.com.au/melbournemuseum/visiting",
            "http://www.mmop.org.au/welcome.htm",
            "http://www.thatsmelbourne.com.au/Placestogo/MelbourneLandmarks/Historic/Pages/4452.aspx",
            "http://chinesemuseum.com.au/",
            "http://www.oldmelbournegaol.com.au/",
            "http://www.parliament.vic.gov.au/visit",
            "https://www.nationaltrust.org.au/places/polly-woodside/",
            "http://www.ripponleaestate.com.au/",
            "http://royalarcade.com.au/",
            "http://www.shrine.org.au/Home",
            "http://theblock.com.au/"};
    String[] hours = {
            "Open Daily, 9am - 5pm. Closed Christmas Day.",
            "Open 24 hours",
            "Closed Good Friday and Christmas Day",
            "Open Weekends, Victorian school holidays and every public holiday except Christmas Day. Closed weekdays during the school term.",
            "Open Daily, 10am - 5pm. (Closed Good Friday and Christmas Day)",
            "Open Sunday to Thursday, 2pm - 5pm without appointment.",
            "Open Monday - Friday, 11am - 12pm, 1pm - 2pm. Tours not available on weekends or public holidays except Australia Day when tours occur every hour from 12pm - 3pm.",
            "Open Daily, 10am – 4pm. Closed Good Friday, Christmas Day and New Year’s Day.",
            "Open Daily, 9.30am – 5pm. Closed Christmas Day and Good Friday",
            "Non-sitting days: Open Monday - Friday, 8:30am - 5:30pm. Closed on public holidays. During sitting days: Opens at 8:30am and remains open while parliament is sitting. Not open on weekends except for special events such as Open Day.",
            "Open Saturday and Sunday, 10am - 4pm. Closed Christmas Day, Boxing Day, New Year's Day and Easter weekend Polly Woodside has limited seasonal opening hours and is sometimes closed to general visitors. Please call prior to visiting to confirm opening hours. Open everyday during School Holidays, 10am - 4pm",
            "Open Daily, 10am – 5pm. Closed Good Friday and Christmas Day. Entry to the House is by Guided Tour only, booked upon arrival at the Gatehouse. Guided House Tours are subject to volunteer availability and run half hourly from 10:30am to 3pm",
            "Open Monday - Thursday 9am – 6pm, Friday 10am – 8pm, Saturday 9am – 5pm, Sunday 10am – 5pm, Public Holidays 11am – 5pm",
            "Open Daily, 10am - 5pm. Closed Good Friday and Christmas Day",
            "Open Monday - Thursday 8am - 6pm, Friday 8am - 8pm, Saturday 8am - 5pm, Sunday 9am - 5pm"
    };
    String[] description = {
            "Cook's cottage was built in 1755 and it is the oldest building in Australia. The cottage was originally built and located in Yokshire, England and built by Captain James Cooks' parent. In 1934, The cottage was relocated by Sir Russell Grimware. To bring the cottage over to Australia, every brick was numbered and packed.",
            "Located next to the Botanical Gardens and surrounded by Kings Domain, The Governor of Victoria uses Government House as their office and official residence. Between 1901 and 1930, it was used as the official residence of the Governor-General of Australia. It is the largest Government House in the former British Empire. The land for Government House was set aside in 1841, the surrounding landscape was completed in 1857. Construction on Government house began in 1871 and was completed in 1876.",
            "The former Old Customs house was restore and reopened as the Immigration Museum in 1998. It is the former office of Melbourne's immigration and customs and is full of real stories of people that migrated to Victoria. Inside the museum, you will find two levels of galleries that consists of temporary and permanent exhibitions. The Tribute Garden honours immigrants from over 90 different countries.",
            "Located on Port Phillip Bay, Luna Park has been the destination for families and visitors for over a century. Luna Park celebrated 100 years in 2012. It is full of heritage listed attracts and new rides to cater for all different ages. The most famous of the Park’s attractions is its Scenic Railway Roller Coaster, a large wooden coaster, that takes its passengers at high speed, around the perimeter of the Park. The Roller Coaster holds the title of the oldest, continually operating roller cosaster in the world and the only roller coasts to still have a standing brakeman that is in control of its moving carriages.",
            "The Melbourne Museum provides visitors an insight into the life in Victoria. It boasts permanent collections as well as temporary exhibitons from all over the world. Visitors will get to experience Victoria's natural environment, culter and history. The museum was previously located at the State Library Building but closed in 1997, but relocated to the Carlton Gardens in a brand new building on the 21st of October 2000.",
            "Visitors can see numerous components of typesetting and printing, including: Gutenberg's invention of movable types, Typesetting (by hand) with movable types, Linotype linecaster (hot metal composition), Ludlow Typograph linecaster (hot metal for large or fancy type), Poster types (usu. wood) for large lettering, engravings needed to print an illustration, stereotypes (duplicates of typesettings), the hand press as used by early printers, the cylinder press (more modern process), posters, books and other works printed in our studio by visiting artists, Library of Books as Artefacts, where the interest is not \"Who wrote it?\" but \"Who printed it, and how?\", Archival collections of thousands of artefacts and documents, illustrating the day-to-day work of the printing trade Slide show on traditional printing and the Museum's collections and programs.",
            "The Melbourne Town Hall was building in the heart of Melbourne, opening in 1870. It hosts theatre, weddings, receptions, exhibitons and cultural and civis activity. Built from bluestone and Tasmanian freestone, the building features a clock tower and fine masonry. Besides the Council Chambers, the Town Hall has a large auditorium, which was remodelled following a fire in 1925 and includes panels decorated with sepia figures. The auditorium’s Grand Organ features cutting-edge performances by a range of artists including Philip Glass, Paul Grabowski and the Necks. A free lunchtime concert series has been running since 2003, and the Grand Organ Museum allows visitors the opportunity to learn more about the organ.",
            "The Chinese Museum, located in Chinatown, has five floors of artworks, artefacts, fashion, books, photographs and jade. Contemporary exhibits include a new Chinatown Visitor Centre, a Dragon Gallery housing the Museum’s four dragons, the \"Finding Gold\" interactive display, research library, Bridge of Memories exhibition and a top floor filled with treasures. The Chinese Museum tells Australia’s 200-year Chinese history through artefacts as well as the stories of recent arrivals from all parts of the Asia Pacific region since the 1950s.",
            "Looks can be deceiving because the Old Melbourne Gaol is one of Melbourne's best historical places and truly worth a visit. Old Melbourne Gaol dominated the Melbourne skyline as a symbol of authority when it was built in the mid 1800s.",
            "Parliament House has been the seat of the Parliament of Victoria since 1855, except from 1901 to 1928 when it was the Parliament of Australia. From 1901 to 1928 Parliament House was the home of the Commonwealth Parliament, there were long delays in finding a suitable site and for construction to commence. In the meantime, the Victorian Parliament met in the Royal Exhibition Building in Carlton. It is the largest 19th century public building in Australia and one of the finest examples of the civic architecture of the British Empire period anywhere in the world.",
            "Polly Woodside is a Belfast-built, three-mast, iron-hulled barque and is the central feature of the Melbourne South Wharf precient. Built in Belfast by William J. Woodside and it was launched in 1885.",
            "Rippon Lea Estate is one of Australia's last grand suburban estates located 20 minutes out of the CBD. The estate is listed with the National Heritage Trust and showcases the unique lifestyle of the wealthy back in time.",
            "It is the first arcade built and longest-standing arcade in Australia. The archade's historical significance is profound and underpinned by the fact that it is part of the Victorian Heritage Register. The most striking feature of the arcade is the magnificent Gaunt’s Clock which is flanked either side by two giant statues of the mythical figures of Gog and Magog.",
            "The Shrine is a Victoria state memorial built in 1934. It is a memorial to all of the Australians who served in war and peacekeeping operation throughout Australia's history. Immerse yourself in the real-life stories of Australians in global conflicts, with over 800 artworks, historical artefacts and personal effects featured in the Galleries of Remembrance. Special exhibitions and events are held throughout the year providing an ever changing experience.",
            "The Block Arcade is a heritage shopping arcade located in the centre of Melbourne, one hundred years ago it was the \"place to be seen\". The arcade was designed by David C. Askew and constructured between 1891 and 1893."
    };
    String[] photo = {
            "cooks_cottage",
            "government_house",
            "immigration_museum",
            "luna_park",
            "melbourne_museum",
            "melbourne_museum_of_printing",
            "melbourne_town_hall",
            "melbourne_chinese_museum",
            "old_melbourne_gaol",
            "parliament_house",
            "polly_woodside",
            "rippon_lea_house",
            "royal_arcade",
            "shrine_of_remembrance",
            "the_block_arcade"
    };
    int[] icons = new int[]{R.mipmap.icon_cooks, R.mipmap.icon_gov_house, R.mipmap.icon_immi_museum, R.mipmap.icon_luna,
            R.mipmap.icon_melb_museum, R.mipmap.icon_printing, R.mipmap.icon_town_hall, R.mipmap.icon_chinese_museum, R.mipmap.icon_gaol, R.mipmap.icon_parliament,
            R.mipmap.icon_polly, R.mipmap.icon_rippon, R.mipmap.icon_royal, R.mipmap.icon_shrine, R.mipmap.icon_block_arcade};
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        checkPlayServices();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // create SQLiteOpen helper object
        myOpenHelper = new MySQLiteHelper(MapsActivity.this);
        //Create our database object and insert data in it using our OpenHelper object
        // by accessing getWritableDatabase() method of our myOpenHelper object
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //loop through the data arrays and insert it into the database
        for (int i = 0; i < 15; i++) {
            // wrap the database data
            ContentValues values = new ContentValues();
            //names inside the quotes must be exactly the same as the database column names
            values.put(MySQLiteHelper.COLUMN_LOCATION, location[i]);
            values.put(MySQLiteHelper.COLUMN_ADDRESS, address[i]);
            values.put(MySQLiteHelper.COLUMN_PHONE, phone[i]);
            values.put(MySQLiteHelper.COLUMN_WEBSITE, website[i]);
            values.put(MySQLiteHelper.COLUMN_OPENING_HOURS, hours[i]);
            values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description[i]);
            values.put(MySQLiteHelper.COLUMN_PHOTO, photo[i]);
            values.put(MySQLiteHelper.COLUMN_ICON, icons[i]);
            // insert data into the database from our ContentValues object and avoiding duplicate entries
            // by using SQLiteDatabase.CONFLICT_IGNORE
                /*Stack overflow, available at: http://stackoverflow.com/questions/26326696/how-to-prevent-to-insert-duplicate-value-in-sqlite-databse-if-duplicate-then-ov
                * Accessed 5 Dec 16*/
            db.insertWithOnConflict(MySQLiteHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }


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

        //Check the location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Enable MyLocation
            mMap.setMyLocationEnabled(true);
        } else {
            //Call a dialog if no permissions
            RequirePermissionsDialog();
        }

        mMap.setIndoorEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);


        // connecting button widget
        userTrackerButton = (Button) findViewById(R.id.userTrackerButton);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //set camera bounds and constrain the camera target to the camera bounds
        LatLngBounds Bounds = new LatLngBounds(new LatLng(-37.881824, 144.878735), new LatLng(-37.803969, 145.001916));
        mMap.setLatLngBoundsForCameraTarget(Bounds);

        // set minimum zoom and maximum zoom
        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(20.0f);

        // set historic place location
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
                .title("Cooks Cottage"));

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
                .title("Melbourne Chinese Museum"));

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

                switch (marker.getTitle()) {

                    case "Cooks Cottage":
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
                    case "Melbourne Chinese Museum":
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

                goToMelbourneCBD(melbourneCBD_location);

            }
        });

    }

    // Stack Overflow available at http://stackoverflow.com/questions/31016722/googleplayservicesutil-vs-googleapiavailability
    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
                Toast.makeText(this, "Please update Google Play Services and try again.", Toast.LENGTH_SHORT).show();
            }

            return false;
        }

        return true;
    }

    // Function to focus the camera to Melbourne CBD
    public void goToMelbourneCBD(LatLng melbourneCBDLocation) {

        // set camera to Melbourne CBD
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(melbourneCBDLocation, 15));

    }

    // Function to bring user to location details activity
    public void GoToLocationDetailsPage(String locationName) {
        Intent intent = new Intent(MapsActivity.this, LocationDetailsActivity.class);

        // Send the marker title (the one that the user choose) to the location details activity
        Bundle myBundle = new Bundle();
        myBundle.putString("name", locationName);
        intent.putExtras(myBundle);

        startActivity(intent);
    }

    //A dialog for asking users to grant the location permission in setting.
    public void RequirePermissionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle(getString(R.string.require_permission))
                .setMessage(getString(R.string.go_to_setting))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Go to Setting
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Close the app
                        finish();
                    }
                })
                .create().show();
    }
}