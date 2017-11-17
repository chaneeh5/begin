package com.cookbook.implicit_intents;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListActivityExample extends ListActivity {
    static final String[] ACTIVITY_CHOICES = new String[] {
        "Open Website Example",
        "Open Contacts",
        "Open Phone Dialer Example",
        "Search Google Example",        
        "Start Voice Command"        
    };
    final String searchTerms = "superman"; 

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ACTIVITY_CHOICES));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setTextFilterEnabled(true);
        getListView().setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                switch(arg2) {
                case 0: //opens web browser and navigates to given website
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.android.com/"))); 
                    break;
                case 1: //opens contacts application to browse all contacts
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("content://contacts/people/"))); 
                    break;
                case 2: //opens phone dialer and fills in the given number
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("tel:12125551212")));
                    break;
                case 3: //no http or https means it will search Google for the string
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH ); 
                    intent.putExtra(SearchManager.QUERY, searchTerms); 
                    startActivity(intent);
                    break;              
                case 4: //starts the voice command
                    startActivity(new Intent(Intent.ACTION_VOICE_COMMAND));
                    break;          
                default: break;
                }
            }
        });
    }
}



