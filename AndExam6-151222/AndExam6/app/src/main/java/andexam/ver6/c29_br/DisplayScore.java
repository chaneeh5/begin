package andexam.ver6.c29_br;

import andexam.ver6.*;
import android.content.*;
import android.widget.*;

public class DisplayScore extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Kia : Lotte = 2 : 3", 
				Toast.LENGTH_SHORT).show();
	}
}
