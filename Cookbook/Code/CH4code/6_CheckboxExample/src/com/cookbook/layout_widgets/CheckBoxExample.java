package com.cookbook.layout_widgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxExample extends Activity {
    private TextView tv;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ckbox);
        tv = (TextView) findViewById(R.id.status);
        
        class Toppings {private boolean LETTUCE, TOMATO, CHEESE;}

        final Toppings sandwichToppings = new Toppings();
        final CheckBox CheckBox[] = {(CheckBox) findViewById(R.id.checkbox0), 
                (CheckBox) findViewById(R.id.checkbox1), 
                (CheckBox) findViewById(R.id.checkbox2)};


        CheckBox[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    sandwichToppings.LETTUCE = true;
                } else {
                    sandwichToppings.LETTUCE = false;
                }
                tv.setText(""+sandwichToppings.LETTUCE + " " 
                        +sandwichToppings.TOMATO + " "
                        +sandwichToppings.CHEESE + " ");
            }
        });
        CheckBox[1].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    sandwichToppings.TOMATO = true;
                } else {
                    sandwichToppings.TOMATO = false;
                }
                tv.setText(""+sandwichToppings.LETTUCE + " " 
                        +sandwichToppings.TOMATO + " "
                        +sandwichToppings.CHEESE + " ");
            }
        });
        CheckBox[2].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
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