package com.juicy.app.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.juicy.app.R;
import com.juicy.app.objects.VideoGame;
import com.juicy.app.objects.VideoGame.VideoGameConsole;

public class VideoGameBaseAdpater extends BaseAdapter {

    // REMEMBER CONTEXT SO THAT IT CAN BE USED TO INFLATE VIEWS
    private LayoutInflater mInflater;

    // LIST OF VIDEO GAMES
    private List<VideoGame> mItems = new ArrayList<VideoGame>();

    public VideoGameBaseAdpater(Context context, List<VideoGame> items) {
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
        VideoGameViewHolder holder;

        // IF VIEW IS NULL THEN WE NEED TO INSTANTIATE IT BY INFLATING IT
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_entry, null);

            holder = new VideoGameViewHolder();
            holder.name_entry = (TextView) convertView.findViewById(R.id.name_entry);
            holder.type_entry = (TextView) convertView.findViewById(R.id.number_type_entry);

            convertView.setTag(holder);
        } else {
            // GET VIEW HOLDER BACK FOR FAST ACCESS TO FIELDS
            holder = (VideoGameViewHolder) convertView.getTag();
        }

        // EFFICIENTLY BIND DATA WITH HOLDER
        VideoGame v = mItems.get(position);
        holder.name_entry.setText(v.getName());
        String type = VideoGameConsole.convertIntToString(v.getConsoleType());
        holder.type_entry.setText(type);

        return convertView;
    }

    static class VideoGameViewHolder {
        TextView name_entry;

        TextView type_entry;
    }

}
