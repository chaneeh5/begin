package com.cookbook.datastorage;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookbook.data.Constants;
import com.cookbook.data.MyDB;

public class DisplayDiaries extends ListActivity{
	
	MyDB dba;
	DiaryAdapter myAdapter;
	private class MyDiary{
		public MyDiary(String t, String c, String r){
			title=t;
			content=c;
			recorddate=r;
		}
		public String title;
		public String content;
		public String recorddate;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		dba = new MyDB(this);
		dba.open();
		setContentView(R.layout.diaries);
	
		super.onCreate(savedInstanceState);
		myAdapter = new DiaryAdapter(this);
		this.setListAdapter(myAdapter);
	}

	private class DiaryAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		private ArrayList<MyDiary> diaries;
		public DiaryAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			diaries = new ArrayList<MyDiary>();
			getdata();
		}
		public void getdata(){
			Cursor c = dba.getdiaries();
            startManagingCursor(c);
            if(c.moveToFirst()){
            	do{
            		String title = c.getString(c.getColumnIndex(Constants.TITLE_NAME));
            		String content = c.getString(c.getColumnIndex(Constants.CONTENT_NAME));
            		DateFormat dateFormat = DateFormat.getDateTimeInstance();
            		String datedata = dateFormat.format(new Date(c.getLong(c.getColumnIndex(Constants.DATE_NAME))).getTime());
            		MyDiary temp = new MyDiary(title,content,datedata);
            		diaries.add(temp);
            	}while(c.moveToNext());
            	
            }
			
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return diaries.size();
		}

		@Override
		public MyDiary getItem(int i) {
			// TODO Auto-generated method stub
			return diaries.get(i);
		}

		@Override
		public long getItemId(int i) {
			// TODO Auto-generated method stub
			return i;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			final ViewHolder holder;
			View v = arg1;
			if ((v == null) || (v.getTag() == null)) {
					v = mInflater.inflate(R.layout.diaryrow, null);
					holder = new ViewHolder();
					holder.mTitle = (TextView)v.findViewById(R.id.name);
					holder.mDate = (TextView)v.findViewById(R.id.datetext);
					v.setTag(holder);
			} else {
				holder = (ViewHolder) v.getTag();
			}  

			holder.mdiary = getItem(arg0);
			holder.mTitle.setText(holder.mdiary.title);
			holder.mDate.setText(holder.mdiary.recorddate);

			v.setTag(holder);

			// TODO Auto-generated method stub
			return v;
		}
		public class ViewHolder {
			MyDiary mdiary;
			TextView mTitle;
			TextView mDate;
		}
		
	}
	
}
