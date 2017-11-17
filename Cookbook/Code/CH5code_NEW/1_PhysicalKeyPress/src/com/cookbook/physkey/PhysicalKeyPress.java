package com.cookbook.physkey;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class PhysicalKeyPress extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) { 
        case KeyEvent.KEYCODE_CAMERA: 
            Toast.makeText(this, "Pressed Camera Button", 
                    Toast.LENGTH_LONG).show();
            return true;
        case KeyEvent.KEYCODE_DPAD_LEFT:
            Toast.makeText(this, "Pressed DPAD Left Button", 
                    Toast.LENGTH_LONG).show();
            return true;
        case KeyEvent.KEYCODE_VOLUME_UP:
            Toast.makeText(this, "Pressed Volume Up Button", 
                    Toast.LENGTH_LONG).show();
            return false;
        case KeyEvent.KEYCODE_SEARCH:
            //example of tracking through to the KeyUp
            if(event.getRepeatCount() == 0)
                event.startTracking();
            return true;
        case KeyEvent.KEYCODE_BACK:
            // Make new onBackPressed compatible with earlier SDK's
            if (android.os.Build.VERSION.SDK_INT 
                    < android.os.Build.VERSION_CODES.ECLAIR
                    && event.getRepeatCount() == 0) {
                onBackPressed();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
        Toast.makeText(this, "Pressed BACK Key", Toast.LENGTH_LONG).show();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SEARCH && event.isTracking()
                && !event.isCanceled()) {
            Toast.makeText(this, "Pressed SEARCH Key", Toast.LENGTH_LONG).show();
            return true;

        }
        return super.onKeyUp(keyCode, event);
    }
}