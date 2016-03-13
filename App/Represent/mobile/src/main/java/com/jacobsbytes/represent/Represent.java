package com.jacobsbytes.represent;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Represent extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    EditText zipcode;
    Button search;
    Button currentLocation;
//    ArrayList<Integer> zipcodeList;
    String zipCodeStr;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String mLatitudeText;
    private String mLongitudeText;
    private List<Address> addresses;
    private String postalCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        setContentView(R.layout.activity_represent);


        zipcode = (EditText) findViewById(R.id.zipcode);
        zipcode.setOnClickListener(this);
        currentLocation = (Button) findViewById(R.id.currentLocation);
        currentLocation.setOnClickListener(this);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
        zipCodeStr = new String();
        mLatitudeText = new String();
        mLongitudeText = new String();
        addresses = new ArrayList<>();
        postalCode = new String();


}



    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
//    public void onConnected(Bundle bundle) {
//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                mGoogleApiClient);
//        Log.d("null", "MY LOCATION IS NULL");
//        if (mLastLocation != null) {
//            Log.d("not", "MY LOCATION IS not NULL");
//            mLatitudeText = String.valueOf(mLastLocation.getLatitude());
//            mLongitudeText = String.valueOf(mLastLocation.getLongitude());
//        }
//    }

    public void onConnected(Bundle connectionHint) {
        Log.d("current location", "1");
        try {
            Log.d("current location", "2");
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } catch (SecurityException e) {
            Toast.makeText(this, "The app does not have permission to access current location.", Toast.LENGTH_SHORT).show();
        }

        if (mLastLocation != null) {
            Log.d("current location", "3");
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                postalCode = addresses.get(0).getPostalCode();
            } catch (IOException e) {
                Toast.makeText(this, "You did not enter a valid zipcode", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zipcode:
                zipCodeStr = zipcode.getText().toString();
//                int zipcodeInt = Integer.parseInt(zipcodestr);

                break;

            case R.id.search:
                Intent congressionalView = new Intent(getApplicationContext(), com.jacobsbytes.represent.CongressionalView.class);

                congressionalView.putExtra("zip_code", zipCodeStr);
                startActivity(congressionalView);
//                Intent sendIntentZipWatch = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntentZipWatch.putExtra("/view", "scroll");
//                startService(sendIntentZipWatch);

                break;

            case R.id.currentLocation:
                Intent congressionalView2 = new Intent(getApplicationContext(), com.jacobsbytes.represent.CongressionalView.class);
                congressionalView2.putExtra("zip_code", postalCode);
                startActivity(congressionalView2);
//                Intent sendIntentZipWatch2 = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntentZipWatch2.putExtra("/view", "scroll");
//                startService(sendIntentZipWatch2);


//                Log.d("lat", mLatitudeText);
//                Log.d("long", mLongitudeText);
//                Log.d("postalcode", postalCode);
//                Intent currLocation = new Intent(getApplicationContext(), com.jacobsbytes.represent.CurrLocation.class);
//                currLocation.putExtra("zip_code", postalCode);
//                startActivity(currLocation);
//                Intent sendIntentCurrWatch = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntentCurrWatch.putExtra("/view", "scroll");
//                startService(sendIntentCurrWatch);
                break;
        }
    }
}
