package com.cookbook.datastorage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.cookbook.data.Constants;
import com.cookbook.data.MyDB;

public class DiaryContentProvider extends ContentProvider {

	private MyDB dba;
	private static final  UriMatcher sUriMatcher;
	private static final int DIARIES=1; //the code that is returned when a URI is matched against the given components. Must be positive.
    public  static final  String AUTHORITY = "com.cookbook.datastorage";
    static {
    	        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    	        sUriMatcher.addURI(AUTHORITY, Constants.TABLE_NAME, DIARIES);

    }
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dba = new MyDB(this.getContext());
		dba.open();
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub

		 Cursor c=null;
        switch (sUriMatcher.match(uri)) {
	            case DIARIES:
	            	c = dba.getdiaries();
	                break;
	                
	            default:
                    throw new IllegalArgumentException("Unknown URI " + uri);
     
        }

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
