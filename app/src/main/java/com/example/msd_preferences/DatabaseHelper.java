/***********************************************************************
 *
 *  This code does two main things
 *    1) Contains an onCreate method which contains the database creation code for a sample database (builds up the SQL "create table" commmands
 *    2) Contains an onUpgrade method - which in this example isn't used.
 *
 *
 ***************************************************************************/

package com.example.msd_preferences;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    // These are the names of the columns the table will contain. Could make these private
    // and use getters so that other classes can access them, but, as they are "final", this removes
    // the security risk that encapsulation (privacy/ getters/ setters) protects against

    // Database Details
    public static final String DATABASE_NAME = "Preferences";
    public static final int DATABASE_VERSION = 1;


    // Movies Table
    public static final String DATABASE_TABLE_MOVIES = "Movies";
    public static final String KEY_MOVIES_ROWID = "_id";
    public static final String KEY_MOVIES_MOVIEID = "movie_id";
    public static final String KEY_MOVIES_NAME = "movie_name";
    public static final String KEY_MOVIES_GENRE = "movie_genre";

    // My Movies Table
    public static final String DATABASE_TABLE_MYMOVIES = "MyMovies";
    public static final String KEY_MYMOVIES_ROWID = "_id";
    public static final String KEY_MYMOVIES_MOVIEID = "movie_id";
    public static final String KEY_MYMOVIES_NAME = "movie_name";
    public static final String KEY_MYMOVIES_GENRE = "movie_genre";

    // Genres Table
    public static final String DATABASE_TABLE_GENRES = "Genres";
    public static final String KEY_GENRES_ROWID = "_id";
    public static final String KEY_GENRES_NAME = "genre_name";

    // My Genres Table
    public static final String DATABASE_TABLE_MYGENRES = "MyGenres";
    public static final String KEY_MYGENRES_ROWID = "_id";
    public static final String KEY_MYGENRES_NAME = "genre_name";

    // Locations Table
    public static final String DATABASE_TABLE_LOCATIONS = "Locations";
    public static final String KEY_LOCATIONS_ROWID = "_id";
    public static final String KEY_LOCATIONS_LOCATIONID = "location_id";
    public static final String KEY_LOCATIONS_NAME = "location_name";
    public static final String KEY_LOCATIONS_LATITUDE = "location_latitude";
    public static final String KEY_LOCATIONS_LONGITUDE = "location_longitude";
    public static final String KEY_LOCATIONS_TYPE = "location_type";

    // Movie Showings Table
    public static final String DATABASE_TABLE_SHOWINGS = "Movie_Showings";
    public static final String KEY_SHOWINGS_ROWID = "_id";
    public static final String KEY_SHOWINGS_CINEMA_ID= "showing_cinema_id";
    public static final String KEY_SHOWINGS_MOVIE_ID = "showing_movie_id";
    public static final String KEY_SHOWINGS_SHOWING_DATE = "showing_date";
    public static final String KEY_SHOWINGS_SHOWING_TIME = "showing_time";



    // SQL create tables statements
    private static final String DATABASE_TABLE_MOVIES_CREATE =
            "create table " + DATABASE_TABLE_MOVIES  +
                    " (" + KEY_MOVIES_ROWID + " integer primary key autoincrement, " +
                    KEY_MOVIES_MOVIEID + " integer, " +
                    KEY_MOVIES_NAME + " text not null, " +
                    KEY_MOVIES_GENRE + " text not null); ";

    private static final String DATABASE_TABLE_MYMOVIES_CREATE =
            "create table " + DATABASE_TABLE_MYMOVIES  +
                    " (" + KEY_MYMOVIES_ROWID + " integer primary key autoincrement, " +
                    KEY_MYMOVIES_MOVIEID + " integer, " +
                    KEY_MYMOVIES_NAME + " text not null, " +
                    KEY_MYMOVIES_GENRE + " text not null); ";

    private static final String DATABASE_TABLE_GENRES_CREATE =
            "create table " + DATABASE_TABLE_GENRES  +
                    " (" + KEY_GENRES_ROWID + " integer primary key autoincrement, " +
                    KEY_GENRES_NAME + " text not null); ";

    private static final String DATABASE_TABLE_MYGENRES_CREATE =
            "create table " + DATABASE_TABLE_MYGENRES  +
                    " (" + KEY_MYGENRES_ROWID + " integer primary key autoincrement, " +
                    KEY_MYGENRES_NAME + " text not null); ";

    private static final String DATABASE_TABLE_LOCATIONS_CREATE =
            "create table " + DATABASE_TABLE_LOCATIONS  +
                    " (" + KEY_LOCATIONS_ROWID + " integer primary key autoincrement, " +
                    KEY_LOCATIONS_LOCATIONID + " integer not null," +
                    KEY_LOCATIONS_NAME + " text not null, " +
                    KEY_LOCATIONS_LATITUDE + " text not null, " +
                    KEY_LOCATIONS_LONGITUDE + " text not null, " +
                    KEY_LOCATIONS_TYPE + " text not null); ";

    private static final String DATABASE_TABLE_SHOWINGS_CREATE =
            "create table " + DATABASE_TABLE_SHOWINGS  +
                    " (" + KEY_SHOWINGS_ROWID + " integer primary key autoincrement, " +
                    KEY_SHOWINGS_CINEMA_ID + " integer not null, " +
                    KEY_SHOWINGS_MOVIE_ID + " integer not null, " +
                    KEY_SHOWINGS_SHOWING_DATE + " date not null, " +
                    KEY_SHOWINGS_SHOWING_TIME + " time not null); ";



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_TABLE_MOVIES_CREATE);
        db.execSQL(DATABASE_TABLE_MYMOVIES_CREATE);
        db.execSQL(DATABASE_TABLE_GENRES_CREATE);
        db.execSQL(DATABASE_TABLE_MYGENRES_CREATE);
        db.execSQL(DATABASE_TABLE_LOCATIONS_CREATE);
        db.execSQL(DATABASE_TABLE_SHOWINGS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion)
    {
        // If you want to change the structure of your database, e.g.

    }
}

