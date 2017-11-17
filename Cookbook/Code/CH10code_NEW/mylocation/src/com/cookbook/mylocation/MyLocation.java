package com.cookbook.mylocation;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class MyLocation extends MapActivity {
	 	
	LocationManager mLocationManager;
	Location mLocation;	  
	TextView tv;	  
	List<Overlay> mapOverlays;
	MyMarkerLayer markerlayer;
	private MapController mc;	
	private MapView mapView;
	public static Context mContext;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
       tv = (TextView) findViewById(R.id.tv1);
       mapView =(MapView)findViewById(R.id.map1);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);       

        Criteria criteria = new Criteria();

        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        criteria.setPowerRequirement(Criteria.POWER_LOW);

        String locationprovider = mLocationManager.getBestProvider(criteria,true);
        Log.v("Provider","Found provider:"+locationprovider);

        mLocation = mLocationManager.getLastKnownLocation(locationprovider);
        List<Address> addresses;
        String area="Seattle,WA";
        int geolat = 0;
        int geolon = 0;

        //mLocationManager.requestLocationUpdates(locationprovider,0,0.0f,ll);
            
            Geocoder gc = new Geocoder(this);
            String addr="";
            try
            {
              addresses = gc.getFromLocationName(area, 1);
              if(addresses != null)
              {
                  Address x = addresses.get(0);
                  StringBuilder mSB = new StringBuilder("Address:\n");

                  geolat =(int)(x.getLatitude()*1E6);
                  geolon = (int)(x.getLongitude()*1E6);

                    mSB.append("latitude: ").append(x.getLatitude()).append("\n")
                    .append("longitude: ").append(x.getLongitude());
                    tv.setText(mSB.toString());

              }
            }catch(IOException e){
                tv.setText(e.getMessage());
            }

		mapView.setBuiltInZoomControls(true);
		GeoPoint point = new GeoPoint(geolat,geolon);
		mc.animateTo(point);
		mc.setZoom(3);
		
		int x=50;
		int y=50;
		MapView.LayoutParams mScreenLayoutParams;
		mScreenLayoutParams = new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT,MapView.LayoutParams.WRAP_CONTENT,x,y,MapView.LayoutParams.LEFT);
		
		final TextView tv = new TextView(this);
		tv.setText("Adding View to Google Map");
		tv.setTextColor(Color.BLUE);
		tv.setTextSize(20);
		mapView.addView(tv, mScreenLayoutParams);
		
		
		x=250;
		y =250;
		mScreenLayoutParams = new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT,MapView.LayoutParams.WRAP_CONTENT,x,y,MapView.LayoutParams.BOTTOM_CENTER);
		
		Button clickMe = new Button(this);
		
		clickMe.setText("Click Me");
			
		clickMe.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				tv.setTextColor(Color.RED);
				tv.setText("Let's play");
			}
		});
			
		
		mapView.addView(clickMe, mScreenLayoutParams);
		
		
		
		
		
		/*
		OverlayItem overlayitem = new OverlayItem(point, "Google Campus", "I am in Google");
		markerlayer.addOverlayItem(overlayitem);
		mapOverlays.add(markerlayer);
    		*/
        	/*
        	 * 
        try{
        Geocoder mGC = new Geocoder(this, Locale.ENGLISH);
        addresses = mGC.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
    	if(addresses != null)
    	{
    		Address currentAddr = addresses.get(0);
    		StringBuilder mSB = new StringBuilder("Address:\n");
    		for(int i=0; i<currentAddr.getMaxAddressLineIndex(); i++){
    			mSB.append(currentAddr.getAddressLine(i)).append("\n");
    		}
    		
            tv.setText(mSB.toString());

    	}
        }catch(IOException e){
        	tv.setText(e.getMessage());
        }*/
/*        List<String> providers = mLocationManager.getProviders(true);

        StringBuilder mSB = new StringBuilder("Providers:"+providers.size()+"\n");

        for(int i = 0; i<providers.size(); i++){

        		mLocationManager.requestLocationUpdates(providers.get(i), 1000, 1, new LocationListener(){

					@Override
					public void onLocationChanged(Location location) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub
						
					}
        			
        		});
        		mSB.append(providers.get(i)).append(": \n");
        	    mLocation = mLocationManager.getLastKnownLocation(providers.get(i));
        	    if(mLocation != null){
        	    	mSB.append(mLocation.getLatitude()).append(" , ").append(mLocation.getLongitude()).append("\n");
        	    }
        	    else{
        	    	mSB.append("Location can not be found");
        	    }
        	    

        }
        */



      }

      private final LocationListener ll = new LocationListener(){
          public void onLocationChanged(Location l){
                 mLocation = l;

                 showupdate();

          }

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
      };

      public void showupdate(){

           tv.setText("Last location lat:"+mLocation.getLatitude()+" long:"+mLocation.getLongitude());

      }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}