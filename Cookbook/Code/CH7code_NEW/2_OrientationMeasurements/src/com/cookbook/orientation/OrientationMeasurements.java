package com.cookbook.orientation;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class OrientationMeasurements extends Activity {
    private SensorManager myManager = null;
    TextView tv, tv2, tv3;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.attitude);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        // Set Sensor Manager 
        myManager = (SensorManager)getSystemService(SENSOR_SERVICE); 
        myManager.registerListener(mySensorListener, 
                myManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
                SensorManager.SENSOR_DELAY_GAME); 
        myManager.registerListener(mySensorListener, 
                myManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
                SensorManager.SENSOR_DELAY_GAME); 
        
        myManager.registerListener(mySensorListener, 
                myManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),
                SensorManager.SENSOR_DELAY_FASTEST);
        myManager.registerListener(mySensorListener, 
                myManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_FASTEST);

    }

    float[] mags = new float[3];
    float[] accels = new float[3];
    float[] RotationMat = new float[9];
    float[] InclinationMat = new float[9];
    float[] attitude = new float[3];
    final static double RAD2DEG = 180/Math.PI;
    private final SensorEventListener mySensorListener 
                                   = new SensorEventListener() { 
        @Override
        public void onSensorChanged(SensorEvent event) 
        { 
            int type = event.sensor.getType();

            if(type == Sensor.TYPE_MAGNETIC_FIELD) {    
                mags = event.values;  
            }
            if(type == Sensor.TYPE_ACCELEROMETER) { 
                accels = event.values;               
            }
            
            SensorManager.getRotationMatrix(RotationMat, 
                    InclinationMat, accels, mags);
            SensorManager.getOrientation(RotationMat, attitude);
            tv.setText("Azimuth, Pitch, Roll:\n" 
                    + attitude[0]*RAD2DEG + "\n"
                    + attitude[1]*RAD2DEG + "\n"
                    + attitude[2]*RAD2DEG);
         
                if(type==Sensor.TYPE_TEMPERATURE){
                    tv2.setText("Temperature:"+event.values[0]);
                }
            
          
                if(type==Sensor.TYPE_LIGHT){
                    tv3.setText("Light:"+event.values[0]);
                }


        };

            
        

        public void onAccuracyChanged(Sensor sensor, int accuracy) {} 
    }; 
}