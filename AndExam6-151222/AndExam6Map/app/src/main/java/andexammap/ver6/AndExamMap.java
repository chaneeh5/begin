package andexammap.ver6;

import java.util.*;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import andexammap.ver6.c32_map.*;

public class AndExamMap extends Activity {
    static final int SETTING_ACTIVITY = 1;
    // 예제가 있는 시작 챕터. 스피너의 첨자에 이 값을 더해야 장 번호가 된다.
    static final int START_CHAPTER = 32;
    class Example {
        Example(Class<?> acls, String aDesc) {
            cls = acls;
            Name = cls.getSimpleName();
            Desc = aDesc;
        }
        Class<?> cls;
        String Name;
        String Desc;
    }

    ArrayList<Example> arExample = new ArrayList<Example>();

    // 요청한 장의 예제들을 배열에 채운다.
    void FillExample(int chapter) {
        arExample.clear();

        switch(chapter) {
            case 32: // 맵서비스
                arExample.add(new Example(GetProvider.class, "위치 제공자 조사"));
                arExample.add(new Example(ReadLocation.class, "현재 위치 읽기"));
                arExample.add(new Example(LastKnown.class, "최근 위치 조사"));
                arExample.add(new Example(LocationConvert.class, "포맷 변환"));
                arExample.add(new Example(LocationAlert.class, "도착 알림"));
                arExample.add(new Example(GeoCoding.class, "지오 코딩"));
                arExample.add(new Example(ViewLocation.class, "63빌딩 위치 보기"));
                arExample.add(new Example(GoogleMapTest.class, "구글맵 테스트"));
                arExample.add(new Example(MyLocation.class, "현재 위치 보기"));
                arExample.add(new Example(MapType.class, "지도 형태 선택"));
                arExample.add(new Example(CameraMap.class, "위치 지정"));
                arExample.add(new Example(MapUi.class, "지도 UI 설정"));
                arExample.add(new Example(MapShape.class, "지도에 도형 배치"));
                break;
        }
    }

    // 예제는 4장부터 제공된다. 4(START_CHAPTER)장이 첨자 0번이다.
    String[] arChapter = {
            "32장 맵 서비스",
    };

