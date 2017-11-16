package andexam.ver6.c29_br;

import andexam.ver6.*;
import android.content.*;

public class FreeBR extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Intent intent2 = new Intent(context, 
				andexam.ver6.c28_network.AsyncDownHtml.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
	}
}