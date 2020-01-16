package com.example.mdccourseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView headlineTextView = findViewById(R.id.headlineTextView);
        TextView textTextView = findViewById(R.id.textTextView);

        Bundle extras = getIntent().getExtras();
        String headline = extras.getString("headline");
        String url = extras.getString("url");
        String text = extras.getString("text");

        headlineTextView.setText(headline);
        textTextView.setText(text);
    }
}
