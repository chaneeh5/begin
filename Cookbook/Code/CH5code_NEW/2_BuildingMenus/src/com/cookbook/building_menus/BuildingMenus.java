package com.cookbook.building_menus;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class BuildingMenus extends Activity {
    private final int MENU_ADD=1, MENU_SEND=2, MENU_DEL=3;
    private final int GROUP_DEFAULT=0, GROUP_DEL=1;
    private final int ID_DEFAULT=999;
    private final int ID_TEXT1=1, ID_TEXT2=2, ID_TEXT3=3;
    private String[] choices = {"Press Me", "Try Again", "Change Me"};
    
    private static int itemNum=0;
    private static TextView bv;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bv = (TextView) findViewById(R.id.focus_text);

        registerForContextMenu((View) findViewById(R.id.focus_text));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(GROUP_DEFAULT, MENU_ADD, 0, "Add")
        .setIcon(R.drawable.icon); //example of adding icon
        menu.add(GROUP_DEFAULT, MENU_SEND, 0, "Send");
        menu.add(GROUP_DEL, MENU_DEL, 0, "Delete");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(itemNum>0) { 
            menu.setGroupVisible(GROUP_DEL, true);
        } else {
            menu.setGroupVisible(GROUP_DEL, false);            
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case MENU_ADD:
            create_note();
            return true;
        case MENU_SEND:
            send_note();
            return true;
        case MENU_DEL:
            delete_note();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.focus_text) {
            SubMenu textMenu = menu.addSubMenu("Change Text");
            textMenu.add(0, ID_TEXT1, 0, choices[0]);
            textMenu.add(0, ID_TEXT2, 0, choices[1]);
            textMenu.add(0, ID_TEXT3, 0, choices[2]);
            menu.add(0, ID_DEFAULT, 0,  "Original Text");
        }   
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case ID_DEFAULT:
            bv.setText(R.string.hello);
            return true;
        case ID_TEXT1:
        case ID_TEXT2:
        case ID_TEXT3:
            bv.setText(choices[item.getItemId()-1]);
            return true;
        }
        return super.onContextItemSelected(item);
    }
    
    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);       
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);        
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.orig:
            bv.setText(R.string.hello);
            return true;
        case R.id.text1:
            bv.setText(choices[0]);
            return true;
        case R.id.text2:
            bv.setText(choices[1]);
            return true;
        case R.id.text3:
            bv.setText(choices[2]);
            return true;
        }
        return super.onContextItemSelected(item);
    }*/

    void create_note() { // example code to create note
        itemNum++;
    }    
    void send_note() { // example code to send note
        Toast.makeText(this, "Item: "+itemNum, 
                Toast.LENGTH_SHORT).show();
    }  
    void delete_note() { // example code to delete note
        itemNum--;
    }
}