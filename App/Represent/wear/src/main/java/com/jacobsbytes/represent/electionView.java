package com.jacobsbytes.represent;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.common.api.Api;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class electionView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_view);

//        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+ZipCodeTxt;
//        Api apiURL = new URL(url);
//        HttpsURLConnection urlConnection = (HttpsURLConnection) apiUrl.openConnection
    }
}