    ArrayAdapter<CharSequence> mAdapter;
    ListView mExamList;
    Spinner mSpinner;
    boolean mInitSelection = true;
    int mFontSize;
    int mBackType;
    boolean mDescSide;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.andexam);

        mExamList = (ListView)findViewById(R.id.examlist);
        mSpinner = (Spinner)findViewById(R.id.spinnerchapter);
        mSpinner.setPrompt("장을 선택하세요.");

        mAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_item, arChapter);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 최초 전개시에도 Selected가 호출되는데 이때는 프레퍼런스에서 최후 장을 찾아 로드한다.
                // 이후부터는 사용자가 선택한 장을 로드한다.
                if (mInitSelection) {
                    mInitSelection = false;
                    SharedPreferences pref = getSharedPreferences("AndExam", 0);
                    int lastchapter = pref.getInt("LastChapter", START_CHAPTER);
                    mSpinner.setSelection(lastchapter - START_CHAPTER);
                    ChangeChapter(lastchapter);
                } else {
                    // 장을 변경할 때마다 프레퍼런스에 기록한다.
                    int chapter = position + START_CHAPTER;
                    ChangeChapter(chapter);
                    SharedPreferences pref = getSharedPreferences("AndExam", 0);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putInt("LastChapter", chapter);
                    edit.commit();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ReadOption();

        // 자동실행 옵션의 디폴트는 false로 설정한다. 한 예제만 반복적으로 테스트할 때 이 옵션을
        // 사용하되 예외 처리가 어려우므로 왠만하면 사용하지 않는 것이 좋다.
        boolean bRunLast = false;
        if (bRunLast) {
            SharedPreferences pref = getSharedPreferences("AndExam", 0);
            int pkg = pref.getInt("LastChapter", START_CHAPTER);
            int pos = pref.getInt("LastPosition", 0);
            ChangeChapter(pkg);
            Intent intent = new Intent(this, arExample.get(pos).cls);
            startActivity(intent);
        }
    }

    public void ReadOption() {
        SharedPreferences pref = getSharedPreferences("AndExam", 0);
        mFontSize = pref.getInt("mFontSize", 1);
        mBackType = pref.getInt("mBackType", 0);
        mDescSide = pref.getBoolean("mDescSide", false);

        if (mBackType != 0) {
            mExamList.setBackgroundColor(0xff101010);
            mExamList.setDivider(new ColorDrawable(0xff606060));
            mExamList.setDividerHeight(1);
        } else {
            mExamList.setBackgroundColor(0xffe0e0e0);
            mExamList.setDivider(new ColorDrawable(0xff404040));
            mExamList.setDividerHeight(1);
        }
    }

    // 장을 변경한다. 인수로 전달되는 chapter는 첨자가 아니라 장 번호이다.
    public void ChangeChapter(int chapter) {
        FillExample(chapter);
        ExamListAdapter Adapter = new ExamListAdapter(this);
        mExamList.setAdapter(Adapter);

        final Context ctx = this;
        mExamList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences pref = getSharedPreferences("AndExam", 0);
                SharedPreferences.Editor edit = pref.edit();
                edit.putInt("LastPosition", position);
                edit.commit();
                Intent intent = new Intent(ctx, arExample.get(position).cls);
                startActivity(intent);
            }
        });
    }

    public void mOnClick(View v) {
        SharedPreferences pref = getSharedPreferences("AndExam", 0);
        int lastchapter = pref.getInt("LastChapter", START_CHAPTER);
        switch (v.getId()) {
            case R.id.btnprev:
                if (lastchapter !=  START_CHAPTER) {
                    lastchapter--;
                    mSpinner.setSelection(lastchapter - START_CHAPTER);
                }
                break;
            case R.id.btnnext:
                if (lastchapter != arChapter.length - 1 + START_CHAPTER) {
                    lastchapter++;
                    mSpinner.setSelection(lastchapter - START_CHAPTER);
                }
                break;
        }
    }

    //어댑터 클래스
    class ExamListAdapter extends BaseAdapter {
        Context maincon;
        LayoutInflater Inflater;

        public ExamListAdapter(Context context) {
            maincon = context;
            Inflater = (LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return arExample.size();
        }

        public String getItem(int position) {
            return arExample.get(position).Name;
        }

        public long getItemId(int position) {
            return position;
        }

        // 각 항목의 뷰 생성
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = Inflater.inflate(R.layout.andexamlist, parent, false);
            }

            LinearLayout examlayout = (LinearLayout)convertView.findViewById(R.id.examlayout);
            TextView txt1 = (TextView)convertView.findViewById(R.id.text1);
            TextView txt2 = (TextView)convertView.findViewById(R.id.text2);

            if (mDescSide) {
                examlayout.setOrientation(LinearLayout.HORIZONTAL);
            }

            if (mBackType != 0) {
                examlayout.setBackgroundResource(R.drawable.exambackdark);
                txt1.setTextColor(Color.WHITE);
                txt2.setTextColor(Color.LTGRAY);
            }

            switch (mFontSize) {
                case 0:
                    txt1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    txt2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 9);
                    break;
                case 1:
                    txt1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                    txt2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
                    break;
                case 2:
                    txt1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                    txt2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    break;
            }

            txt1.setText(arExample.get(position).Name);
            txt2.setText(arExample.get(position).Desc);

            return convertView;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"소개");
        menu.add(0,2,0,"옵션");
        menu.add(0,3,0,"종료");

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                new AlertDialog.Builder(this)
                        .setTitle("프로그램 소개")
                        .setMessage("안드로이드 프로그래밍 정복(한빛미디어)의 예제 모음 프로그램입니다.\n" +
                                "상단의 스피너에서 패키지를 선택하고 목록에서 예제를 선택하십시오.")
                        .setIcon(R.drawable.andexam)
                        .setPositiveButton("닫기", null)
                        .show();
                return true;
            case 2:
                startActivityForResult(new Intent(this, AndExamSetting.class), SETTING_ACTIVITY);
                return true;
            case 3:
                finish();
                System.exit(0);
                return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SETTING_ACTIVITY:
                if (resultCode != RESULT_OK) return;
                ReadOption();
                SharedPreferences pref = getSharedPreferences("AndExam", 0);
                int lastchapter = pref.getInt("LastChapter", START_CHAPTER);
                ChangeChapter(lastchapter);
                break;
        }
    }
}

