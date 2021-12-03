package com.example.msd_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.msd_preferences.MainActivity;
import com.example.msd_preferences.DatabaseManager;
import com.example.msd_preferences.R;

public class DatabaseSetup extends MainActivity {

    public MainActivity context;
    public DatabaseSetup(MainActivity context) {
        this.context = context;
    }

    public void open()  {
        DatabaseManager dbManager = new DatabaseManager(this.context);
        dbManager.open();

        dbManager.insertMovie((long) 1,"Toy Story", "Cartoon");
        dbManager.insertMovie((long) 2,"James Bond", "Action");
        dbManager.insertMovie((long) 3,"Step Brothers", "Comedy");
        dbManager.insertMovie((long) 4,"The Shawshank Redemption", "Drama");
        dbManager.insertMovie((long) 5,"The Godfather", "Drama");
        dbManager.insertMovie((long) 6,"The Dark Knight", "Action");
        dbManager.insertMovie((long) 7,"The Godfather: Part II", "Drama");
        dbManager.insertMovie((long) 8,"The Lord of the Rings: The Return of the King", "Action");
        dbManager.insertMovie((long) 9,"Pulp Fiction", "Drama");
        dbManager.insertMovie((long) 10,"E.T. the Extra-Terrestrial", "Adventure");
        dbManager.insertMovie((long) 11,"Hugo", "Adventure");
        dbManager.insertMovie((long) 12,"Home Alone", "Comedy");
        dbManager.insertMovie((long) 13,"The Sound of Music", "Biography");
        dbManager.insertMovie((long) 14," Harry Potter and the Sorcerer's Stone", "Adventure");
        dbManager.insertMovie((long) 15,"Schindler's List ", "Biography");
        dbManager.insertMovie((long) 16,"Inception", "Action");
        dbManager.insertMovie((long) 17," Fight Club", "Drama");
        dbManager.insertMovie((long) 18,"The Lord of the Rings: The Fellowship of the Ring ", "Adventure");
        dbManager.insertMovie((long) 19,"Forrest Gump", "Drama");
        dbManager.insertMovie((long) 20,"Interstellar", "Adventure");
        dbManager.insertMovie((long) 21,"Casablanca", "Romance");
        dbManager.insertMovie((long) 22,"The Lion King", "Cartoon");


        dbManager.insertGenre("Cartoon");
        dbManager.insertGenre("Action");
        dbManager.insertGenre("Comedy");
        dbManager.insertGenre("Thriller");
        dbManager.insertGenre("Documentary");
        dbManager.insertGenre("Drama");
        dbManager.insertGenre("Adventure");
        dbManager.insertGenre("Biography");


        dbManager.insertLocation((long) 1,"Light House Cinema Smithfield", "53.348766", "-6.278975", "Cinema");
        dbManager.insertLocation((long) 2,"Cineworld Cinema Dublin", "53.350053", "-6.267701", "Cinema");
        dbManager.insertLocation((long) 3,"Savoy Cinema", "53.351180", "-6.260334", "Cinema");
        dbManager.insertLocation((long) 4,"Odeon Cinema Blanchardstown", "53.392691", "-6.391719", "Cinema");

        dbManager.insertShowing(1, 2, "12/05/2021", "14:15");
        dbManager.insertShowing(1, 2, "12/05/2021", "20:15");
        dbManager.insertShowing(1, 2, "13/05/2021", "13:00");
        dbManager.insertShowing(1, 1, "13/05/2021", "16:00");
        dbManager.insertShowing(1, 1, "14/05/2021", "11:00");
        dbManager.insertShowing(2, 2, "12/05/2021", "13:00");
        dbManager.insertShowing(2, 1, "12/05/2021", "17:00");

        /*String name = dbManager.getPerson(0).getString(0);
        Log.d("tag", "Value: " + name);*/

        dbManager.close();

    }
}