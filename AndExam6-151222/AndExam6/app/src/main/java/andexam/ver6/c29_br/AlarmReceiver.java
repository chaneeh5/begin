package andexam.ver6.c29_br;

import andexam.ver6.*;
import android.content.*;
import android.widget.*;

public class AlarmReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "It's time to start", 
				Toast.LENGTH_LONG).show();
	}
}