package com.udacity.gradle.builditbigger.free;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Jokes;
import com.example.jenny.myapplication.LibraryActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private InterstitialAd mInterstitialAd;
    private String JOKE = "joke";
    private final Jokes jokes;
    private ProgressBar spinner;
    private View root;
    public MainActivityFragment() {
        this.jokes = new Jokes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellJokeButton = (Button) root.findViewById(R.id.tellJokeButton);
        this.spinner = (ProgressBar) root.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        createAdd();
        requestNewInterstitial();
        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showAdd();

                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                }else{
                    loadJoke();
                }
                spinner.setVisibility(View.VISIBLE);
            }
        });
        return root;
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    private void createAdd(){
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.full_page_add_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                loadJoke();
            }
        });
    }

    private void loadJoke(){
        new EndPointsAsyncTask() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s != null) {
                    Intent myIntent = new Intent(getActivity(), LibraryActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString(JOKE, s);
                    myIntent.putExtras(extras);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(getActivity(), R.string.joke_not_found, Toast.LENGTH_SHORT).show();
                }
                spinner.setVisibility(View.GONE);
            }
        }.execute();
    }

    //SMALL ADD
 private void showAdd(){
     AdView mAdView = (AdView) root.findViewById(R.id.adView);
     // Create an ad request. Check logcat output for the hashed device ID to
     // get test ads on a physical device. e.g.
     // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
     AdRequest adRequest = new AdRequest.Builder()
             .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
             .build();
     mAdView.loadAd(adRequest);
 }

}
