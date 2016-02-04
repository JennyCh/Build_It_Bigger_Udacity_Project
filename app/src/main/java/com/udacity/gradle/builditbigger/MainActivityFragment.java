package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.Jokes;
import com.example.jenny.myapplication.LibraryActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private String JOKE = "joke";
    private String joke;
    private final Jokes jokes;
    public MainActivityFragment() {
        this.jokes = new Jokes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellJokeButton = (Button) root.findViewById(R.id.tellJokeButton);

        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"default text" , Toast.LENGTH_SHORT).show();

                joke =  jokes.getRandomJoke();
                Intent myIntent = new Intent(getActivity(), LibraryActivity.class);
                Bundle extras = new Bundle();
                extras.putString(JOKE, joke);
                //myIntent.putExtra(joke,JOKE);
                Log.v("MainActivityFragment", joke);
                myIntent.putExtras(extras);
                startActivity(myIntent);
            }
        });



        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }



}
