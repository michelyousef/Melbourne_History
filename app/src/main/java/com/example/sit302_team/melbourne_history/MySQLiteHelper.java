package com.example.sit302_team.melbourne_history;

/*Name: Michel Yousef, SID: 213021179
* SIT 207 Android Programing Assignment 2
* This class is a custom SQLiteHelper class to create the database to store our data
*
*
* References:
*  1) SIT207 Lectures and weekly tutorials, School of IT, Deakin University
* */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    // declaring the database table name and column names
    public static final String TABLE_NAME  = "places";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_OPENING_HOURS = "hours";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PHOTO = "photo";




    // declaring database name and version
    private static final String DATABASE_NAME = "placesDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( "  + COLUMN_LOCATION +" text primary key, "+COLUMN_ADDRESS +" text not null, "+ COLUMN_PHONE
            + " text not null, "+COLUMN_WEBSITE +" text not null, "+COLUMN_OPENING_HOURS +" text not null, "+COLUMN_DESCRIPTION +" text not null, "+COLUMN_PHOTO+" text not null);";
    // declaring database constructor
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // database onCreate method
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // database onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}