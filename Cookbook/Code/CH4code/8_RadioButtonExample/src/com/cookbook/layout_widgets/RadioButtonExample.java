package com.cookbook.layout_widgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.Toast;

public class RadioButtonExample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rbutton);
        
        final RadioButton rbutton[] = {(RadioButton) findViewById(R.id.rbutton0), 
                (RadioButton) findViewById(R.id.rbutton1), 
                (RadioButton) findViewById(R.id.rbutton2)};
        
        rbutton[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (((RadioButton) v).isChecked()) {
                    Toast.makeText(RadioButtonExample.this, "Yes", Toast.LENGTH_LONG).show();              
                    
                }
                
            }
        });



    }
}
