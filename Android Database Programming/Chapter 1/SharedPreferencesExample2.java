package jwei.apps.dataforandroid.ch1;

import jwei.apps.dataforandroid.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Examples for use cases as defined in Chapter 1 of Data for Android.
 * 
 * @author Jason Wei
 * 
 */
public class SharedPreferencesExample2 extends Activity {

    private static final String MY_DB = "my_db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences sp = getSharedPreferences(MY_DB, Context.MODE_PRIVATE);

        /**
         * CHECK IF THIS IS USER'S FIRST VISIT
         */
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        if (!hasVisited) {
            // ...
            // SHOW SPLASH ACTIVITY, LOGIN ACTIVITY, ETC
            // ...

            // DON'T FORGET TO COMMIT THE CHANGE
            Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();
        }

        /**
         * CHECK LAST VISIT TIME
         */
        long lastVisitTime = sp.getLong("lastVisitKey", 0L);
        long timeElapsed = System.currentTimeMillis() - lastVisitTime;

        // YOUR UPDATE FREQUENCY HERE
        final long UPDATE_FREQ = 1000 * 60 * 60 * 24;

        if (timeElapsed > UPDATE_FREQ) {
            // ...
            // PERFORM NECESSARY UPDATES
            // ...
        }

        // STORE LATEST UPDATE TIME
        Editor e = sp.edit();
        e.putLong("lastVisitKey", System.currentTimeMillis());
        e.commit();

        /**
         * CACHE USER NAME AS STRING
         */
        // TYPICALLY YOU WILL HAVE AN EDIT TEXT VIEW
        // WHERE THE USER ENTERS THEIR USERNAME
        EditText userNameLoginText = (EditText) findViewById(R.id.login_editText);
        String userName = userNameLoginText.getText().toString();

        Editor e1 = sp.edit();
        e1.putString("userNameCache", userName);
        e1.commit();

        /**
         * REMEBERING A CERTAIN STATE
         */
        boolean isSilentMode = sp.getBoolean("isSilentRinger", false);
        if (isSilentMode) {
            // ...
            // TURN OFF SOUND
            // ...
        }

        /**
         * CACHING A LOCATION
         */
        // INSTANTIATE LOCATION MANAGER
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // ...
        // IGNORE LOCATION LISTENERS
        // ...
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        float lat = (float) lastKnownLocation.getLatitude();
        float lon = (float) lastKnownLocation.getLongitude();

        Editor e2 = sp.edit();
        e2.putFloat("latitudeCache", lat);
        e2.putFloat("longitudeCache", lon);
        e.commit();
    }
}
