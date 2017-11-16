package andexam.ver6.c29_br;

import andexam.ver6.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class DetectFree extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detectfree);
	}

	public void mOnClick(View v) {
		Intent intent = new Intent();
		intent.setAction("andexam.ver6.FREEWIFI");
		sendBroadcast(intent);
	}
}