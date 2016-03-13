package com.jacobsbytes.represent;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by Claudia Nguyen
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
//    private static final String SCROLL = "/scroll";
    private static final String ELECTION = "/election";
    private static final String JSONSTR = "/jsonArray";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());


        if (messageEvent.getPath().equalsIgnoreCase(ELECTION)) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, electionView.class);
            intent.addFlags(Intent .FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            Log.d("T", "about to start watch MainActivity with MEM_NAME: nancy");
            startActivity(intent);


        }
        else {

            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//            System.out.println(value);
            Intent intent = new Intent(this, MainViewWatch.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("jsonArray", value);
            Log.d("starting", "about to start watchview");
            startActivity(intent);
        }

//        else {
//            System.out.println(messageEvent.getPath());
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//            Intent intent = new Intent(this, MainViewWatch.class);
//            System.out.println("IN scrolls");
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            //you need to add this flag since you're starting a new activity from a service
//            intent.putExtra("MEMBER_NAME", "nancy");
//            Log.d("T", "about to start watch MainActivity with MEM_NAME: nancy");
//            startActivity(intent);
//        }

    }
}
