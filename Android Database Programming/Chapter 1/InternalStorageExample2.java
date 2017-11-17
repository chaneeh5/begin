package jwei.apps.dataforandroid.ch1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import jwei.apps.dataforandroid.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class InternalStorageExample2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // THE NAME OF THE FILE
        String fileName = "my_file.txt";

        try {
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);

            // READ STRING OF UNKNOWN LENGTH
            StringBuilder sb = new StringBuilder();
            char[] inputBuffer = new char[2048];
            int l;
            // FILL BUFFER WITH DATA
            while ((l = isr.read(inputBuffer)) != -1) {
                sb.append(inputBuffer, 0, l);
            }

            // CONVERT BYTES TO STRING
            String readString = sb.toString();
            Log.i("LOG_TAG", "Read string: " + readString);

            // CAN ALSO DELETE THE FILE
            deleteFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
