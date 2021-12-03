package com.example.msd_preferences.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.msd_preferences.DatabaseManager;
import com.example.msd_preferences.MapsActivity;
import com.example.msd_preferences.MovieShowings;
import com.example.msd_preferences.R;
import com.example.msd_preferences.ui.genre_list.GenreListFragment;

import java.lang.reflect.Array;

public class HomeFragment extends Fragment {

    ListView l;

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DatabaseManager dbManager = new DatabaseManager(this.getContext());
        dbManager.open();

        int[] id = { R.id.MovieRightRowItem };

        String[] columns = new String[] { "movie_name" };

        l = (ListView) view.findViewById(R.id.home_movie_list);

        Cursor c = dbManager.getMyTop10Movies();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getContext(),
                R.layout.home_movie_row, c, columns, id, 0);
        l.setAdapter(adapter);

        int[] indexes = new int[6];
        if(dbManager.getAllMyGenres().getCount() > 0) {
            int count = 0;
            Cursor myGenresC = dbManager.getAllMyGenres();
            while(myGenresC.moveToNext()) {
                int rowNo = dbManager.getMyGenreRow(myGenresC.getString(1));
                indexes[count] = rowNo;
                count++;
            }
        }
        // genre 1
        if(dbManager.getMyGenre(indexes[0]).getCount() > 0) {
            String b1Title = (String) dbManager.getMyGenre(indexes[0]).getString(1);
            Button b1 = (Button) view.findViewById(R.id.button1);
            b1.setText(b1Title);
        }
        // genre 2
        if(dbManager.getMyGenre(indexes[1]).getCount() > 0) {
            String b2Title = (String) dbManager.getMyGenre(indexes[1]).getString(1);
            Button b2 = (Button) view.findViewById(R.id.button2);
            b2.setText(b2Title);
        }
        else {
            Button b2 = (Button) view.findViewById(R.id.button2);
            b2.setText(R.string.add_genre);
        }
        // genre 3
        if(dbManager.getMyGenre(indexes[2]).getCount() > 0) {
            String b3Title = (String) dbManager.getMyGenre(indexes[2]).getString(1);
            Button b3 = (Button) view.findViewById(R.id.button3);
            b3.setText(b3Title);
        }
        else {
            Button b3 = (Button) view.findViewById(R.id.button3);
            b3.setText(R.string.add_genre);
        }
        // genre 4
        if(dbManager.getMyGenre(indexes[3]).getCount() > 0) {
            String b4Title = (String) dbManager.getMyGenre(indexes[3]).getString(1);
            Button b4 = (Button) view.findViewById(R.id.button4);
            b4.setText(b4Title);
        }
        else {
            Button b4 = (Button) view.findViewById(R.id.button4);
            b4.setText(R.string.add_genre);
        }
        // genre 5
        if(dbManager.getMyGenre(indexes[4]).getCount() > 0) {
            String b5Title = (String) dbManager.getMyGenre(indexes[4]).getString(1);
            Button b5 = (Button) view.findViewById(R.id.button5);
            b5.setText(b5Title);
        }
        else {
            Button b5 = (Button) view.findViewById(R.id.button5);
            b5.setText(R.string.add_genre);
        }
        // genre 6
        if(dbManager.getMyGenre(indexes[5]).getCount() > 0) {
            String b6Title = (String) dbManager.getMyGenre(indexes[5]).getString(1);
            Button b6 = (Button) view.findViewById(R.id.button6);
            b6.setText(b6Title);
        }
        else {
            Button b6 = (Button) view.findViewById(R.id.button6);
            b6.setText(R.string.add_genre);
        }


        Button updateGenresBtn = (Button) view.findViewById(R.id.updateGenresBtn);
        updateGenresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), GenreListFragment.class);
                startActivity(intent);*/
            }
        });

        Button openShowings = (Button) view.findViewById(R.id.seeShowingsBtn);
        openShowings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MovieShowings.class);
                startActivity(intent);
            }
        });

        Button openMapBtn = (Button) view.findViewById(R.id.seeShowingLocationsBtn);
        openMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), GenreListFragment.class);
                startActivity(intent);*/
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}