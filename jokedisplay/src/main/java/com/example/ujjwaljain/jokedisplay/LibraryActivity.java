package com.example.ujjwaljain.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LibraryActivity extends AppCompatActivity {

    private TextView mJokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        String joke = getIntent().getStringExtra("joke");

        mJokeTextView = (TextView) findViewById(R.id.txtJoke);
        mJokeTextView.setText(joke);

    }
}
