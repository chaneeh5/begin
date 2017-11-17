package jwei.apps.dataforandroid.ch6;

import java.util.ArrayList;
import java.util.List;

import jwei.apps.dataforandroid.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.ListView;

public class CustomBaseAdapterActivity extends ListActivity {

    private List<ContactEntry> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        // MAKE QUERY TO CONTACT CONTENTPROVIDER
        String[] projections = new String[] { Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER, Phone.TYPE };
        Cursor c = getContentResolver().query(Phone.CONTENT_URI, projections, null, null, null);
        startManagingCursor(c);

        contacts = new ArrayList<ContactEntry>();
        while (c.moveToNext()) {
            int nameCol = c.getColumnIndex(Phone.DISPLAY_NAME);
            int numCol = c.getColumnIndex(Phone.NUMBER);
            int typeCol = c.getColumnIndex(Phone.TYPE);

            String name = c.getString(nameCol);
            String number = c.getString(numCol);
            int type = c.getInt(typeCol);
            contacts.add(new ContactEntry(name, number, type));
        }

        // CREATE ADAPTER USING LIST OF CONTACT OBJECTS
        ContactBaseAdapter cAdapter = new ContactBaseAdapter(this, contacts);

        // SET THIS ADAPTER AS YOUR LIST ACTIVITY'S ADAPTER
        this.setListAdapter(cAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ContactEntry c = contacts.get(position);

        String name = c.getName();
        String number = c.getNumber();
        String type = c.getType();

        System.out.println("CLICKED ON " + name + " " + number + " " + type);
    }
}
