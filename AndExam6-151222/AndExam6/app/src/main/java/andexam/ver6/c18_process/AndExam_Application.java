package andexam.ver6.c18_process;

import android.app.*;

public class AndExam_Application extends Application {
	private int mMode;
	static final int BEGINNER = 0;
	static final int PROFESSIONAL = 1;

	public void onCreate() {
		super.onCreate();
		mMode = BEGINNER;
	}

	public void onTerminate() {
		super.onTerminate();
	}
	
	public int getMode() {
		return mMode;
	}
	
	public void setMode(int aMode) {
		mMode = aMode;
	}
}