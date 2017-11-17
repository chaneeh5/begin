package com.cookbook.twitter;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateAndList extends ListActivity {
    /** Called when the activity is first created. */
	  EditText userET;
	  Button updateBT;
	  Twitter twitter;
	  ResponseList<Status> userTimeline;
	  UserTimeLineAdapter myAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        userET = (EditText)findViewById(R.id.userStatus);
        
        updateBT = (Button)findViewById(R.id.updateButton);

        twitter = TwitterCookBook.twitter;
        setup stup = new setup();
        stup.execute();

        updateBT.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
                try {
                	twitter.updateStatus(userET.getText().toString());
                	loadstatus ldstatus = new loadstatus();
            		ldstatus.execute();
                	userET.setText("");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
   
	private class UserTimeLineAdapter extends BaseAdapter{
		private LayoutInflater mInflater;

		public UserTimeLineAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return userTimeline.size();
		}

		@Override
		public Status getItem(int i) {
			// TODO Auto-generated method stub
			return userTimeline.get(i);
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
					v = mInflater.inflate(R.layout.usertimelinerow, null);
					holder = new ViewHolder();
					holder.mName = (TextView)v.findViewById(R.id.name);
					holder.mStatus = (TextView)v.findViewById(R.id.msg);
					v.setTag(holder);
			} else {
				holder = (ViewHolder) v.getTag();
			}  

			holder.status= getItem(arg0);
			holder.mName.setText(holder.status.getUser().getName());
			holder.mStatus.setText(holder.status.getText());

			v.setTag(holder);

			// TODO Auto-generated method stub
			return v;
		}
		public class ViewHolder {
			Status status;
			TextView mName;
			TextView mStatus;
		}
		
	}
	private class setup extends AsyncTask<String, Integer, String> {
		
		protected String doInBackground(String... searchKey) {
			

			try{
            	userTimeline = twitter.getHomeTimeline();
				return "";
			}catch(Exception e){
				Log.v("Exception google search","Exception:"+e.getMessage());
				return "";
						
			}
		}		
		protected void onPostExecute(String result) {
			try{				
				myAdapter = new UserTimeLineAdapter(UpdateAndList.this);
				UpdateAndList.this.setListAdapter(myAdapter);
			}catch(Exception e){
				Log.v("Exception google search","Exception:"+e.getMessage());
						
			}		

	
		}
	}
	private class loadstatus extends AsyncTask<String, Integer, String> {
		
		protected String doInBackground(String... searchKey) {
			

			try{
            	userTimeline = twitter.getHomeTimeline();
				return "";
			}catch(Exception e){
				Log.v("Exception google search","Exception:"+e.getMessage());
				return "";
						
			}
		}		
		protected void onPostExecute(String result) {
			try{				
            	myAdapter.notifyDataSetChanged();
				;
			}catch(Exception e){
				Log.v("Exception google search","Exception:"+e.getMessage());
						
			}		

	
		}
	}
}