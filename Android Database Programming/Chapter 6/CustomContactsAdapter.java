package jwei.apps.dataforandroid.ch6;

import jwei.apps.dataforandroid.R;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CustomContactsAdapter extends SimpleCursorAdapter {

    private int layout;

    public CustomContactsAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layout, parent, false);
        return v;
    }

    @Override
    public void bindView(View v, Context context, Cursor c) {
        int nameCol = c.getColumnIndex(Phone.DISPLAY_NAME);
        int numCol = c.getColumnIndex(Phone.NUMBER);
        int typeCol = c.getColumnIndex(Phone.TYPE);

        String name = c.getString(nameCol);
        String number = c.getString(numCol);
        int type = c.getInt(typeCol);

        String numType = "";
        switch (type) {
            case Phone.TYPE_HOME:
                numType = "HOME";
                break;
            case Phone.TYPE_MOBILE:
                numType = "MOBILE";
                break;
            case Phone.TYPE_WORK:
                numType = "WORK";
                break;
            default:
                numType = "MOBILE";
                break;
        }

        // FIND THE VIEW AND SET THE NAME
        TextView name_text = (TextView) v.findViewById(R.id.name_entry);
        name_text.setText(name);

        TextView number_text = (TextView) v.findViewById(R.id.number_entry);
        number_text.setText(number);

        TextView type_text = (TextView) v.findViewById(R.id.number_type_entry);
        type_text.setText(numType);
    }
}
