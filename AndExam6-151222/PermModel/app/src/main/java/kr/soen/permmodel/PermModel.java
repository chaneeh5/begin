package kr.soen.permmodel;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PermModel extends AppCompatActivity {
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

