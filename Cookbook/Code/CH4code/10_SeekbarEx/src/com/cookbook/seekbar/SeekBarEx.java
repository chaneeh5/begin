package com.cookbook.seekbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

public class SeekBarEx extends Activity {
    private SeekBar m_seekBar;
    boolean advancing = true;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        m_seekBar = (SeekBar) findViewById(R.id.SeekBar01);
        m_seekBar.setOnSeekBarChangeListener(new 
                SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar,
                    int progress, boolean fromUser) {
                if(fromUser) count = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        Thread initThread = new Thread(new Runnable() {
            public void run() {
                show_time();
            }
        });
        initThread.start();
    }
    int count;
    private void show_time() {
        for(count=0; count<100; count++) {
            m_seekBar.setProgress(count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}