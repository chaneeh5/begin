package com.cookbook.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TwitterCookBook extends Activity {
    /** Called when the activity is first created. */
	SharedPreferences myprefs;
	  EditText userET, passwordET;
	  Button loginBT;
	  static Twitter twitter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myprefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String username = myprefs.getString("username", null);
        final String password = myprefs.getString("password", null);
        setContentView(R.layout.login);
        userET = (EditText)findViewById(R.id.userText);
        passwordET = (EditText)findViewById(R.id.passwordText);
        loginBT = (Button)findViewById(R.id.loginButton);
        userET.setText(username);
        passwordET.setText(password);
        loginBT.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
               //     if( username.equals(userET.getText().toString())&&password.equals(passwordET.getText().toString())){
                    	 twitter = new TwitterFactory().getInstance(userET.getText().toString(), passwordET.getText().toString());
                      	 twitter.getFollowersIDs();
                      	 //if(tmp != null){
	                     	Intent i = new Intent(TwitterCookBook.this, UpdateAndList.class);
	                    	startActivity(i);
	                    	finish();
	                    	Editor ed = myprefs.edit();
	                    	ed.putString("username",userET.getText().toString());
	                    	ed.putString("password", passwordET.getText().toString());
	                    	ed.commit();
                      	// }
       
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(TwitterCookBook.this, "login failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });   
                
    }

}