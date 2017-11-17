package com.cookbook.hardware;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CameraApplication extends Activity implements SurfaceHolder.Callback{
    /** Called when the activity is first created. */
    private static final String TAG = "cookbook hardware";
    private LayoutInflater mInflater = null;
    Camera mCamera;
    byte[] tempdata;
    boolean mPreviewRunning = false;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    Button takepicture;
    SensorManager sensorManager;
    TextView tv1,tv2,tv3;
    private final SensorEventListener mSensorEventListener = new SensorEventListener(){
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
		//	Log.v("test","Event Azimuth:"+event.values[0]+" Pitch:"+event.values[1]+" Roll:"+event.values[2]);
		//	Log.v("test","onSensorChanged:"+event.sensor.getName());
			if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
				tv1.setText("Event Azimuth:"+Math.round(event.values[0])+" Pitch:"+Math.round(event.values[1])+" Roll:"+Math.round(event.values[2]));
			}
		}

    };
    private final SensorEventListener mTListener = new SensorEventListener(){
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
		//	Log.v("test","Event Azimuth:"+event.values[0]+" Pitch:"+event.values[1]+" Roll:"+event.values[2]);
		//	Log.v("test Temperature","onSensorChanged:"+event.sensor.getName());
			if(event.sensor.getType()==Sensor.TYPE_TEMPERATURE){
				tv2.setText("Temperature:"+event.values[0]);
			}
		}

    };
    
    private final SensorEventListener mLListener = new SensorEventListener(){
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
		//	Log.v("test","Event Azimuth:"+event.values[0]+" Pitch:"+event.values[1]+" Roll:"+event.values[2]);
		//	Log.v("test Light","onSensorChanged:"+event.sensor.getName());
			if(event.sensor.getType()==Sensor.TYPE_LIGHT){
				tv3.setText("Light:"+event.values[0]);
			}
		}

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);

        mSurfaceView = (SurfaceView)findViewById(R.id.surface);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);        
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    //    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        mInflater = LayoutInflater.from(this);
        View overView = mInflater.inflate(R.layout.cameraoverlay, null);
        this.addContentView(overView,new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        takepicture = (Button)findViewById(R.id.button);
        tv1 =(TextView) findViewById(R.id.tv1);
        tv2 =(TextView) findViewById(R.id.tv2);
        tv3 =(TextView) findViewById(R.id.tv3);
        takepicture.setOnClickListener(new OnClickListener(){
    		public void onClick(View view){			
    		    {
    		    	
    		   		mCamera.takePicture(mShutterCallback, mPictureCallback,mjpeg);
    		    }
    		}
    		
    	});
      sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
      sensorManager.registerListener(mSensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);
      sensorManager.registerListener(mTListener, sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),SensorManager.SENSOR_DELAY_FASTEST);
      //sensorManager.registerListener(mLListener, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_FASTEST);
           
      List<Sensor> sensors= sensorManager.getSensorList(Sensor.TYPE_ALL);
      for(int i =0;i<sensors.size();i++){
    	  Log.v("LST",i+"] type:"+sensors.get(i).getName()+" type code:"+sensors.get(i).getType());
      }
  
    }

    PhoneStateListener mphoneListner = new PhoneStateListener(){
    	
    };
    
    
    
    Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback(){

		@Override
		public void onShutter() {
			// TODO Auto-generated method stub
			
		}
    	
    };
    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera c) {
        	if(data !=null)
        	{

        	}
        }
    };

    Camera.PictureCallback mjpeg = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera c) {
        	if(data !=null)
        	{
        		tempdata=data;
        		done();
            

        	}
        }
    };

    void done()
    {
     	 Bitmap bm = BitmapFactory.decodeByteArray(tempdata,0,tempdata.length);
         String url = Images.Media.insertImage(getContentResolver(),
         	 bm, null, null);	                                 
         bm.recycle();
         Bundle bundle = new Bundle();
         if(url!=null)
         {
        	 bundle.putString("url", url);
        	 
        	 Intent mIntent = new Intent();
        	 mIntent.putExtras(bundle);
        	 setResult(RESULT_OK, mIntent);
         }
         else
         {
        	 Toast.makeText(this, "Picture can not be saved", Toast.LENGTH_SHORT).show(); 
         }
         finish();
    }

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Auto-generated method stub
        Log.e(TAG, "surfaceChanged");
		try
		{
	        // XXX stopPreview() will crash if preview is not running
	        if (mPreviewRunning) {
	            mCamera.stopPreview();
	        }
	
	        Camera.Parameters p = mCamera.getParameters();
	        p.setPreviewSize(w, h);
/*
	        p.set("rotation", 90);
	        
	        p.setPictureSize(480,640);
	*/        
	        mCamera.setParameters(p);
	        mCamera.setPreviewDisplay(holder);
	        mCamera.startPreview();
	        mPreviewRunning = true;
		}
		catch(Exception e)
		{
			Log.d(TAG,e.toString());
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
        Log.e(TAG, "surfaceCreated");
        mCamera = Camera.open();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
        Log.e(TAG, "surfaceDestroyed");
        mCamera.stopPreview();
        mPreviewRunning = false;
        mCamera.release();
        mCamera=null;
	}

}