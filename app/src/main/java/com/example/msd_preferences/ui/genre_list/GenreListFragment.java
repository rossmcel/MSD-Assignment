package com.example.msd_preferences.ui.genre_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.msd_preferences.DatabaseManager;
import com.example.msd_preferences.Genre;
import com.example.msd_preferences.MainActivity;
import com.example.msd_preferences.R;
import com.example.msd_preferences.ui.slideshow.SlideshowViewModel;

import java.util.Vector;

public class GenreListFragment extends Fragment {

    private ListViewModel listViewModel;

    ListView l;
    SimpleCursorAdapter adapterHolder;

    private Button genreBtn;
    private Button removeGenreBtn;

    private String spinnerText;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre_list, container, false);

        DatabaseManager dbManager = new DatabaseManager(this.getContext());
        dbManager.open();

        int[] id = { R.id.LeftRowItem };

        String[] columns = new String[] { "genre_name" };

        l = (ListView) view.findViewById(android.R.id.list);

        Cursor c = dbManager.getAllMyGenres();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getContext(),
                R.layout.row, c, columns, id, 0);
        l.setAdapter(adapter);
        this.adapterHolder = adapter;



        Cursor gCursor = dbManager.getAllGenresMinusMyGenres();

        int[] spinnerId = { android.R.id.text1 };

        String[] spinnerColumns = new String[] { "genre_name" };

        SimpleCursorAdapter sca = new SimpleCursorAdapter(this.getContext(), android.R.layout.simple_spinner_item, gCursor, spinnerColumns, spinnerId);

        // set layout for activated adapter
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spin = (Spinner) view.findViewById(R.id.genres_spinner);
        spin.setAdapter(sca);


        Context mContext = this.getContext();
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                Cursor obj = (Cursor) parent.getItemAtPosition(position);
                spinnerText = obj.getString(1);
                Toast.makeText(mContext, "Selected ID=" + spinnerText, Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });



        genreBtn = (Button) view.findViewById(R.id.addGenreBtn);
        genreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dbManager.insertMyGenre(spinnerText);
                Cursor gCursorUpdated = dbManager.getAllMyGenres();
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),
                        R.layout.row, gCursorUpdated, columns, id, 0);
                l.setAdapter(adapter);
                Cursor spinCursorUpdated = dbManager.getAllGenresMinusMyGenres();
                SimpleCursorAdapter newSpinAdapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_spinner_item, spinCursorUpdated, spinnerColumns, spinnerId);
                newSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(newSpinAdapter);
            }
        });


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long l) {
                Cursor tempC = (Cursor) adapter.getItem(position);
                tempC.getString(1);
                int dbRow = dbManager.getMyGenreRow((String) tempC.getString(1));
                dbManager.deleteValue("MyGenres", dbRow);
            }
        });

        return view;
    }

}
