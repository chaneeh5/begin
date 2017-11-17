package jwei.apps.dataforandroid.ch6;

import jwei.apps.dataforandroid.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.SimpleCursorAdapter;

public class SimpleContactsActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        // MAKE QUERY TO CONTACT CONTENTPROVIDER
        String[] projections = new String[] { Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER, Phone.TYPE };
        Cursor c = getContentResolver().query(Phone.CONTENT_URI, projections, null, null, null);
        startManagingCursor(c);

        // THE DESIRED COLUMNS TO BE BOUND
        String[] columns = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER, Phone.TYPE };

        // THE XML DEFINED VIEWS FOR EACH FIELD TO BE BOUND TO
        int[] to = new int[] { R.id.name_entry, R.id.number_entry, R.id.number_type_entry };

        // CREATE ADAPTER USING CURSOR POINTING TO DESIRED DATA
        SimpleCursorAdapter cAdapter = new SimpleCursorAdapter(this, R.layout.list_entry, c, columns, to);

        // SET THIS ADAPTER AS YOUR LIST ACTIVITY'S ADAPTER
        this.setListAdapter(cAdapter);
    }
}
