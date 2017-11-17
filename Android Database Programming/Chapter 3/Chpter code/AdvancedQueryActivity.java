package jwei.apps.dataforandroid.ch3;

import jwei.apps.dataforandroid.R;
import jwei.apps.dataforandroid.ch2.ClassTable;
import jwei.apps.dataforandroid.ch2.CourseTable;
import jwei.apps.dataforandroid.ch2.SchemaHelper;
import jwei.apps.dataforandroid.ch2.StudentTable;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;

public class AdvancedQueryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SchemaHelper sch = new SchemaHelper(this);
        SQLiteDatabase sqdb = sch.getWritableDatabase();

        /*
         * ORDER BY Clause
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        Cursor c = sqdb.rawQuery("SELECT * from " + StudentTable.TABLE_NAME + " ORDER BY " + StudentTable.STATE
                + " ASC", null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        c = sqdb.query(StudentTable.TABLE_NAME, null, null, null, null, null, StudentTable.STATE + " ASC");
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.NAME);
            int colid2 = c.getColumnIndex(StudentTable.STATE);
            String name = c.getString(colid);
            String state = c.getString(colid2);
            System.out.println("GOT STUDENT " + name + " FROM " + state);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        String query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, null, null, null, null,
                StudentTable.STATE + " ASC", null);
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
         * GROUP BY Clause
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        String colName = "COUNT(" + StudentTable.STATE + ")";
        c = sqdb.rawQuery("SELECT " + StudentTable.STATE + "," + colName + " from " + StudentTable.TABLE_NAME
                + " GROUP BY " + StudentTable.STATE, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            int colid2 = c.getColumnIndex(colName);
            String state = c.getString(colid);
            int count = c.getInt(colid2);
            System.out.println("STATE " + state + " HAS COUNT " + count);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        c = sqdb.query(StudentTable.TABLE_NAME, new String[] { StudentTable.STATE, colName }, null, null,
                StudentTable.STATE, null, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            int colid2 = c.getColumnIndex(colName);
            String state = c.getString(colid);
            int count = c.getInt(colid2);
            System.out.println("STATE " + state + " HAS COUNT " + count);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, new String[] { StudentTable.STATE,
                colName }, null, StudentTable.STATE, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            int colid2 = c.getColumnIndex(colName);
            String state = c.getString(colid);
            int count = c.getInt(colid2);
            System.out.println("STATE " + state + " HAS COUNT " + count);
        }

        /*
         * HAVING Filter
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        colName = "COUNT(" + StudentTable.STATE + ")";
        c = sqdb.rawQuery("SELECT " + StudentTable.STATE + "," + colName + " from " + StudentTable.TABLE_NAME
                + " GROUP BY " + StudentTable.STATE + " HAVING " + colName + " > 1", null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            int colid2 = c.getColumnIndex(colName);
            String state = c.getString(colid);
            int count = c.getInt(colid2);
            System.out.println("STATE " + state + " HAS COUNT " + count);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        c = sqdb.query(StudentTable.TABLE_NAME, new String[] { StudentTable.STATE, colName }, null, null,
                StudentTable.STATE, colName + " > 1", null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            int colid2 = c.getColumnIndex(colName);
            String state = c.getString(colid);
            int count = c.getInt(colid2);
            System.out.println("STATE " + state + " HAS COUNT " + count);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, new String[] { StudentTable.STATE,
                colName }, null, StudentTable.STATE, colName + " > 1", null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            int colid2 = c.getColumnIndex(colName);
            String state = c.getString(colid);
            int count = c.getInt(colid2);
            System.out.println("STATE " + state + " HAS COUNT " + count);
        }

        /*
         * SQL Functions - MIN/MAX/AVG
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        colName = "MIN(" + StudentTable.GRADE + ")";
        c = sqdb.rawQuery("SELECT " + colName + " from " + StudentTable.TABLE_NAME, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(colName);
            int minGrade = c.getInt(colid);
            System.out.println("MIN GRADE " + minGrade);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        colName = "MAX(" + StudentTable.GRADE + ")";
        c = sqdb.query(StudentTable.TABLE_NAME, new String[] { colName }, null, null, null, null, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(colName);
            int maxGrade = c.getInt(colid);
            System.out.println("MAX GRADE " + maxGrade);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        colName = "AVG(" + StudentTable.GRADE + ")";
        query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, new String[] { colName }, null,
                null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(colName);
            double avgGrade = c.getDouble(colid);
            System.out.println("AVG GRADE " + avgGrade);
        }

        /*
         * SQL Functions - UPPER/LOWER
         */

        System.out.println("METHOD 1");

        // METHOD #1 - SQLITEDATABASE RAWQUERY()
        colName = "UPPER(" + StudentTable.NAME + ")";
        c = sqdb.rawQuery("SELECT " + colName + " from " + StudentTable.TABLE_NAME, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(colName);
            String upperName = c.getString(colid);
            System.out.println("GOT STUDENT " + upperName);
        }

        System.out.println("METHOD 2");

        // METHOD #2 - SQLITEDATABASE QUERY()
        colName = "LOWER(" + StudentTable.NAME + ")";
        c = sqdb.query(StudentTable.TABLE_NAME, new String[] { colName }, null, null, null, null, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(colName);
            String lowerName = c.getString(colid);
            System.out.println("GOT STUDENT " + lowerName);
        }

        System.out.println("METHOD 3");

        // METHOD #3 - SQLITEQUERYBUILDER
        colName = "SUBSTR(" + StudentTable.NAME + ",1,4)";
        query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, new String[] { colName }, null,
                null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(colName);
            String subName = c.getString(colid);
            System.out.println("GOT STUDENT " + subName);
        }

        /*
         * SQL JOINS
         */

        SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();

        // NOTICE THE SYNTAX FOR COLUMNS IN JOIN QUERIES
        String courseIdCol = CourseTable.TABLE_NAME + "." + CourseTable.ID;
        String classCourseIdCol = ClassTable.TABLE_NAME + "." + ClassTable.COURSE_ID;
        String classIdCol = ClassTable.TABLE_NAME + "." + ClassTable.ID;

        sqb.setTables(ClassTable.TABLE_NAME + " INNER JOIN " + CourseTable.TABLE_NAME + " ON (" + classCourseIdCol
                + " = " + courseIdCol + ")");
        String[] cols = new String[] { classIdCol, ClassTable.COURSE_ID, CourseTable.NAME };
        query = sqb.buildQuery(cols, null, null, null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(cols[0]);
            int colid2 = c.getColumnIndex(cols[1]);
            int colid3 = c.getColumnIndex(cols[2]);
            int rowId = c.getInt(colid);
            int courseId = c.getInt(colid2);
            String courseName = c.getString(colid3);
            System.out.println(rowId + " || COURSE ID " + courseId + " || " + courseName);
        }
    }
}
