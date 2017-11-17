package jwei.apps.dataforandroid.ch1;

import jwei.apps.dataforandroid.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

/**
 * Examples for initial case as defined in Chapter 1 of Data for Android.
 * 
 * @author Jason Wei
 * 
 */
public class SharedPreferencesExample extends Activity {

    private static final String MY_DB = "my_db";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences sp = getSharedPreferences(MY_DB, Context.MODE_PRIVATE);

        Editor e = sp.edit();
        e.putString("testStringKey", "Hello World");
        e.putBoolean("testBooleanKey", true);
        e.commit();

        String stringValue = sp.getString("testStringKey", "error");
        boolean booleanValue = sp.getBoolean("testBooleanKey", false);

        Log.i("SharedPreferencesExample", "Retrieved string value: " + stringValue);
        Log.i("SharedPreferencesExample", "Retrieved boolean value: " + booleanValue);
    }
}
