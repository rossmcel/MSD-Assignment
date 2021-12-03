package com.example.msd_preferences.ui.movie_list;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.msd_preferences.DatabaseManager;
import com.example.msd_preferences.Genre;
import com.example.msd_preferences.MainActivity;
import com.example.msd_preferences.R;

import java.util.Vector;

public class MovieListFragment extends Fragment {

    ListView l;
    View viewHolder;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        this.viewHolder = view;

        DatabaseManager dbManager = new DatabaseManager(this.getContext());
        dbManager.open();

        int[] id = { R.id.MovieRightRowItem };

        String[] columns = new String[] { "movie_name" };

        l = (ListView) view.findViewById(R.id.movie_list);

        Cursor c = dbManager.getAllMyMovies();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getContext(),
                R.layout.movie_row, c, columns, id, 0);
        l.setAdapter(adapter);



        Button generateBtn = (Button) view.findViewById(R.id.generate_movies_btn);
        generateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                generateMovies();
            }
        });


        return view;
    }

    private void generateMovies() {
        // Hold User's favourite genres
        Vector<String> genresVect = new Vector<String>();
        // Hold all movies
        Vector<Cursor> allMoviesVect = new Vector<Cursor>();
        // Hold generated movies
        Vector<Cursor> generatedMoviesVect = new Vector<Cursor>();

        DatabaseManager dbManager = new DatabaseManager(this.getContext());
        dbManager.open();

        // Get user's favourite genres
        Cursor gCursor = dbManager.getAllMyGenres();
        int cI = gCursor.getColumnIndex("genre_name");
        //Toast.makeText(this.getContext(), "" + genresVect.get(1), Toast.LENGTH_LONG).show();


        // Get all movies
        Cursor mCursor = dbManager.getAllMovies();
        // Loop through movies & their genre
        int in = mCursor.getColumnIndex("_id");
        int mIdIn = mCursor.getColumnIndex("_id");
        int mnI = mCursor.getColumnIndex("movie_name");
        int gnI = mCursor.getColumnIndex("movie_genre");


        // Get my movies
        Cursor myMoviesCursor = dbManager.getAllMyMovies();
        // clear all previously generated movies if there is any
        if(myMoviesCursor.getCount() > 0) {
            while (myMoviesCursor.moveToNext()) {
                int row = Integer.parseInt(myMoviesCursor.getString(in));
                dbManager.deleteValue("MyMovies", row);
            }
        }

        // loop through all movies
        while (mCursor.moveToNext()) {
            long movieId = mCursor.getLong(mIdIn);
            String movieName = mCursor.getString(mnI);
            String genreName = mCursor.getString(gnI);

            // loop through genres
            Cursor gCursor2 = dbManager.getAllMyGenres();
            while (gCursor2.moveToNext()) {
                // if movie's genre is one of the user's favourite
                if(gCursor2.getString(cI).equals(genreName)) {
                    dbManager.insertMyMovie(movieId, movieName, genreName);
                }
            }
        }


        // Refresh list to include newly generated movies
        int[] id = { R.id.MovieRightRowItem };
        String[] columns = new String[] { "movie_name" };
        l = (ListView) viewHolder.findViewById(R.id.movie_list);
        Cursor c = dbManager.getAllMyMovies();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getContext(),
                R.layout.movie_row, c, columns, id, 0);
        l.setAdapter(adapter);

    }
}
