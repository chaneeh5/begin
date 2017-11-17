package jwei.apps.dataforandroid.ch6;

import jwei.apps.dataforandroid.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.ListView;

public class CustomContactsActivity extends ListActivity {

    private CustomContactsAdapter cAdapter;

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

        // CREATE CUSTOM ADAPTER
        cAdapter = new CustomContactsAdapter(this, R.layout.list_entry, c, columns, to);

        // SET THIS ADAPTER AS YOUR LIST ACTIVITY'S ADAPTER
        this.setListAdapter(cAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor c = (Cursor) cAdapter.getItem(position);

        int nameCol = c.getColumnIndex(Phone.DISPLAY_NAME);
        int numCol = c.getColumnIndex(Phone.NUMBER);
        int typeCol = c.getColumnIndex(Phone.TYPE);

        String name = c.getString(nameCol);
        String number = c.getString(numCol);
        int type = c.getInt(typeCol);

        System.out.println("CLICKED ON " + name + " " + number + " " + type);
    }
}
