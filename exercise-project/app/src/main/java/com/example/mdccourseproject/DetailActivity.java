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
        TextView linkTextView = findViewById(R.id.linkTextView);

        Bundle extras = getIntent().getExtras();
        String headline = extras.getString("headline");
        String text = extras.getString("text");
        String url = extras.getString("url");

        headlineTextView.setText(headline);
        textTextView.setText(text);
        linkTextView.setText(url);
    }
}
