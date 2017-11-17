package com.cookbook.fling_ex;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.TextView;

public class FlingExample extends Activity { 
    private static final int LARGE_MOVE = 60;
    private GestureDetector gestureDetector;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);    
        tv = (TextView) findViewById(R.id.text_result);

        gestureDetector = new GestureDetector(this,
                new SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                    float velocityX, float velocityY) {

                if (e1.getY() - e2.getY() > LARGE_MOVE) {
                    tv.append("\nFling Up with velocity " + velocityY);                    
                    return true;    

                } else if (e2.getY() - e1.getY() > LARGE_MOVE) { 
                    tv.append("\nFling Down with velocity " + velocityY);  
                    return true;       

                } else if (e1.getX() - e2.getX() > LARGE_MOVE) {
                    tv.append("\nFling Left with velocity " + velocityX);  
                    return true;   

                } else if (e2.getX() - e1.getX() > LARGE_MOVE) {
                    tv.append("\nFling Right with velocity " + velocityX);  
                    return true;
                }

                return false;
            } });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}