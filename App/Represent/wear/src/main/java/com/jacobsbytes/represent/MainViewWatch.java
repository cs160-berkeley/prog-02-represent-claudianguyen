package com.jacobsbytes.represent;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainViewWatch extends Activity implements WearableListView.ClickListener {

    private WearableListView listView;
    private ArrayList<Members> myMembers = new ArrayList<Members>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_represent);

//        Button moreInfo = (Button) findViewById(R.id.moreInfoButt);
//        moreInfo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                    startService(sendIntent);
//                }
//            });






        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                listView = (WearableListView) findViewById(R.id.listView1);
                listView.setAdapter(new MyAdapter(MainViewWatch.this));
                listView.setClickListener(MainViewWatch.this);
            }
        });

        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("jsonArray");

        if (jsonArray != null) {
            try {
                JSONArray array = new JSONArray(jsonArray);
//                System.out.println(array.toString(2));
                try {
                    for (int i = 0; i < array.length(); i ++) {
                        JSONObject obj1 = array.getJSONObject(i);
                        String name = obj1.getString("name");
                        String party = obj1.getString("party");
                        String title = obj1.getString("title");
                        myMembers.add(new Members(title, name, party));
                        System.out.println("HEEREREREREER");
                        System.out.println(myMembers);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {


    }

    // Adapter that fills in ListView
    @Override
    public void onTopEmptyRegionClick() {
    }

    private class MyAdapter extends WearableListView.Adapter {

        private final LayoutInflater inflater;
        private MyAdapter(Context c) {
            inflater = LayoutInflater.from(c);
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {



            Button electionButton = (Button) findViewById(R.id.electionButton);
            electionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent electionView = new Intent(getApplicationContext(), com.jacobsbytes.represent.electionView.class);
                    startActivity(electionView);
                }
            });

            return new WearableListView.ViewHolder(inflater.inflate(R.layout.member_view, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {


            TextView name = (TextView) viewHolder.itemView.findViewById(R.id.item_txtName);
            name.setText(myMembers.get(i).getName());

            TextView title = (TextView) viewHolder.itemView.findViewById(R.id.item_txtTitle);
            title.setText(myMembers.get(i).getTitle());

            TextView party = (TextView) viewHolder.itemView.findViewById(R.id.item_txtParty);
            party.setText(myMembers.get(i).getParty());


        }

        @Override
        public int getItemCount() {
            return myMembers.size();
        }
    }
}









