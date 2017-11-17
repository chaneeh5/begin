package jwei.apps.dataforandroid.ch1;

import jwei.apps.dataforandroid.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class SQLiteExample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // INIT OUR SQLITE HELPER
        SQLiteHelper sqh = new SQLiteHelper(this);

        // RETRIEVE A READABLE AND WRITEABLE DATABASE
        SQLiteDatabase sqdb = sqh.getWritableDatabase();

        // METHOD #1: INSERT USING CONTENTVALUE CLASS
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.NAME, "jason wei");

        // CALL INSERT METHOD
        sqdb.insert(SQLiteHelper.TABLE_NAME, SQLiteHelper.NAME, cv);

        // METHOD #2: INSERT USING SQL QUERY
        String insertQuery = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (" + SQLiteHelper.NAME + ") VALUES ('jwei')";
        sqdb.execSQL(insertQuery);

        // METHOD #1: QUERY USING WRAPPER METHOD
        Cursor c = sqdb.query(SQLiteHelper.TABLE_NAME, new String[] { SQLiteHelper.UID, SQLiteHelper.NAME }, null,
                null, null, null, null);

        while (c.moveToNext()) {
            // GET COLUMN INDICES AS WELL AS VALUES OF THOSE COLUMNS
            int id = c.getInt(c.getColumnIndex(SQLiteHelper.UID));
            String name = c.getString(c.getColumnIndex(SQLiteHelper.NAME));
            Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name);
        }

        c.close();

        // METHOD #2: QUERY USING SQL SELECT QUERY
        String query = "SELECT " + SQLiteHelper.UID + "," + SQLiteHelper.NAME + " FROM " + SQLiteHelper.TABLE_NAME;
        Cursor c2 = sqdb.rawQuery(query, null);

        while (c2.moveToNext()) {
            int id = c2.getInt(c2.getColumnIndex(SQLiteHelper.UID));
            String name = c2.getString(c2.getColumnIndex(SQLiteHelper.NAME));
            Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name);
        }

        c2.close();

        // CLOSE DATABASE CONNECTIONS
        sqdb.close();
        sqh.close();
    }
}
