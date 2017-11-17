package com.cookbook.hardware.telephony;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

public class TelephonyApp extends Activity {
    /** Called when the activity is first created. */
    TextView tv1;
    TelephonyManager telManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv1 =(TextView) findViewById(R.id.tv1);
        telManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        StringBuilder sb = new StringBuilder();
        sb.append("deviceid:").append(telManager.getDeviceId()).append("\n");
        sb.append("device Software Ver:").append(telManager.getDeviceSoftwareVersion()).append("\n");
        sb.append("Line number:").append(telManager.getLine1Number()).append("\n");   
        sb.append("Network Country ISO:").append(telManager.getNetworkCountryIso()).append("\n");
        sb.append("Network Operator:").append(telManager.getNetworkOperator()).append("\n");
        sb.append("Network Operator Name:").append(telManager.getNetworkOperatorName()).append("\n");
        sb.append("Sim Country ISO:").append(telManager.getSimCountryIso()).append("\n");
        sb.append("Sim Operator:").append(telManager.getSimOperator()).append("\n");
        sb.append("Sim Operator Name:").append(telManager.getSimOperatorName()).append("\n");
        sb.append("Sim Serial Number:").append(telManager.getSimSerialNumber()).append("\n");
        sb.append("Subscriber Id:").append(telManager.getSubscriberId()).append("\n");
        sb.append("Voice Mail Alpha Tag:").append(telManager.getVoiceMailAlphaTag()).append("\n");
        sb.append("Voice Mail Number:").append(telManager.getVoiceMailNumber()).append("\n");
        tv1.setText(sb.toString());
        telManager.listen(new TelListener(), PhoneStateListener.LISTEN_CALL_STATE);
      //  startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:14088923812"))); 

    }
    private class TelListener extends PhoneStateListener
    {
    	
	    public void onCallStateChanged(int state, String incomingNumber)
	    {
	    	super.onCallStateChanged(state, incomingNumber);
	    	Log.v("Phone State","state:"+state);
	    	
		    switch (state)
		    {
			    case TelephonyManager.CALL_STATE_IDLE:
				    //CALL_STATE_IDLE;
			    	Log.v("Phone State","incomingNumber:"+incomingNumber+" ended");
				    break;
			    case TelephonyManager.CALL_STATE_OFFHOOK:
				    //CALL_STATE_OFFHOOK;
			    	Log.v("Phone State","incomingNumber:"+incomingNumber+" picked up");
				    break;
			    case TelephonyManager.CALL_STATE_RINGING:
				    //CALL_STATE_RINGING
			    	Log.v("Phone State","incomingNumber:"+incomingNumber+" received");
				    break;
			    default:
			    	break;
		    }
	    }
    }
}