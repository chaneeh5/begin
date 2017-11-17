package com.cookbook.programmatic_layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProgrammaticLayout extends Activity {
    private int TEXTVIEW1_ID = 100011;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Show alternative to XML layout declaration:
        ////setContentView(R.layout.main);        
        //instead declare layout programmatically
        final RelativeLayout relLayout = new RelativeLayout( this );
        relLayout.setLayoutParams( new ViewGroup.LayoutParams( 
                                           LayoutParams.FILL_PARENT, 
                                           LayoutParams.FILL_PARENT ) );

        TextView textView1 = new TextView( this );
        textView1.setText("middle");
        textView1.setTag(TEXTVIEW1_ID);

        RelativeLayout.LayoutParams text1layout = new RelativeLayout.LayoutParams(
                                           LayoutParams.WRAP_CONTENT, 
                                           LayoutParams.WRAP_CONTENT );
        text1layout.addRule( RelativeLayout.CENTER_IN_PARENT );        
        relLayout.addView(textView1, text1layout);

        TextView textView2 = new TextView( this );
        textView2.setText("high");

        RelativeLayout.LayoutParams text2Layout = new RelativeLayout.LayoutParams( 
                                           LayoutParams.WRAP_CONTENT, 
                                           LayoutParams.WRAP_CONTENT );
        text2Layout.addRule(RelativeLayout.ABOVE, TEXTVIEW1_ID );
        relLayout.addView( textView2, text2Layout );

        setContentView( relLayout );
    }
}