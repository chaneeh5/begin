package com.cookbook.change_font;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChangeFont extends Activity {
    TextView tv;
    /* private int color_vals[]={R.color.red, R.color.green, R.color.blue};
    int idx=0;
     *//** Called when the activity is first created. *//*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.text);

        Button changeFont = (Button) findViewById(R.id.change);
        changeFont.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tv.setTextColor(getResources()
                        .getColor(color_vals[idx]));
                idx = (idx+1)%3;
            }
        });
    }*/

    private int size_vals[]={R.dimen.small, R.dimen.medium, R.dimen.large};
    private int text_vals[]={R.string.first_text, R.string.second_text, R.string.third_text};
    int idx=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.text);

        Button changeFont = (Button) findViewById(R.id.change);
        changeFont.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tv.setTextSize(getResources()
                        .getDimension(size_vals[idx]));
                //tv.setText(getBaseContext().getString(text_vals[idx]));
                idx = (idx+1)%3;
            }
        });
    }
}