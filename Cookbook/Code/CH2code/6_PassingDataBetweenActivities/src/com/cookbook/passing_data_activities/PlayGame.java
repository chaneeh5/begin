package com.cookbook.passing_data_activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayGame extends Activity {
    private TextView tv2;
    int answer;
    String author;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        tv2 = (TextView) findViewById(R.id.game_text);
       
        
        //reading information passed to this activity
        //Get the intent that started this activity
        Intent i = getIntent();
        //returns -1 if not initialized by calling activity
        answer = i.getIntExtra("meaningOfLife", -1); 
        //returns [] if not initialized by calling activity
        author = i.getStringExtra("userName");
        
        tv2.setText(author + ":" + answer);
        
        
        //change values for an example of return
        answer = answer - 41;
        author = author + " Jr.";
        
        //setup button listener
        Button startButton = (Button) findViewById(R.id.end_game);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //return information to calling activity
                Intent i = getIntent();
                i.putExtra("returnInt", answer);
                i.putExtra("returnStr", author);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}