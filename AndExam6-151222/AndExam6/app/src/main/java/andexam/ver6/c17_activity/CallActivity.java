package andexam.ver6.c17_activity;

import andexam.ver6.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class CallActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callactivity);
	}

	public void mOnClick(View v) {
		Intent intent = new Intent(this, SubActivity.class);
		startActivity(intent);
	}
}
