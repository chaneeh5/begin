package com.cookbook.layout_widgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;
import android.widget.TextView;

public class ToggleButtonExample extends Activity {
    private TextView tv;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle);
        tv = (TextView) findViewById(R.id.status);
        
        class Toppings {private boolean LETTUCE, TOMATO, CHEESE;}

        final Toppings sandwichToppings = new Toppings();
        final ToggleButton ToggleButton[] = {(ToggleButton) findViewById(R.id.ToggleButton0), 
                (ToggleButton) findViewById(R.id.ToggleButton1), 
                (ToggleButton) findViewById(R.id.ToggleButton2)};


        ToggleButton[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()) {
                    sandwichToppings.LETTUCE = true;
                } else {
                    sandwichToppings.LETTUCE = false;
                }
                tv.setText(""+sandwichToppings.LETTUCE + " " 
                        +sandwichToppings.TOMATO + " "
                        +sandwichToppings.CHEESE + " ");
            }
        });
        ToggleButton[1].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()) {
                    sandwichToppings.TOMATO = true;
                } else {
                    sandwichToppings.TOMATO = false;
                }
                tv.setText(""+sandwichToppings.LETTUCE + " " 
                        +sandwichToppings.TOMATO + " "
                        +sandwichToppings.CHEESE + " ");
            }
        });
        ToggleButton[2].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()) {
                    sandwichToppings.CHEESE = true;
                } else {
                    sandwichToppings.CHEESE = false;
                }
                tv.setText(""+sandwichToppings.LETTUCE + " " 
                        +sandwichToppings.TOMATO + " "
                        +sandwichToppings.CHEESE + " ");
            }
        });
    }
}