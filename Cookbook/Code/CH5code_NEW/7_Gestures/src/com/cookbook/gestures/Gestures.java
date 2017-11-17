package com.cookbook.gestures;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.TextView;

public class Gestures extends Activity implements OnGesturePerformedListener {
    private GestureLibrary mLibrary;
    private TextView tv;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.prediction);

        mLibrary = GestureLibraries.fromRawResource(this, R.raw.numbers);
        if (!mLibrary.load()) finish();
        
        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
    }
    
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
        String predList = "";
        NumberFormat formatter = new DecimalFormat("#0.00"); 
        for(int i=0; i<predictions.size(); i++) {
            Prediction prediction = predictions.get(i);
            predList = predList + prediction.name + " " + formatter.format(prediction.score) + "\n";
        }
        tv.setText(predList);        
    }
}