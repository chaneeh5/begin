package com.cookbook.handler_ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class HandlerUpdateUi extends Activity {
    private static ProgressBar m_progressBar; //UI reference
    int percent_done = 0;
    
    final Handler mHandler = new Handler();
    // Create runnable for posting results to the UI thread
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            if (m_progressBar != null) m_progressBar.setProgress(percent_done);
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        m_progressBar = (ProgressBar) findViewById(R.id.ex_progress_bar);
        
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
                percent_done = 0;               
                mHandler.post(mUpdateResults);
                
                computation(1);
                percent_done = 50;
                mHandler.post(mUpdateResults);
                
                computation(2);
                percent_done = 100;
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