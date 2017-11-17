package jwei.apps.dataforandroid.ch2;

import java.util.Set;

import jwei.apps.dataforandroid.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

public class SchemaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SchemaHelper sh = new SchemaHelper(this);

        // ADD STUDENTS AND RETURN THEIR IDS
        long sid1 = sh.addStudent("Jason Wei", "IL", 12);
        long sid2 = sh.addStudent("Du Chung", "AR", 12);
        long sid3 = sh.addStudent("George Tang", "CA", 11);
        long sid4 = sh.addStudent("Mark Bocanegra", "CA", 11);
        long sid5 = sh.addStudent("Bobby Wei", "IL", 12);

        // ADD COURSES AND RETURN THEIR IDS
        long cid1 = sh.addCourse("Math51");
        long cid2 = sh.addCourse("CS106A");
        long cid3 = sh.addCourse("Econ1A");

        // ENROLL STUDENTS IN CLASSES
        sh.enrollStudentClass((int) sid1, (int) cid1);
        sh.enrollStudentClass((int) sid1, (int) cid2);
        sh.enrollStudentClass((int) sid2, (int) cid2);
        sh.enrollStudentClass((int) sid3, (int) cid1);
        sh.enrollStudentClass((int) sid3, (int) cid2);
        sh.enrollStudentClass((int) sid4, (int) cid3);
        sh.enrollStudentClass((int) sid5, (int) cid2);

        // GET STUDENTS FOR COURSE
        Cursor c = sh.getStudentsForCourse((int) cid2);
        while (c.moveToNext()) {
            int sid = c.getInt(c.getColumnIndex(ClassTable.STUDENT_ID));
            System.out.println("STUDENT " + sid + " IS ENROLLED IN COURSE " + cid2);
        }

        // GET STUDENTS FOR COURSE AND FILTER BY GRADE
        Set<Integer> sids = sh.getStudentsByGradeForCourse((int) cid2, 11);
        for (Integer sid : sids) {
            System.out.println("STUDENT " + sid + " OF GRADE 11 IS ENROLLED IN COURSE " + cid2);
        }

        // GET CLASSES IM TAKING
        c = sh.getCoursesForStudent((int) sid1);
        while (c.moveToNext()) {
            int cid = c.getInt(c.getColumnIndex(ClassTable.COURSE_ID));
            System.out.println("STUDENT " + sid1 + " IS ENROLLED IN COURSE " + cid);
        }

        // TRY REMOVING A COURSE
        sh.removeCourse((int) cid1);
        
        System.out.println("------------------------------");

        // SEE IF REMOVAL KEPT SCHEMA CONSISTENT
        c = sh.getCoursesForStudent((int) sid1);
        while (c.moveToNext()) {
            int cid = c.getInt(c.getColumnIndex(ClassTable.COURSE_ID));
            System.out.println("STUDENT " + sid1 + " IS ENROLLED IN COURSE " + cid);
        }

    }

}
