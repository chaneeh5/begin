package jwei.apps.dataforandroid.ch3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jwei.apps.dataforandroid.R;
import jwei.apps.dataforandroid.ch2.StudentTable;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;

public class PerformanceActivity extends Activity {

    private String[] STATES = new String[] { "IL", "CA", "AR", "NY", "PA" };

    private char[] ALPHA = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TestSchemaHelper sch = new TestSchemaHelper(this);
        SQLiteDatabase sqdb = sch.getWritableDatabase();

        boolean shouldLoad = false;
        if (shouldLoad) {
            int l = ALPHA.length;
            int s = STATES.length;
            Random r = new Random();
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    for (int k = 0; k < l; k++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(ALPHA[i]);
                        sb.append(ALPHA[j]);
                        sb.append(ALPHA[k]);
                        String name = sb.toString();
                        int ind = r.nextInt(s);
                        String state = STATES[ind];
                        int income = r.nextInt(1000000);
                        sch.addRow(name, state, income);
                    }
                }
            }
        }

        /*
         * TEST PERFORMANCE HERE
         */
        // TEST WHERE FILTER PERFORMANCE //

        // SQL OPTIMIZED
        long start = System.nanoTime();
        String query = SQLiteQueryBuilder.buildQueryString(false, TestTable.TABLE_NAME,
                new String[] { TestTable.NAME }, TestTable.INCOME + " > 500000", null, null, null, null);
        System.out.println(query);
        Cursor c = sqdb.rawQuery(query, null);
        int numRows = 0;
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(TestTable.NAME);
            String name = c.getString(colid);
            numRows++;
        }
        System.out.println("RETRIEVED " + numRows);
        System.out.println((System.nanoTime() - start) / 1000000 + " MILLISECONDS");
        c.close();

        // JAVA OPTIMIZED
        start = System.nanoTime();
        query = SQLiteQueryBuilder.buildQueryString(false, TestTable.TABLE_NAME, new String[] { TestTable.NAME,
                TestTable.INCOME }, null, null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        numRows = 0;
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(TestTable.NAME);
            int colid2 = c.getColumnIndex(TestTable.INCOME);
            String name = c.getString(colid);
            int income = c.getInt(colid2);
            if (income > 500000) {
                numRows++;
            }
        }
        System.out.println("RETRIEVED " + numRows);
        System.out.println((System.nanoTime() - start) / 1000000 + " MILLISECONDS");
        c.close();
        // TEST GROUP BY PERFORMANCE //

        // SQL OPTIMIZED
        start = System.nanoTime();
        String colName = "COUNT(" + TestTable.STATE + ")";
        query = SQLiteQueryBuilder.buildQueryString(false, TestTable.TABLE_NAME, new String[] { TestTable.STATE,
                colName }, null, TestTable.STATE, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(StudentTable.STATE);
            int colid2 = c.getColumnIndex(colName);
            String state = c.getString(colid);
            int count = c.getInt(colid2);
            System.out.println("STATE " + state + " HAS COUNT " + count);
        }
        System.out.println((System.nanoTime() - start) / 1000000 + " MILLISECONDS");
        c.close();

        // JAVA OPTIMIZED
        start = System.nanoTime();
        query = SQLiteQueryBuilder.buildQueryString(false, TestTable.TABLE_NAME, new String[] { TestTable.STATE },
                null, null, null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        Map<String, Integer> map = new HashMap<String, Integer>();
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(TestTable.STATE);
            String state = c.getString(colid);
            if (map.containsKey(state)) {
                int curValue = map.get(state);
                map.put(state, curValue + 1);
            } else {
                map.put(state, 1);
            }
        }

        for (String key : map.keySet()) {
            System.out.println("STATE " + key + " HAS COUNT " + map.get(key));
        }
        System.out.println((System.nanoTime() - start) / 1000000 + " MILLISECONDS");
        c.close();

        // TEST AVERAGE PERFORMANCE //

        // SQL OPTIMIZED
        start = System.nanoTime();
        colName = "AVG(" + TestTable.INCOME + ")";
        query = SQLiteQueryBuilder.buildQueryString(false, TestTable.TABLE_NAME, new String[] { colName }, null, null,
                null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(colName);
            double avgGrade = c.getDouble(colid);
            System.out.println("AVG INCOME " + avgGrade);
        }
        System.out.println((System.nanoTime() - start) / 1000000 + " MILLISECONDS");
        c.close();

        // JAVA OPTIMIZED
        start = System.nanoTime();
        colName = TestTable.INCOME;
        query = SQLiteQueryBuilder.buildQueryString(false, TestTable.TABLE_NAME, new String[] { colName }, null, null,
                null, null, null);
        System.out.println(query);
        c = sqdb.rawQuery(query, null);
        double sumIncomes = 0.0;
        double numIncomes = 0.0;
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(colName);
            int income = c.getInt(colid);
            sumIncomes += income;
            numIncomes++;
        }

        System.out.println("AVG INCOME " + sumIncomes / numIncomes);
        System.out.println((System.nanoTime() - start) / 1000000 + " MILLISECONDS");
        c.close();
    }
}
