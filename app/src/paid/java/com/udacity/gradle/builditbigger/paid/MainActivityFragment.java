package com.udacity.gradle.builditbigger.paid;


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
import com.udacity.gradle.builditbigger.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private String JOKE = "joke";
    private final Jokes jokes;
    private ProgressBar spinner;

    public MainActivityFragment() {
        this.jokes = new Jokes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellJokeButton = (Button) root.findViewById(R.id.tellJokeButton);
        this.spinner = (ProgressBar) root.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    spinner.setVisibility(View.VISIBLE);
                new EndPointsAsyncTask(){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        if (s != null){
                            Intent myIntent = new Intent(getActivity(), LibraryActivity.class);
                            Bundle extras = new Bundle();
                            extras.putString(JOKE, s);
                            myIntent.putExtras(extras);
                            startActivity(myIntent);
                        }else{
                            Toast.makeText(getActivity(), R.string.joke_not_found, Toast.LENGTH_SHORT ).show();
                        }
                        spinner.setVisibility(View.GONE);
                    }
                }.execute();
            }
        });
        return root;
    }
}
