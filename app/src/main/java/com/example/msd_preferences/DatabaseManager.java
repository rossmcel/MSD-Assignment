/***********************************************************************
 ***************************************************************************/

package com.example.msd_preferences;

import static com.example.msd_preferences.DatabaseHelper.DATABASE_TABLE_MOVIES;
import static com.example.msd_preferences.DatabaseHelper.KEY_MOVIES_ROWID;
import static com.example.msd_preferences.DatabaseHelper.KEY_MOVIES_MOVIEID;
import static com.example.msd_preferences.DatabaseHelper.KEY_MOVIES_NAME;
import static com.example.msd_preferences.DatabaseHelper.KEY_MOVIES_GENRE;

import static com.example.msd_preferences.DatabaseHelper.DATABASE_TABLE_MYMOVIES;
import static com.example.msd_preferences.DatabaseHelper.KEY_MYMOVIES_ROWID;
import static com.example.msd_preferences.DatabaseHelper.KEY_MYMOVIES_MOVIEID;
import static com.example.msd_preferences.DatabaseHelper.KEY_MYMOVIES_NAME;
import static com.example.msd_preferences.DatabaseHelper.KEY_MYMOVIES_GENRE;

import static com.example.msd_preferences.DatabaseHelper.DATABASE_TABLE_GENRES;
import static com.example.msd_preferences.DatabaseHelper.KEY_GENRES_ROWID;
import static com.example.msd_preferences.DatabaseHelper.KEY_GENRES_NAME;

import static com.example.msd_preferences.DatabaseHelper.DATABASE_TABLE_MYGENRES;
import static com.example.msd_preferences.DatabaseHelper.KEY_MYGENRES_ROWID;
import static com.example.msd_preferences.DatabaseHelper.KEY_MYGENRES_NAME;

import static com.example.msd_preferences.DatabaseHelper.DATABASE_TABLE_LOCATIONS;
import static com.example.msd_preferences.DatabaseHelper.KEY_LOCATIONS_ROWID;
import static com.example.msd_preferences.DatabaseHelper.KEY_LOCATIONS_LOCATIONID;
import static com.example.msd_preferences.DatabaseHelper.KEY_LOCATIONS_NAME;
import static com.example.msd_preferences.DatabaseHelper.KEY_LOCATIONS_LATITUDE;
import static com.example.msd_preferences.DatabaseHelper.KEY_LOCATIONS_LONGITUDE;
import static com.example.msd_preferences.DatabaseHelper.KEY_LOCATIONS_TYPE;

