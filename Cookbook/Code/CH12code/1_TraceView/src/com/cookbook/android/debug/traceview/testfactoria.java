package com.cookbook.android.debug.traceview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;

public class testfactoria extends Activity {
	public final String tag="testfactoria";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        factoria(10);
    }
    
    
    public int factoria(int n){
    	Debug.startMethodTracing(tag);
    	int result=1;;
    	for(int i=1; i<=n; i++){
    		result*=i;
    	}
    	Debug.stopMethodTracing();
    	return result;
    }
}