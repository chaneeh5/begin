package com.cookbook.handler_ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerUpdateUi extends Activity {
    TextView av; //UI reference
    int text_string = R.string.start;
    int background_color = Color.DKGRAY;

    final Handler mHandler = new Handler();
    // Create runnable for posting results to the UI thread
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            av.setText(text_string);
            av.setBackgroundColor(background_color);
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        av = (TextView) findViewById(R.id.computation_status);
        
        Button actionButton = (Button) findViewById(R.id.action);
        actionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                do_work();
            }
        });
    }

    //example of a computationally intensive action with UI updates
    private void do_work() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                text_string=R.string.start;
                background_color = Color.DKGRAY;                
                mHandler.post(mUpdateResults);
                
                computation(1);
                text_string=R.string.first;
                background_color = Color.BLUE;
                mHandler.post(mUpdateResults);
                
                computation(2);
                text_string=R.string.second;
                background_color = Color.GREEN;
                mHandler.post(mUpdateResults);
            }     
        });
        thread.start();
    }

    final static int SIZE=1000; //large enough to take some time
    double tmp;
    private void computation(int val) {
        for(int ii=0; ii<SIZE; ii++) 
            for(int jj=0; jj<SIZE; jj++) 
                tmp=val*Math.log(ii+1)/Math.log1p(jj+1);
    }
}