package com.packt.asyncandroid.chapter2.example2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.packt.asyncandroid.R;

public class Example2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch2_example_layout);
        ((TextView)findViewById(R.id.title)).setText(R.string.ch2_ex2);
        ((TextView)findViewById(R.id.description)).setText(R.string.ch2_ex2_desc);

        Button goButton = (Button) findViewById(R.id.go);
        final TextView resultView = (TextView) findViewById(R.id.result);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrimesTask task =
                    new PrimesTask(Example2Activity.this, resultView);
                task.execute(500);
            }
        });
    }

}
