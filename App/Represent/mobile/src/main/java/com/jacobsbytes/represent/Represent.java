package com.jacobsbytes.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.HashMap;

public class Represent extends AppCompatActivity implements View.OnClickListener {

    EditText zipcode;
    Button search;
    Button currentLocation;
//    ArrayList<Integer> zipcodeList;
    String zipCodeStr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//        getActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_represent);

        zipcode = (EditText) findViewById(R.id.zipcode);
        zipcode.setOnClickListener(this);
        currentLocation = (Button) findViewById(R.id.currentLocation);
        currentLocation.setOnClickListener(this);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
        zipCodeStr = new String();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zipcode:
                zipCodeStr = zipcode.getText().toString();
//                int zipcodeInt = Integer.parseInt(zipcodestr);

                break;

            case R.id.search:
                Intent congressionalView = new Intent(getApplicationContext(), com.jacobsbytes.represent.CongressionalView.class);
                startActivity(congressionalView);
                Intent sendIntentZipWatch = new Intent(getBaseContext(), PhoneToWatchService.class);
                startService(sendIntentZipWatch);

                break;

            case R.id.currentLocation:
                Intent currLocation = new Intent(getApplicationContext(), com.jacobsbytes.represent.CurrLocation.class);
                startActivity(currLocation);
                Intent sendIntentCurrWatch = new Intent(getBaseContext(), PhoneToWatchService.class);
                startService(sendIntentCurrWatch);
                break;
        }
    }
}
