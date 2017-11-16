package andexam.ver6.c18_process;

import andexam.ver6.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class ApplicationTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.applicationtest);

		UpdateNowMode();
	}

	void UpdateNowMode() {
		TextView txtMode = (TextView)findViewById(R.id.mode);
		AndExam_Application app = (AndExam_Application)getApplication();
		if (app.getMode() == AndExam_Application.BEGINNER) {
			txtMode.setText("현재 모드 : 초보자 모드");
		} else {
			txtMode.setText("현재 모드 : 숙련자 모드");
		}
	}

	public void mOnClick(View v) {
		AndExam_Application app = (AndExam_Application)getApplication();
		switch (v.getId()) {
		case R.id.beginner:
			app.setMode(AndExam_Application.BEGINNER);
			break;
		case R.id.professional:
			app.setMode(AndExam_Application.PROFESSIONAL);
			break;
		}
		UpdateNowMode();
	}
}


