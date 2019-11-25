package com.example.exercise3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView homeView = (TextView) findViewById(R.id.homeView);
        TextView homeScoreView = (TextView) findViewById(R.id.homeScoreView);
        TextView guestView = (TextView) findViewById(R.id.guestView);
        TextView guestScoreView = (TextView) findViewById(R.id.guestScoreView);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            homeView.setText(extras.getString("home"));
            homeScoreView.setText(extras.getString("homescore"));
            guestView.setText(extras.getString("guest"));
            guestScoreView.setText(extras.getString("guestscore"));
        }
    }
}
