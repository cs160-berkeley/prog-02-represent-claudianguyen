package com.jacobsbytes.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class detailedView extends AppCompatActivity implements View.OnClickListener {

    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent i = getIntent();
//        var = i.getStringExtra("hi");




        setContentView(R.layout.activity_detailed_view);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }
}
