package com.jacobsbytes.represent;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;



public class CongressionalView extends AppCompatActivity implements View.OnClickListener {

    private List<Congressionals> myCongressionals = new ArrayList<Congressionals>();
    Button back; EditText zipCodeTxt; Button secondSearch; String zipCodeStr;
    String name; String website; String chamber;
    String endTerm; String tweeterId;
    String email; String party; String bioguide_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional_view);

        name = new String(); website = new String(); tweeterId = new String();
        chamber = new String(); endTerm = new String();
        email = new String(); party = new String(); bioguide_id = new String();


        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
//
        zipCodeTxt = (EditText) findViewById(R.id.enterZip);
        zipCodeTxt.setOnClickListener(this);

        secondSearch = (Button) findViewById(R.id.secondSearch);
        secondSearch.setOnClickListener(this);
        zipCodeStr = new String();
//        populateCongressionals();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            zipCodeStr = extras.getString("zip_code");
            zipCodeTxt.setText(zipCodeStr);
        }

        String apikey = "3a742877313b429ea3b0a0d18da19c1d";
        String baseURL = "https://congress.api.sunlightfoundation.com";
        String zipAddition = "/legislators/locate?zip=" + zipCodeStr + "&apikey="+apikey;
        String url = baseURL + zipAddition;
        new ProcessJSON().execute(url);

        registerClickCallback();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.secondSearch:
                Intent sendIntentZipWatch = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntentZipWatch.putExtra("/view", "election");
                startService(sendIntentZipWatch);
                break;
        }
    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];
            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);
            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream) {
            if (stream != null) {
                try {
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader = new JSONObject(stream);
                    // Get the JSONObject "result"
                    JSONArray results = reader.getJSONArray("results");
                    for (int i = 0; i < results.length(); i ++) {
                        JSONObject obj1 = results.getJSONObject(i);
                        String firstName = obj1.getString("first_name");
                        String lastName = obj1.getString("last_name");
                        name = firstName + " " + lastName;
                        String title1 = obj1.getString("title");
                        if (title1.equals("Sen")) {
                            chamber = "Senator";
                        } else {
                            chamber = "Representative";
                        }
                        website = obj1.getString("website");
                        tweeterId = obj1.getString("twitter_id");
                        endTerm = obj1.getString("term_end");
                        email = obj1.getString("oc_email");
                        party = obj1.getString("party");
                        bioguide_id = obj1.getString("bioguide_id");
                        if (party.equals("D")) {
                            party = "Democrat";
                        } else {
                            party = "Republican";
                        }

                        Congressionals first = new Congressionals(R.drawable.barbara, chamber, name, party, email, website, "I love America!", endTerm, bioguide_id);
                        myCongressionals.add(first);





                    }
                    populateListView();
                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//
                    JSONObject obj;
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < myCongressionals.size(); i++) {
                        obj = new JSONObject();
                        try {
                            obj.put("name", myCongressionals.get(i).getName());
                            obj.put("title", myCongressionals.get(i).getTitle());
                            obj.put("party", myCongressionals.get(i).getParty());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonArray.put(obj);
                    }

//                    System.out.println(jsonArray.toString());
                    sendIntent.putExtra("/jsonArray", jsonArray.toString());
                    startService(sendIntent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void populateListView() {
        ArrayAdapter<Congressionals> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailedView = new Intent(getApplicationContext(), com.jacobsbytes.represent.detailedView.class);
                Congressionals c = myCongressionals.get(position);
                detailedView.putExtra("name", c.getName());
                detailedView.putExtra("title", c.getTitle());
                detailedView.putExtra("party", c.getParty());
                detailedView.putExtra("end_date", c.getEndTerm());
                detailedView.putExtra("bioguide_id", c.getBioguide_id());
                startActivity(detailedView);
            }
        });
    }


    private class MyListAdapter extends ArrayAdapter<Congressionals> {
        public MyListAdapter() {
            super(CongressionalView.this, R.layout.congressionalslistview, myCongressionals);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.congressionalslistview, parent, false);
            }

            //find the congressional to work with

            Congressionals currMembers = myCongressionals.get(position);


            //fill the pic
            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_iconPic);
            imageView.setImageResource(currMembers.getIconId());

            //Name
            TextView nameText = (TextView) itemView.findViewById(R.id.item_txtName);
            nameText.setText(currMembers.getName());

            // Senator or Rep
            TextView titleText = (TextView) itemView.findViewById(R.id.item_txtTitle);
            titleText.setText(currMembers.getTitle());

            //party affiliation
            TextView partyText = (TextView) itemView.findViewById(R.id.item_txtParty);
            partyText.setText(currMembers.getParty());

            //email
            TextView emailText = (TextView) itemView.findViewById(R.id.item_txtEmail);
            emailText.setText(currMembers.getEmail());

            //website
            TextView websiteurlText = (TextView) itemView.findViewById(R.id.item_txtWebsiteurl);
            websiteurlText.setText(currMembers.getWebsiteurl());

            //tweet
            TextView tweetText = (TextView) itemView.findViewById(R.id.item_txtTweet);
            tweetText.setText(currMembers.getTweet());

            return itemView;

        }





    }
}
