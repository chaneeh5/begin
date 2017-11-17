package com.cookbook.show_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ShowNotification extends Activity { 

    private NotificationManager mNManager; 
    private static final int NOTIFY_ID=1100; 

    /** Called when the activity is first created. */ 
    @Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main); 

        String ns = Context.NOTIFICATION_SERVICE;
        mNManager = (NotificationManager) getSystemService(ns); 
        final Notification msg = new Notification(R.drawable.icon,
                "New event of importance",
                System.currentTimeMillis()); 

        Button start = (Button)findViewById(R.id.start); 
        Button cancel = (Button)findViewById(R.id.cancel); 

        start.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) { 
                Context context = getApplicationContext(); 
                CharSequence contentTitle = "ShowNotification Example"; 
                CharSequence contentText = "Browse Android Cookbook Site"; 
                Intent msgIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.pearson.com")); 
                PendingIntent intent = 
                    PendingIntent.getActivity(ShowNotification.this, 
                            0, msgIntent, 
                            Intent.FLAG_ACTIVITY_NEW_TASK); 
                
                msg.defaults |= Notification.DEFAULT_SOUND;
                msg.flags |= Notification.FLAG_AUTO_CANCEL;
                
                msg.setLatestEventInfo(context, 
                        contentTitle, contentText, intent); 
                mNManager.notify(NOTIFY_ID, msg); 
            } 
        }); 

        cancel.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) { 
                mNManager.cancel(NOTIFY_ID); 
            } 
        }); 
    } 
}
