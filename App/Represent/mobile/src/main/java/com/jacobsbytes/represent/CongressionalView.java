package com.jacobsbytes.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;



public class CongressionalView extends AppCompatActivity implements View.OnClickListener {

    private List<Congressionals> myCongressionals = new ArrayList<Congressionals>();
    Button back;
//    EditText zipCodeTxt;
//    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional_view);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
//
//        zipCodeTxt = (EditText) findViewById(R.id.item_enterZip);
//        zipCodeTxt.setOnClickListener(this);

//        search = (Button) findViewById(R.id.searchZip);
//        search.setOnClickListener(this);

        //on click method here!!!
        populateCongressionals();
        populateListView();
        registerClickCallback();

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();

//        if (extras != null) {
//            String zipCodeStr = extras.getString("zip_code");
//            zipCodeTxt.setText(zipCodeStr);
//        }



    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
//            case R.id.search:
//                Intent sendIntentZipWatch = new Intent(getBaseContext(), PhoneToWatchService.class);
//
//                startService(sendIntentZipWatch);


        }
    }

    private void populateCongressionals() {
        Congressionals first = new Congressionals(R.drawable.barbara, "Senator", "Barbara Boxer", "Democrat", "boxer@gmail.com", "barbaraboxer.com", "T love America!");
        Congressionals sec = new Congressionals(R.drawable.paul, "Representative", "Paul Cook", "Democrat", "cook@gmail.com", "paulcook.com", "T love America too!");
        Congressionals third = new Congressionals(R.drawable.diane, "Senator", "Diane Feinstein", "Democrat", "feinstein@gmail.com", "dianefeinst.com", "T love America!");
        Congressionals fourth = new Congressionals(R.drawable.nancy, "Representative", "Nancy Pelosi", "Democrat", "polsi@gmail.com", "nancypolsi.com", "T love America too!");
        myCongressionals.add(first);
        myCongressionals.add(sec);
        myCongressionals.add(third);
        myCongressionals.add(fourth);
    }

    private void populateListView() {
        ArrayAdapter<Congressionals> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
//        String[] party = {"dem", "Reb"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.congressionalslistview, R.id.textView1, party);
//        ListView list = (ListView) findViewById(R.id.listView);
//        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Congressionals clickedMember = myCongressionals.get(position);
                Intent detailedView = new Intent(getApplicationContext(), com.jacobsbytes.represent.detailedView.class);
//                detailedView.putExtra("hi", name of what you want to put in );
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
