package com.cookbook.simple_activity;

import android.app.Activity;
import android.os.Bundle;

public class SimpleActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}