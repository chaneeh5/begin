package com.cookbook.SMSresponser;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SMSResponser extends Activity {

	TextView tv1;
	EditText ed1;
	Button bt1;
	SharedPreferences myprefs;
	Editor updater;
	String reply=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myprefs = PreferenceManager.getDefaultSharedPreferences(this);
        tv1 = (TextView) this.findViewById(R.id.display);
        ed1 = (EditText) this.findViewById(R.id.editText);
        bt1 = (Button) this.findViewById(R.id.submit);
        reply = myprefs.getString("reply", "Thank you for your message. I am busy now. I will call you later");
        tv1.setText(reply);
        updater = myprefs.edit();
        ed1.setHint(reply);
        bt1.setOnClickListener(new OnClickListener(){
    		public void onClick(View view){
    			updater.putString("reply", ed1.getText().toString());
    			updater.commit();
    			SMSResponser.this.finish();
    			
    		}
    	});        
        
		try {
		    // start Service
		      Intent svc = new Intent(this, ResponserService.class);
		      startService(svc);	
		}	
		catch (Exception e) {
		    Log.e("onCreate", "service creation problem", e);
		}
    }
    
    
}