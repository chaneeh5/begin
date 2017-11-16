package andexam.ver6.c36_tel;

import andexam.ver6.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.telephony.*;
import android.widget.*;

public class ReceiveSms extends Activity {
	TextView mResult;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receivesms);

		mResult = (TextView)findViewById(R.id.result);
	}

	protected void onResume() {
		super.onResume();
		registerReceiver(mReceiverBR, new IntentFilter(
				"android.provider.Telephony.SMS_RECEIVED"));
	}

	protected void onPause() {
		super.onPause();
		unregisterReceiver(mReceiverBR);
	}

	BroadcastReceiver mReceiverBR = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String result = "";
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[])bundle.get("pdus");
				for (int i = 0; i < pdus.length; i++) {
					SmsMessage msg = SmsMessage.createFromPdu((byte [])pdus[i]);
					result += "from " + msg.getOriginatingAddress() + " => " +
					msg.getMessageBody() + "\n";
				}
				mResult.setText("메시지 : " + result);
			}
		}
	};
}
