package jwei.apps.dataforandroid.ch6;

import java.util.ArrayList;
import java.util.List;

import jwei.apps.dataforandroid.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactBaseAdapter extends BaseAdapter {

    // REMEMBER CONTEXT SO THAT IT CAN BE USED TO INFLATE VIEWS
    private LayoutInflater mInflater;

    // LIST OF CONTACTS
    private List<ContactEntry> mItems = new ArrayList<ContactEntry>();

    // CONSTRUCTOR OF THE CUSTOM BASE ADAPTER
    public ContactBaseAdapter(Context context, List<ContactEntry> items) {
        // HERE WE CACHE THE INFLATOR FOR EFFICIENCY
        mInflater = LayoutInflater.from(context);
        mItems = items;
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ContactViewHolder holder;

        // IF VIEW IS NULL THEN WE NEED TO INSTANTIATE IT BY INFLATING IT
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_entry, null);

            holder = new ContactViewHolder();
            holder.name_entry = (TextView) convertView.findViewById(R.id.name_entry);
            holder.number_entry = (TextView) convertView.findViewById(R.id.number_entry);
            holder.type_entry = (TextView) convertView.findViewById(R.id.number_type_entry);

            convertView.setTag(holder);
        } else {
            // GET VIEW HOLDER BACK FOR FAST ACCESS TO FIELDS
            holder = (ContactViewHolder) convertView.getTag();
        }

        // EFFICIENTLY BIND DATA WITH HOLDER
        ContactEntry c = mItems.get(position);
        holder.name_entry.setText(c.getName());
        holder.number_entry.setText(c.getNumber());
        holder.type_entry.setText(c.getType());

        return convertView;
    }

    static class ContactViewHolder {
        TextView name_entry;

        TextView number_entry;

        TextView type_entry;
    }
}
