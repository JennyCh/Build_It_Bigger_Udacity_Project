package com.example.jenny.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        Bundle bundle = getIntent().getExtras();

        String joke = bundle.getString("joke");
        Log.v("LibraryActivity", joke);

        TextView jokeView = (TextView) findViewById(R.id.joke_view);
        jokeView.setText(joke);

    }




}
