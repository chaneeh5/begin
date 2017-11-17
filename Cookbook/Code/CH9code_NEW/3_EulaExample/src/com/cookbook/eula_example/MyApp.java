package com.cookbook.eula_example;

import android.app.Activity;
import android.os.Bundle;

public class MyApp extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Eula.show(this);
    }
}