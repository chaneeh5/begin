package andexam.ver6.c11_widget;

import andexam.ver6.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class ArrowButton extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrowbutton);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.arrow1:
			Toast.makeText(this, "Arrow1 clicked", 0).show();
			break;
		case R.id.arrow2:
			Toast.makeText(this, "Arrow2 clicked", 0).show();
			break;
		case R.id.arrow3:
			Toast.makeText(this, "Arrow3 clicked", 0).show();
			break;
		}
	}    
}