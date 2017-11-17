package com.cookbook.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cookbook.data.MyDB;

public class Diary extends Activity {
	  EditText titleET, contentET;
	  Button submitBT;
	  MyDB dba;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary);
        dba = new MyDB(this);
        dba.open();
            titleET = (EditText)findViewById(R.id.diarydescriptionText);
            contentET = (EditText)findViewById(R.id.diarycontentText);
            submitBT = (Button)findViewById(R.id.submitButton);
            submitBT.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    try {
                    	saveittoDB();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
    }
    public void saveittoDB(){
    	//save to DB
    	dba.insertdiary(titleET.getText().toString(), contentET.getText().toString());
    	dba.close();
    	titleET.setText("");
    	contentET.setText("");
    	Intent i = new Intent(Diary.this, DisplayDiaries.class);
    	startActivity(i);
    }
}