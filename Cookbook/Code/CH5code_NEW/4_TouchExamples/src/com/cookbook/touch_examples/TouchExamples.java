package com.cookbook.touch_examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TouchExamples extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button ex = (Button) findViewById(R.id.ex_button); 

        ex.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(TouchExamples.this, "Click", 
                        Toast.LENGTH_SHORT).show();
            }
        });
        ex.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText(TouchExamples.this, "LONG Click", 
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void onClick(View v) {
        if(v.getId() == R.id.ex_button){
            Toast.makeText(this, "Another Click", 
                    Toast.LENGTH_SHORT).show(); 
        }
    }
}