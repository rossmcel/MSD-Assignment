package com.example.msd_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static com.example.msd_preferences.DatabaseHelper.KEY_LOCATIONS_NAME;
import static com.example.msd_preferences.DatabaseHelper.KEY_MOVIES_NAME;
import static com.example.msd_preferences.DatabaseHelper.KEY_SHOWINGS_CINEMA_ID;
import static com.example.msd_preferences.DatabaseHelper.KEY_SHOWINGS_SHOWING_DATE;
import static com.example.msd_preferences.DatabaseHelper.KEY_SHOWINGS_SHOWING_TIME;

public class MovieShowings extends AppCompatActivity {
    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_showings);

        getSupportActionBar().setTitle("Movie Showings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseManager dbManager = new DatabaseManager(getApplicationContext());
        dbManager.open();

        int[] id = { R.id.ShowingLeftRowItem, R.id.ShowingMiddleLeftRowItem, R.id.ShowingMiddleRightRowItem, R.id.ShowingRightRowItem };

        String[] columns = new String[] { KEY_MOVIES_NAME, KEY_LOCATIONS_NAME, KEY_SHOWINGS_SHOWING_TIME, KEY_SHOWINGS_SHOWING_DATE };

        l = (ListView) findViewById(R.id.showings_list);

        Cursor showingsC = dbManager.getAllShowingsFullDetails();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.showings_row, showingsC, columns, id, 0);
        l.setAdapter(adapter);

    }
}