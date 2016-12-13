package com.example.sit302_team.melbourne_history;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FavouritesActivity extends ListActivity {
    String[] locations = new String[15];
    int[] locationIcons = new int[15];
    Context context;
    MySQLiteHelper mySQLiteHelper;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        context = this;
        lv = (ListView) findViewById(android.R.id.list);
        FavouritesAdapter favList = new FavouritesAdapter(this, locations, locationIcons);
        // set list adapter
        lv.setAdapter(favList);

        mySQLiteHelper = new MySQLiteHelper(FavouritesActivity.this);
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MySQLiteHelper.TABLE_NAME
                        + " WHERE " + MySQLiteHelper.COLUMN_FAV + "= ?",
                new String[]{"1"});
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            locations[i] = cursor.getString(0);
            locationIcons[i] = cursor.getInt(8);
            favList.add();
        }
        cursor.close();


    }
}
