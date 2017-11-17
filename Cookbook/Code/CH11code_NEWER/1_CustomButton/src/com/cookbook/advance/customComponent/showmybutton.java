package com.cookbook.advance.customComponent;

import android.app.Activity;
import android.os.Bundle;

public class showmybutton extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		myButton myb = (myButton)findViewById(R.id.mybutton1);
		myb.setText("Hello Students");
		myb.setTextSize(40);
		
	}

}
