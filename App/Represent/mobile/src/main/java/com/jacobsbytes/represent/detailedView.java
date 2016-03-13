package com.jacobsbytes.represent;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detailedView extends AppCompatActivity implements View.OnClickListener {

    Button back;
    String name; String party; String title; String endTerm; String bioguide_id;
    TextView txtName;
    TextView txtParty;
    TextView txtTitle;
    TextView txtEndTerm;
    ArrayList<String> commNamesList; ArrayList<String> billNamesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        commNamesList = new ArrayList<>();
        billNamesList = new ArrayList<>();
        txtName = (TextView) findViewById(R.id.item_txtName);
        txtParty = (TextView) findViewById(R.id.item_txtParty);
        txtTitle = (TextView) findViewById(R.id.item_txtTitle);
        txtEndTerm = (TextView) findViewById(R.id.item_resDate);

        name = new String();
        title = new String();
        party = new String();
        endTerm = new String();
        bioguide_id = new String();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            name = extras.getString("name");
            party = extras.getString("party");
            title = extras.getString("title");
            endTerm = extras.getString("end_date");
            bioguide_id = extras.getString("bioguide_id");
            txtName.setText(name);
            txtParty.setText(party);
            txtTitle.setText(title);
            txtEndTerm.setText(endTerm);
        }

//        String apikey = "3a742877313b429ea3b0a0d18da19c1d";
//        String baseURL = "https://congress.api.sunlightfoundation.com";
//        String comAddition = "/committees?member_ids=" + bioguide_id + "&apikey="+apikey;
//        String urlComm = baseURL + comAddition;
//        new ProcessJSON().execute(urlComm);
//
//        String billAddition = "/bills?sponsor_ids=" + bioguide_id + "&apikey="+apikey;
//        String urlBill = baseURL + billAddition;
//        new ProcessJSON().execute(urlBill);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }

//    private class ProcessJSON extends AsyncTask<String, Void, String> {
//        protected String doInBackground(String... strings) {
//            String stream = null;
//            String urlString = strings[0];
//            HTTPDataHandler hh = new HTTPDataHandler();
//            stream = hh.GetHTTPData(urlString);
//            // Return the data from specified url
//            return stream;
//        }
//
//        protected void onPostExecute(String stream) {
//            if (stream != null) {
//                try {
//                    // Get the full HTTP Data as JSONObject
//                    JSONObject reader = new JSONObject(stream);
//                    // Get the JSONObject "result"
//                    JSONArray results = reader.getJSONArray("results");
//                    for (int i = 0; i < results.length(); i ++) {
//                        JSONObject obj1 = results.getJSONObject(i);
//                        String committeeName = obj1.getString("name");
//                        commNamesList.add(committeeName);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    JSONObject readerBills = new JSONObject(stream);
//                    JSONArray results2 = readerBills.getJSONArray("results");
//                    for (int i = 0; i < results2.length(); i ++) {
//                        JSONObject obj1 = results2.getJSONObject(i);
//                        String billName = obj1.getString("short_title");
//                        billNamesList.add(billName);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                }
//            }
//        }
//    }
}
