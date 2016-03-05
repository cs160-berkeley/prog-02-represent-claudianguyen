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

import java.util.ArrayList;
import java.util.List;

public class MainViewWatch extends Activity implements WearableListView.ClickListener {

    private WearableListView listView;
    private ArrayList<Members> myMembers = new ArrayList<Members>();
    private ArrayList<Members> myMembers2 = new ArrayList<Members>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_represent);



        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                listView = (WearableListView) findViewById(R.id.listView1);
                listView.setAdapter(new MyAdapter(MainViewWatch.this));
                listView.setClickListener(MainViewWatch.this);
            }
        });



        populateMembers();
        populateMembers2();


    }


    private void populateMembers() {
        Members first = new Members(R.drawable.barbara, "Senator", "Barbara Boxer", "Democrat");
        Members sec = new Members(R.drawable.nancy, "Representative", "Nancy Pelosi", "Democrat");
        Members third = new Members(R.drawable.diane, "Senator", "Diane Feinstein", "Democrat");
        Members fourth = new Members(R.drawable.paul, "Representative", "Paul Cook", "Democrat");
        myMembers.add(first);
        myMembers.add(sec);
        myMembers.add(third);
        myMembers.add(fourth);
    }

    private void populateMembers2() {
        Members first2 = new Members(R.drawable.barbara, "Senator", "Kansen Chu", "Democrat");
        Members sec2 = new Members(R.drawable.paul, "Representative", "Bob Wieckowski", "Democrat");
        Members third2 = new Members(R.drawable.diane, "Senator", "Diane Feinstein", "Democrat");
        myMembers2.add(first2);
        myMembers2.add(sec2);
        myMembers2.add(third2);
    }

    //When i click on a list item, i should be able to open an intent.

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

            Log.d("testing", "did create item photo");

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();



            return new WearableListView.ViewHolder(inflater.inflate(R.layout.member_view, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {

            //get photo out
            Log.d("testing", "blah2");

            ImageButton imageButton = (ImageButton) viewHolder.itemView.findViewById(R.id.item_photo);
            imageButton.setImageResource(myMembers.get(i).getIconId());

            TextView name = (TextView) viewHolder.itemView.findViewById(R.id.item_txtName);
            name.setText(myMembers.get(i).getName());

            TextView title = (TextView) viewHolder.itemView.findViewById(R.id.item_txtTitle);
            title.setText(myMembers.get(i).getTitle());

            TextView party = (TextView) viewHolder.itemView.findViewById(R.id.item_txtParty);
            party.setText(myMembers.get(i).getParty());


            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                    startService(sendIntent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return myMembers.size();
        }
    }
}















//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//
//        if (extras != null) {
//            String mem_name = extras.getString("MEM_NAME");
//            mFeedBtn.setText("Feed " + catName);
//        }

//        mFeedBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                startService(sendIntent);
//            }
//        });
//    }
//}
