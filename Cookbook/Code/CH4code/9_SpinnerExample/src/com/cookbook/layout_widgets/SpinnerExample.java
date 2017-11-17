package com.cookbook.layout_widgets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerExample extends Activity {
    private static final String[] oceans = {
        "Pacific", "Atlantic", "Indian", 
        "Arctic", "Southern" };  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);

        Spinner favoriteOcean = (Spinner) findViewById(R.id.spinner);

        /*ArrayAdapter<String> mAdapter = new 
            ArrayAdapter<String>(this, R.layout.spinner_entry, oceans);        
        mAdapter.setDropDownViewResource(R.layout.spinner_entry);                   
        favoriteOcean.setAdapter(mAdapter);       */   
        
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.spinner_entry);
        mAdapter.setDropDownViewResource(R.layout.spinner_entry);
        for(int idx=0; idx<oceans.length; idx++)
            mAdapter.add(oceans[idx]);
        favoriteOcean.setAdapter(mAdapter);          
    }         
}

