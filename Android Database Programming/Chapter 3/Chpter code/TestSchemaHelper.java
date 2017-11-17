package jwei.apps.dataforandroid.ch3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TestSchemaHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "test_data.db";

    // TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
    private static final int DATABASE_VERSION = 1;

    public TestSchemaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE STUDENTS TABLE
        db.execSQL("CREATE TABLE " + TestTable.TABLE_NAME + " (" + TestTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TestTable.NAME + " TEXT," + TestTable.STATE + " TEXT," + TestTable.INCOME + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Upgrading database from version " + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        // KILL PREVIOUS TABLES IF UPGRADED
        db.execSQL("DROP TABLE IF EXISTS " + TestTable.TABLE_NAME);

        // CREATE NEW INSTANCE OF SCHEMA
        onCreate(db);
    }

    public long addRow(String name, String state, int income) {
        // CREATE A CONTENTVALUE OBJECT
        ContentValues cv = new ContentValues();
        cv.put(TestTable.NAME, name);
        cv.put(TestTable.STATE, state);
        cv.put(TestTable.INCOME, income);

        // RETRIEVE WRITEABLE DATABASE AND INSERT
        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.insert(TestTable.TABLE_NAME, TestTable.NAME, cv);
        return result;
    }
}
