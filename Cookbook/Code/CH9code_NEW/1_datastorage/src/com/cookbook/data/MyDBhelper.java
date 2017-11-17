package com.cookbook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MyDBhelper extends SQLiteOpenHelper{
	private static final String CREATE_TABLE="create table "+Constants.TABLE_NAME+" ("+
	Constants.KEY_ID+" integer primary key autoincrement, "+
	Constants.TITLE_NAME+" text not null, "+
	Constants.CONTENT_NAME+" text not null, "+
	Constants.DATE_NAME+" long);";

	public MyDBhelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.v("open onCreate","Creating all the tables");
		try{
			
			db.execSQL(CREATE_TABLE);			
		}catch(SQLiteException ex)
		{
			Log.v("open exception caught",ex.getMessage());
			
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w("TaskDBAdapter", "Upgrading from version "+oldVersion +" to "+newVersion+", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
		onCreate(db);
	}

}
