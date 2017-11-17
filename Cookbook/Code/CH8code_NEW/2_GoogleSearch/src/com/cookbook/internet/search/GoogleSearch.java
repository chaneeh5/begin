package com.cookbook.internet.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GoogleSearch extends Activity {
    /** Called when the activity is first created. */
	TextView tv1;
	EditText ed1;
	Button bt1;
	static String url= "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv1 = (TextView) this.findViewById(R.id.display);
        ed1 = (EditText) this.findViewById(R.id.editText);
        bt1 = (Button) this.findViewById(R.id.submit);
        
        bt1.setOnClickListener(new OnClickListener(){
    		public void onClick(View view){
    				if(ed1.getText().toString()!=null){
    					try{
    						new mygoogleSearch().execute(ed1.getText().toString());
    						//processResponse(searchRequest(ed1.getText().toString()));
    					}catch(Exception e){
    						Log.v("Exception google search","Exception:"+e.getMessage());
    					}
    				}
    				ed1.setText("");
    			
    		}
    	});        
    }
   
	public String SearchRequest(String searchString) throws MalformedURLException, IOException  {
		String newFeed=url+"\""+searchString+"\"";
		StringBuilder response = new StringBuilder();
		Log.v("gsearch","gsearch url:"+newFeed);
		URL url = new URL(newFeed);
		
		HttpURLConnection httpconn =(HttpURLConnection) url.openConnection();
		
		if(httpconn.getResponseCode()==HttpURLConnection.HTTP_OK){
			BufferedReader input = new BufferedReader(
					new InputStreamReader(httpconn.getInputStream()), 
					8192);
			String strLine = null;
			while ((strLine = input.readLine()) != null) {
				response.append(strLine);
			}
			input.close();
		}
	    return response.toString();
	}
	public void ProcessResponse(String resp)throws IllegalStateException, 
	IOException, JSONException, NoSuchAlgorithmException{
		StringBuilder sb = new StringBuilder();
		Log.v("gsearch","gsearch result:"+resp);
		JSONObject mResponseObject = new JSONObject(resp);
		JSONObject responObject = mResponseObject.getJSONObject("responseData");
		JSONArray array =responObject.getJSONArray("results");
		Log.v("gsearch","number of resultst:"+array.length());
		for(int i=0;i<array.length();i++){
			Log.v("result",i+"] "+array.get(i).toString()); 	
			String title = array.getJSONObject(i).getString("title");
			String urllink = array.getJSONObject(i).getString("visibleUrl");
			sb.append(title);
			sb.append("\n");
			sb.append(urllink);
			sb.append("\n");
		}
		tv1.setText(sb.toString());
	}
	private class mygoogleSearch extends AsyncTask<String, Integer, String> {
		
		protected String doInBackground(String... searchKey) {
			
			String key = searchKey[0];

			try{
				return SearchRequest(key);
			}catch(Exception e){
				Log.v("Exception google search","Exception:"+e.getMessage());
				return "";
						
			}
		}		
		protected void onPostExecute(String result) {
			try{
				ProcessResponse(result);
			}catch(Exception e){
				Log.v("Exception google search","Exception:"+e.getMessage());
						
			}		

	
		}
	}
}