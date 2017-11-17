package jwei.apps.dataforandroid.ch3;

import jwei.apps.dataforandroid.R;
import jwei.apps.dataforandroid.ch2.SchemaHelper;
import jwei.apps.dataforandroid.ch2.StudentTable;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;

public class BasicQueryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SchemaHelper sch = new SchemaHelper(this);
        SQLiteDatabase sqdb = sch.getWritableDatabase();

        /*
         * SELECT Query
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        Cursor c = sqdb.rawQuery("SELECT * from " + StudentTable.TABLE_NAME, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            String name = c.getString(colid);
            System.out.println("GOT STUDENT " + name);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        c = sqdb.query(StudentTable.TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            String name = c.getString(colid);
            System.out.println("GOT STUDENT " + name);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        String query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, null, null, null, null,
                null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            String name = c.getString(colid);
            System.out.println("GOT STUDENT " + name);
        }

        /*
         * SELECT COLUMNS Query
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        c = sqdb.rawQuery(
                "SELECT " + StudentTable.NAME + "," + StudentTable.STATE + " from " + StudentTable.TABLE_NAME, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        c = sqdb.query(StudentTable.TABLE_NAME, new String[] { StudentTable.NAME, StudentTable.STATE }, null, null,
                null, null, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, new String[] { StudentTable.NAME,
                StudentTable.STATE }, null, null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        /*
         * WHERE Filter
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        c = sqdb.rawQuery("SELECT * from " + StudentTable.TABLE_NAME + " WHERE " + StudentTable.STATE + "= ? ",
                new String[] { "IL" });
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        c = sqdb.query(StudentTable.TABLE_NAME, null, StudentTable.STATE + "= ? ", new String[] { "IL" }, null, null,
                null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, null, StudentTable.STATE + "='IL'",
                null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        /*
         * AND/OR Clauses
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        c = sqdb.rawQuery("SELECT * from " + StudentTable.TABLE_NAME + " WHERE " + StudentTable.STATE + "= ? OR "
                + StudentTable.STATE + "= ?", new String[] { "IL", "AR" });
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        c = sqdb.query(StudentTable.TABLE_NAME, null, StudentTable.STATE + "= ? OR " + StudentTable.STATE + "= ?",
                new String[] { "IL", "AR" }, null, null, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, null, StudentTable.STATE
                + "='IL' OR " + StudentTable.STATE + "='AR'", null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        /*
         * DISTINCT Clause
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        c = sqdb.rawQuery("SELECT DISTINCT " + StudentTable.STATE + " from " + StudentTable.TABLE_NAME, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            String state = c.getString(colid);
            System.out.println("GOT STATE " + state);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        // SWITCH TO MORE GENERAL QUERY() METHOD
        c = sqdb.query(true, StudentTable.TABLE_NAME, new String[] { StudentTable.STATE }, null, null, null, null,
                null, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            String state = c.getString(colid);
            System.out.println("GOT STATE " + state);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        query = SQLiteQueryBuilder.buildQueryString(true, StudentTable.TABLE_NAME, new String[] { StudentTable.STATE },
                null, null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            String state = c.getString(colid);
            System.out.println("GOT STATE " + state);
        }

        /*
         * LIMIT Clause
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        c = sqdb.rawQuery("SELECT * from " + StudentTable.TABLE_NAME + " LIMIT 1,3", null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            String name = c.getString(colid);
            System.out.println("GOT STUDENT " + name);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        // SWITCH TO MORE GENERAL QUERY() METHOD
        c = sqdb.query(false, StudentTable.TABLE_NAME, null, null, null, null, null, null, "3");
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            String name = c.getString(colid);
            System.out.println("GOT STUDENT " + name);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, null, null, null, null, null, "3");
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            String name = c.getString(colid);
            System.out.println("GOT STUDENT " + name);
        }
    }
}