import static com.example.msd_preferences.DatabaseHelper.DATABASE_TABLE_SHOWINGS;
import static com.example.msd_preferences.DatabaseHelper.KEY_SHOWINGS_ROWID;
import static com.example.msd_preferences.DatabaseHelper.KEY_SHOWINGS_CINEMA_ID;
import static com.example.msd_preferences.DatabaseHelper.KEY_SHOWINGS_MOVIE_ID;
import static com.example.msd_preferences.DatabaseHelper.KEY_SHOWINGS_SHOWING_DATE;
import static com.example.msd_preferences.DatabaseHelper.KEY_SHOWINGS_SHOWING_TIME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager
{
    Context context;
    private com.example.msd_preferences.DatabaseHelper myDatabaseHelper;
    private SQLiteDatabase myDatabase;


    public DatabaseManager(Context context)
    {
        this.context = context;

    }

    public DatabaseManager open() throws SQLException {
        myDatabaseHelper = new com.example.msd_preferences.DatabaseHelper(context);
        myDatabase = myDatabaseHelper.getWritableDatabase();
        return this;
    }

    //---closes the database--- any activity that uses the dB will need to do this
    public void close()
    {
        myDatabaseHelper.close();
    }


    // Database inserts
    public long insertMovie(Long movieId, String movieName, String movieGenre)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_MOVIES_MOVIEID, movieId);
        initialValues.put(KEY_MOVIES_NAME, movieName);
        initialValues.put(KEY_MOVIES_GENRE, movieGenre);
        return myDatabase.insert(DATABASE_TABLE_MOVIES, null, initialValues);
    }
    public long insertMyMovie(Long movieId, String movieName, String movieGenre)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_MYMOVIES_MOVIEID, movieId);
        initialValues.put(KEY_MYMOVIES_NAME, movieName);
        initialValues.put(KEY_MYMOVIES_GENRE, movieGenre);
        return myDatabase.insert(DATABASE_TABLE_MYMOVIES, null, initialValues);
    }
    public long insertGenre(String genreName)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_GENRES_NAME, genreName);
        return myDatabase.insert(DATABASE_TABLE_GENRES, null, initialValues);
    }
    public long insertMyGenre(String genreName)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_MYGENRES_NAME, genreName);
        return myDatabase.insert(DATABASE_TABLE_MYGENRES, null, initialValues);
    }
    public long insertLocation(Long locationId, String locationName, String locationLatitude, String locationLongitude, String locationType)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_LOCATIONS_LOCATIONID, locationId);
        initialValues.put(KEY_LOCATIONS_NAME, locationName);
        initialValues.put(KEY_LOCATIONS_LATITUDE, locationLatitude);
        initialValues.put(KEY_LOCATIONS_LONGITUDE, locationLongitude);
        initialValues.put(KEY_LOCATIONS_TYPE, locationType);
        return myDatabase.insert(DATABASE_TABLE_LOCATIONS, null, initialValues);
    }
    public long insertShowing(Integer showingCinemaId, Integer showingMovieId, String showingDate, String showingTime)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_SHOWINGS_CINEMA_ID, showingCinemaId);
        initialValues.put(KEY_SHOWINGS_MOVIE_ID, showingMovieId);
        initialValues.put(KEY_SHOWINGS_SHOWING_DATE, showingDate);
        return myDatabase.insert(KEY_SHOWINGS_SHOWING_TIME, null, initialValues);
    }


    //---deletes a particular contact person---
    public boolean deleteValue(String tableName, long rowId)
    {
        String databaseTableName = null;
        String keyTableRowId = null;

        switch(tableName) {
            case "Movies":
                databaseTableName = DATABASE_TABLE_MOVIES;
                keyTableRowId = KEY_MOVIES_ROWID;
                break;

            case "MyMovies":
                databaseTableName = DATABASE_TABLE_MYMOVIES;
                keyTableRowId = KEY_MYMOVIES_ROWID;
                break;

            case "Genres":
                databaseTableName = DATABASE_TABLE_GENRES;
                keyTableRowId = KEY_GENRES_ROWID;
                break;

            case "MyGenres":
                databaseTableName = DATABASE_TABLE_MYGENRES;
                keyTableRowId = KEY_MYGENRES_ROWID;
                break;

            case "Locations":
                databaseTableName = DATABASE_TABLE_LOCATIONS;
                keyTableRowId = KEY_LOCATIONS_ROWID;
                break;

            case "Showings" :
                databaseTableName = DATABASE_TABLE_SHOWINGS;
                keyTableRowId = KEY_SHOWINGS_ROWID;
                break;
        }
        // delete statement.  If any rows deleted (i.e. >0), returns true
        return myDatabase.delete(databaseTableName, keyTableRowId +
                "=" + rowId, null) > 0;
    }

    //retrieve all rows of tables
    public Cursor getAllMovies()
    {
        return myDatabase.query(DATABASE_TABLE_MOVIES, new String[] {
                        KEY_MOVIES_ROWID,
                        KEY_MOVIES_NAME,
                        KEY_MOVIES_GENRE},
                null,
                null,
                null,
                null,
                null);
    }
    public Cursor getAllMyMovies()
    {
        return myDatabase.query(DATABASE_TABLE_MYMOVIES, new String[] {
                        KEY_MYMOVIES_ROWID,
                        KEY_MYMOVIES_NAME,
                        KEY_MYMOVIES_GENRE},
                null,
                null,
                null,
                null,
                null);
    }
    public Cursor getMyTop10Movies()
    {
        return myDatabase.query(DATABASE_TABLE_MYMOVIES, new String[] {
                        KEY_MYMOVIES_ROWID,
                        KEY_MYMOVIES_NAME,
                        KEY_MYMOVIES_GENRE},
                null,
                null,
                null,
                null,
                null,
                "10");
    }
    public Cursor getAllGenres()
    {
        return myDatabase.query(DATABASE_TABLE_GENRES, new String[] {
                        KEY_GENRES_ROWID,
                        KEY_GENRES_NAME},
                null,
                null,
                null,
                null,
                null);
    }
    public Cursor getAllMyGenres()
    {
        return myDatabase.query(DATABASE_TABLE_MYGENRES, new String[] {
                        KEY_MYGENRES_ROWID,
                        KEY_MYGENRES_NAME},
                null,
                null,
                null,
                null,
                null);
    }
    public Cursor getAllGenresMinusMyGenres()
    {
        return myDatabase.rawQuery("select " + KEY_GENRES_ROWID + ", " + KEY_GENRES_NAME + " from " + DATABASE_TABLE_GENRES + " WHERE " + KEY_GENRES_NAME + " NOT IN( select " + KEY_MYGENRES_NAME + " from " + DATABASE_TABLE_MYGENRES + ");",null);
    }
    public Cursor getAllLocations()
    {
        return myDatabase.query(DATABASE_TABLE_LOCATIONS, new String[] {
                        KEY_LOCATIONS_ROWID,
                        KEY_LOCATIONS_LOCATIONID,
                        KEY_LOCATIONS_NAME,
                        KEY_LOCATIONS_LATITUDE,
                        KEY_LOCATIONS_LONGITUDE,
                        KEY_LOCATIONS_TYPE},
                null,
                null,
                null,
                null,
                null);
    }
    public Cursor getAllShowings()
    {
        return myDatabase.query(DATABASE_TABLE_SHOWINGS, new String[] {
                        KEY_SHOWINGS_ROWID,
                        KEY_SHOWINGS_CINEMA_ID,
                        KEY_SHOWINGS_MOVIE_ID,
                        KEY_SHOWINGS_SHOWING_DATE,
                        KEY_SHOWINGS_SHOWING_TIME},
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getAllShowingsFullDetails()
    {
        return myDatabase.rawQuery("SELECT " + DATABASE_TABLE_SHOWINGS + "." +
                KEY_SHOWINGS_CINEMA_ID + ", " + DATABASE_TABLE_SHOWINGS + "." + KEY_SHOWINGS_MOVIE_ID +
                ", " + DATABASE_TABLE_SHOWINGS + "." + KEY_SHOWINGS_SHOWING_DATE + ", "
                + DATABASE_TABLE_SHOWINGS + "." + KEY_SHOWINGS_SHOWING_TIME + ", " +
                DATABASE_TABLE_LOCATIONS + "." + KEY_LOCATIONS_NAME + ", " + DATABASE_TABLE_MOVIES + "." + KEY_MOVIES_NAME + " FROM " + DATABASE_TABLE_SHOWINGS +
                " JOIN " + DATABASE_TABLE_LOCATIONS + " ON " + DATABASE_TABLE_SHOWINGS + "." + KEY_SHOWINGS_CINEMA_ID
                + " = " + DATABASE_TABLE_LOCATIONS + "." + KEY_LOCATIONS_LOCATIONID + " JOIN " + DATABASE_TABLE_MOVIES + " ON " + DATABASE_TABLE_SHOWINGS + "." +
                KEY_SHOWINGS_MOVIE_ID + " = " + DATABASE_TABLE_MOVIES + "." + KEY_MOVIES_MOVIEID, null );
    }



    //retrieves particular table items
    public Cursor getMovie(long rowId) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_MOVIES, new String[] {
                                KEY_MOVIES_ROWID,
                                KEY_MOVIES_NAME,
                                KEY_MOVIES_GENRE},
                        KEY_MOVIES_ROWID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getMyMovie(long rowId) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_MYMOVIES, new String[] {
                                KEY_MYMOVIES_ROWID,
                                KEY_MYMOVIES_NAME,
                                KEY_MYMOVIES_GENRE},
                        KEY_MYMOVIES_ROWID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Integer getMovieRow(String movieName) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_MOVIES, new String[] {
                                KEY_MOVIES_ROWID,
                                KEY_MOVIES_NAME,
                                KEY_MOVIES_GENRE},
                        KEY_MOVIES_ROWID + " = " + '"' + movieName + '"',
                        null,
                        null,
                        null,
                        null,
                        "1");
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        int index = mCursor.getColumnIndex("_id");
        return Integer.parseInt(mCursor.getString(index));
        //return Integer.parseInt("1");
    }
    public Integer getMyMovieRow(String movieName) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_MYMOVIES, new String[] {
                                KEY_MYMOVIES_ROWID,
                                KEY_MYMOVIES_NAME,
                                KEY_MYMOVIES_GENRE},
                        KEY_MYMOVIES_NAME + " = " + '"' + movieName + '"',
                        null,
                        null,
                        null,
                        null,
                        "1");
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        else {
            return -1;
        }
        int index = mCursor.getColumnIndex("_id");
        return Integer.parseInt(mCursor.getString(index));
        //return Integer.parseInt("1");
    }
    public boolean checkIfMyMovieExists(String movieName) throws SQLException
    {
        Cursor mCursor = myDatabase.rawQuery("select count(*) " + " from " + DATABASE_TABLE_MYMOVIES +
                " WHERE " + KEY_MYMOVIES_NAME + " = " + '"' + movieName + '"' + ";",null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        if(Integer.parseInt(mCursor.getString(0)) < 1 ) {
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getGenre(long rowId) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_GENRES, new String[] {
                                KEY_GENRES_ROWID,
                                KEY_GENRES_NAME},
                        KEY_GENRES_ROWID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getMyGenre(long rowId) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_MYGENRES, new String[] {
                                KEY_MYGENRES_ROWID,
                                KEY_MYGENRES_NAME},
                        KEY_MYGENRES_ROWID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Integer getGenreRow(String genreName) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_GENRES, new String[] {
                                KEY_GENRES_ROWID,
                                KEY_GENRES_NAME},
                        KEY_GENRES_NAME + " = " + '"' + genreName + '"',
                        null,
                        null,
                        null,
                        null,
                        "1");
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        int index = mCursor.getColumnIndex("_id");
        return Integer.parseInt(mCursor.getString(index));
        //return Integer.parseInt("1");
    }
    public Integer getMyGenreRow(String genreName) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_MYGENRES, new String[] {
                                KEY_MYGENRES_ROWID,
                                KEY_MYGENRES_NAME},
                        KEY_MYGENRES_NAME + " = " + '"' + genreName + '"',
                        null,
                        null,
                        null,
                        null,
                        "1");
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        int index = mCursor.getColumnIndex("_id");
        return Integer.parseInt(mCursor.getString(index));
        //return Integer.parseInt("1");
    }

    public Cursor getLocation(long rowId) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_LOCATIONS, new String[] {
                                KEY_LOCATIONS_ROWID,
                                KEY_LOCATIONS_LOCATIONID,
                                KEY_LOCATIONS_NAME,
                                KEY_LOCATIONS_LATITUDE,
                                KEY_LOCATIONS_LONGITUDE,
                                KEY_LOCATIONS_TYPE},
                        KEY_LOCATIONS_ROWID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getShowing(long rowId) throws SQLException
    {
        Cursor mCursor =
                myDatabase.query(true, DATABASE_TABLE_SHOWINGS, new String[] {
                                KEY_SHOWINGS_ROWID,
                                KEY_SHOWINGS_CINEMA_ID,
                                KEY_SHOWINGS_MOVIE_ID,
                                KEY_SHOWINGS_SHOWING_DATE,
                                KEY_SHOWINGS_SHOWING_TIME},
                        KEY_SHOWINGS_ROWID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    // Update Tables
    public boolean updateMovie(long rowId, String movieName, String movieGenre)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_MOVIES_NAME, movieName);
        args.put(KEY_MOVIES_GENRE, movieGenre);
        return myDatabase.update(DATABASE_TABLE_MOVIES, args, KEY_MOVIES_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateMyMovie(long rowId, String movieName, String movieGenre)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_MYMOVIES_NAME, movieName);
        args.put(KEY_MYMOVIES_GENRE, movieGenre);
        return myDatabase.update(DATABASE_TABLE_MYMOVIES, args, KEY_MYMOVIES_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateGenre(long rowId, String genreName)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_GENRES_NAME, genreName);
        return myDatabase.update(DATABASE_TABLE_GENRES, args, KEY_GENRES_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateMyGenre(long rowId, String genreName)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_MYGENRES_NAME, genreName);
        return myDatabase.update(DATABASE_TABLE_MYGENRES, args, KEY_MYGENRES_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateLocation(Long rowId, Long locationId, String locationName, String locationLatitude, String locationLongitude, String locationType)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_LOCATIONS_LOCATIONID, locationId);
        args.put(KEY_LOCATIONS_NAME, locationName);
        args.put(KEY_LOCATIONS_LATITUDE, locationLatitude);
        args.put(KEY_LOCATIONS_LONGITUDE, locationLongitude);
        args.put(KEY_LOCATIONS_TYPE, locationType);
        return myDatabase.update(DATABASE_TABLE_LOCATIONS, args, KEY_LOCATIONS_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateShowing(Long rowId, Integer showingCinemaId, Integer showingMovieId, String showingDate, String showingTime)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_SHOWINGS_CINEMA_ID, showingCinemaId);
        args.put(KEY_SHOWINGS_MOVIE_ID, showingMovieId);
        args.put(KEY_SHOWINGS_SHOWING_DATE, showingDate);
        return myDatabase.update(DATABASE_TABLE_SHOWINGS, args, KEY_SHOWINGS_ROWID + "=" + rowId, null) > 0;
    }

}


