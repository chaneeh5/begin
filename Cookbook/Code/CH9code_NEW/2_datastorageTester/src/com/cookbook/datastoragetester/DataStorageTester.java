package com.cookbook.datastoragetester;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class DataStorageTester extends Activity {
    /** Called when the activity is first created. */
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.output);
         String myUri = "content://com.cookbook.datastorage/diaries";
    	 Uri CONTENT_URI = Uri.parse(myUri);
        ContentResolver crInstance = getContentResolver(); //get a content Resolver instance
        Cursor c = crInstance.query(CONTENT_URI, null, null, null, null);
        startManagingCursor(c);
        StringBuilder sb = new StringBuilder();
        if(c.moveToFirst()){
        	do{
        		sb.append(c.getString(1)).append("\n");
        		
        	}while(c.moveToNext());
        }
        tv.setText(sb.toString());
        
    }
}