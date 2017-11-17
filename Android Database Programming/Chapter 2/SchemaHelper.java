package jwei.apps.dataforandroid.ch2;

import java.util.HashSet;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchemaHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "adv_data.db";

    // TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
    private static final int DATABASE_VERSION = 4;

    SchemaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE STUDENTS TABLE
        db.execSQL("CREATE TABLE " + StudentTable.TABLE_NAME + " (" + StudentTable.ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + StudentTable.NAME + " TEXT," + StudentTable.STATE + " TEXT,"
                + StudentTable.GRADE + " INTEGER);");

        // CREATE COURSES TABLE
        db.execSQL("CREATE TABLE " + CourseTable.TABLE_NAME + " (" + CourseTable.ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + CourseTable.NAME + " TEXT);");

        // CREATE CLASSES MAPPING TABLE
        db.execSQL("CREATE TABLE " + ClassTable.TABLE_NAME + " (" + ClassTable.ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + ClassTable.STUDENT_ID + " INTEGER," + ClassTable.COURSE_ID
                + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Upgrading database from version " + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        // KILL PREVIOUS TABLES IF UPGRADED
        db.execSQL("DROP TABLE IF EXISTS " + StudentTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CourseTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ClassTable.TABLE_NAME);

        // CREATE NEW INSTANCE OF SCHEMA
        onCreate(db);
    }

    public long addStudent(String name, String state, int grade) {
        // CREATE A CONTENTVALUE OBJECT
        ContentValues cv = new ContentValues();
        cv.put(StudentTable.NAME, name);
        cv.put(StudentTable.STATE, state);
        cv.put(StudentTable.GRADE, grade);

        // RETRIEVE WRITEABLE DATABASE AND INSERT
        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.insert(StudentTable.TABLE_NAME, StudentTable.NAME, cv);
        return result;
    }

    public long addCourse(String name) {
        ContentValues cv = new ContentValues();
        cv.put(CourseTable.NAME, name);

        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.insert(CourseTable.TABLE_NAME, CourseTable.NAME, cv);
        return result;
    }

    public boolean enrollStudentClass(int studentId, int courseId) {
        ContentValues cv = new ContentValues();
        cv.put(ClassTable.STUDENT_ID, studentId);
        cv.put(ClassTable.COURSE_ID, courseId);

        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.insert(ClassTable.TABLE_NAME, ClassTable.STUDENT_ID, cv);
        return (result >= 0);
    }

    public Cursor getStudentsForCourse(int courseId) {
        SQLiteDatabase sd = getWritableDatabase();

        // WE ONLY NEED TO RETURN STUDENT IDS
        String[] columns = new String[] { ClassTable.STUDENT_ID };

        String[] selectionArgs = new String[] { String.valueOf(courseId) };

        // QUERY CLASS MAP FOR STUDENTS IN COURSE
        Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.COURSE_ID + "= ? ", selectionArgs, null, null,
                null);

        return c;
    }

    public Cursor getCoursesForStudent(int studentId) {
        SQLiteDatabase sd = getWritableDatabase();

        // WE ONLY NEED TO RETURN COURSE IDS
        String[] columns = new String[] { ClassTable.COURSE_ID };

        String[] selectionArgs = new String[] { String.valueOf(studentId) };

        // QUERY CLASS MAP FOR STUDENTS IN COURSE
        Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.STUDENT_ID + "= ? ", selectionArgs, null, null,
                null);

        return c;
    }

    public Set<Integer> getStudentsByGradeForCourse(int courseId, int grade) {
        SQLiteDatabase sd = getWritableDatabase();

        // WE ONLY NEED TO RETURN COURSE IDS
        String[] columns = new String[] { ClassTable.STUDENT_ID };

        String[] selectionArgs = new String[] { String.valueOf(courseId) };

        // QUERY CLASS MAP FOR STUDENTS IN COURSE
        Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.COURSE_ID + "= ? ", selectionArgs, null, null,
                null);
        Set<Integer> returnIds = new HashSet<Integer>();
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex(ClassTable.STUDENT_ID));
            returnIds.add(id);
        }

        // MAKE SECOND QUERY
        columns = new String[] { StudentTable.ID };
        selectionArgs = new String[] { String.valueOf(grade) };

        c = sd.query(StudentTable.TABLE_NAME, columns, StudentTable.GRADE + "= ?", selectionArgs, null, null, null);
        Set<Integer> gradeIds = new HashSet<Integer>();
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex(StudentTable.ID));
            gradeIds.add(id);
        }

        // RETURN INTERSECTION OF ID SETS
        returnIds.retainAll(gradeIds);
        return returnIds;
    }

    public boolean removeStudent(int studentId) {
        SQLiteDatabase sd = getWritableDatabase();
        String[] whereArgs = new String[] { String.valueOf(studentId) };

        // MAKE SURE YOU DELETE ALL CLASSES STUDENT IS SIGNED UP FOR
        sd.delete(ClassTable.TABLE_NAME, ClassTable.STUDENT_ID + "= ? ", whereArgs);

        // THEN DELETE STUDENT
        int result = sd.delete(StudentTable.TABLE_NAME, StudentTable.ID + "= ? ", whereArgs);
        return (result > 0);
    }

    public boolean removeCourse(int courseId) {
        SQLiteDatabase sd = getWritableDatabase();
        String[] whereArgs = new String[] { String.valueOf(courseId) };

        // MAKE SURE YOU REMOVE COURSE FROM ALL STUDENTS ENROLLED
        sd.delete(ClassTable.TABLE_NAME, ClassTable.COURSE_ID + "= ? ", whereArgs);

        // THEN DELETE COURSE
        int result = sd.delete(CourseTable.TABLE_NAME, CourseTable.ID + "= ? ", whereArgs);
        return (result > 0);
    }
}
