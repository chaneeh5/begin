package andexam.ver6.c07_output;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import andexam.ver6.R;

// 퍼미션 모델 테스트 예제입니다. 별도의 프로젝트로 실습해야 하므로 분리된 프로젝트로 따로 제공됩니다.
// 두번째, 세번째 프로젝트는 직접 실습해 볼 사람을 위해 입력 편의를 위해 소스만 제공하니
// 프로젝트를 직접 생성한 후 레이아웃과 소스를 붙여 넣어 실습하십시오.
public class PermModel extends Activity {
    TextView mResult;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permmodel);
        mResult = (TextView)findViewById(R.id.result);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnread:
                outContact();
                break;
            case R.id.btnreset:
                mResult.setText("주소록");
                break;
        }
    }

    void outContact() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int nameidx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        if (cursor.moveToNext()) {
            mResult.setText(cursor.getString(nameidx));
        } else {
            mResult.setText("주소록이 비어 있습니다.");
        }
        cursor.close();
    }
}

/* PermModel2 소스
public class PermModel2 extends AppCompatActivity {
    TextView mResult;
    final int READ_CONTACT_CODE = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permmodel);
        mResult = (TextView)findViewById(R.id.result);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnread:
                tryOutContact();
                break;
            case R.id.btnreset:
                mResult.setText("주소록");
                break;
        }
    }

    void tryOutContact() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED) {
            outContact();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACT_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode) {
            case READ_CONTACT_CODE:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    outContact();
                }
        }
    }

    void outContact() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int nameidx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        if (cursor.moveToNext()) {
            mResult.setText(cursor.getString(nameidx));
        } else {
            mResult.setText("주소록이 비어 있습니다.");
        }
        cursor.close();
    }
}
*/

/* PermModel3 소스
public class PermModel3 extends AppCompatActivity {
    TextView mResult;
    final int READ_CONTACT_CODE = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permmodel);
        mResult = (TextView)findViewById(R.id.result);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnread:
                tryOutContact();
                break;
            case R.id.btnreset:
                mResult.setText("주소록");
                break;
        }
    }

    void tryOutContact() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "허가된 상태", Toast.LENGTH_SHORT).show();
            outContact();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                new AlertDialog.Builder(this)
                        .setMessage("이 프로그램이 원활하게 동작하기 위해서는 " +
                                "주소록 읽기 퍼미션이 꼭 필요합니다." +
                                "퍼미션을 허가해 주세요.")
                        .setTitle("제발~~")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ActivityCompat.requestPermissions(PermModel3.this,
                                        new String[]{Manifest.permission.READ_CONTACTS},
                                        READ_CONTACT_CODE);
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
            } else {
                Toast.makeText(this, "허가된 상태가 아니어서 퍼미션 요청",
                        Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        READ_CONTACT_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode) {
            case READ_CONTACT_CODE:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "사용자가 퍼미션 허가함",
                            Toast.LENGTH_SHORT).show();
                    outContact();
                } else {
                    Toast.makeText(this, "사용자가 퍼미션 거부함",
                            Toast.LENGTH_SHORT).show();
                    // todo : 다른 대책을 찾거나 에러 처리한다.
                }
        }
    }

    void outContact() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int nameidx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        if (cursor.moveToNext()) {
            mResult.setText(cursor.getString(nameidx));
        } else {
            mResult.setText("주소록이 비어 있습니다.");
        }
        cursor.close();
    }
}
*/