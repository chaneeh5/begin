package andexam.ver6.c32_map;

import andexam.ver6.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.widget.*;

public class GetProvider extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getprovider);

		// 결과 출력
		TextView EditResult =(TextView)findViewById(R.id.result);
		EditResult.setText("맵 관련 예제는 에뮬레이터에서 실행되지 않으므로 AndExam6Map 예제를 실장비에서 실행하십시오.");
	}
}