package andexam.ver6.c28_network;

import andexam.ver6.*;
import android.app.*;
import android.net.*;
import android.os.*;
import android.widget.*;

public class ConMgr extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conmgr);

		TextView result = (TextView)findViewById(R.id.result);
		String sResult = "";
		ConnectivityManager mgr = (ConnectivityManager)
			getSystemService(CONNECTIVITY_SERVICE);
		
		NetworkInfo[] ani = mgr.getAllNetworkInfo();
		for (NetworkInfo n : ani) {
			sResult += (n.toString() + "\n\n");
		}

		NetworkInfo ni = mgr.getActiveNetworkInfo();
		if (ni != null) {
			sResult += ("Active : \n" + ni.toString() + "\n");
			result.setText(sResult);
		}
	}
}

