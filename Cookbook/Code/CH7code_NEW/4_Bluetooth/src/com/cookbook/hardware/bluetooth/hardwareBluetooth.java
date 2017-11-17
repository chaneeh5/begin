package com.cookbook.hardware.bluetooth;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class hardwareBluetooth extends Activity {
    /** Called when the activity is first created. */
	// Create a BroadcastReceiver for ACTION_FOUND
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        // When discovery finds a device
	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	            // Get the BluetoothDevice object from the Intent
	            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	            // Add the name and address to an array adapter to show in a ListView
	            Log.v("BlueTooth Testing",device.getName() + "\n" + device.getAddress());
	        }
	    }
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mBluetoothAdapter.listenUsingRfcommWithServiceRecord(name, uuid);
        mBluetoothAdapter.startDiscovery();
       
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth;

        }else{
        	if (!mBluetoothAdapter.isEnabled()) {
        	    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        	    startActivityForResult(enableBtIntent, 0);
        	}else{
        		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        		// If there are paired devices
        		if (pairedDevices.size() > 0) {
        		    // Loop through paired devices
        		    for (BluetoothDevice device : pairedDevices) {
        		        // Add the name and address to an array adapter to show in a ListView
        		       Log.v("BlueTooth Testing",device.getName() + "\n" + device.getAddress());
        		    }
        		}
        	}
        	
        }
    }
}