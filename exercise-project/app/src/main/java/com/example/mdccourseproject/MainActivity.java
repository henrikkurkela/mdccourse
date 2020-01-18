package com.example.mdccourseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.ItemClickListener {

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        final ArrayList<AdapterData> newsItems = new ArrayList<>();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, newsItems);
        adapter.setClickListener(this);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(adapter);

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://feeds.yle.fi/uutiset/v1/majorHeadlines/YLE_UUTISET.rss";

        /* Set up a Volley Request according to https://developer.android.com/training/volley/simple.html */
        final StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Request Successful", Toast.LENGTH_LONG).show();

                        String ITEM = "item";
                        String TITLE = "title";
                        String DESCRIPTION = "encoded";
                        String LINK = "link";

                        AdapterData item = new AdapterData("NULL", "NULL", "NULL");

                        /* Parse the XML data according to https://developer.android.com/reference/org/xmlpull/v1/XmlPullParser
                        and https://www.vogella.com/tutorials/AndroidXML/article.html */
                        try {

                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            factory.setNamespaceAware(true);
                            XmlPullParser xpp = factory.newPullParser();

                            xpp.setInput(new StringReader(response)); // pass input whatever xml you have
                            int eventType = xpp.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String name = null;
                                if (eventType == XmlPullParser.START_DOCUMENT) {
                                } else if (eventType == XmlPullParser.START_TAG) {
                                    name = xpp.getName();
                                    if (name.equalsIgnoreCase(ITEM)) {
                                        Log.i("new item", "Create new item");
                                        item = new AdapterData("NULL", "NULL", "NULL");
                                    } else if (item.getHeadline() == "NULL" || item.getText() == "NULL" || item.getUrl() == "NULL") {
                                        if (name.equalsIgnoreCase(LINK)) {
                                            Log.i("Attribute", "setLink");
                                            item.setUrl(xpp.nextText());
                                        } else if (name.equalsIgnoreCase(DESCRIPTION)) {
                                            Log.i("Attribute", "description");
                                            item.setText(Html.fromHtml(xpp.nextText(), Html.FROM_HTML_MODE_LEGACY).toString().trim());
                                        } else if (name.equalsIgnoreCase(TITLE)) {
                                            Log.i("Attribute", "title");
                                            item.setHeadline(xpp.nextText().trim());
                                        }
                                    }
                                } else if (eventType == XmlPullParser.END_TAG) {
                                    if (item.getHeadline() != "NULL" && item.getText() != "NULL" && item.getUrl() != "NULL") {
                                        Log.i("Added", item.toString());
                                        newsItems.add(item);
                                        item = new AdapterData("NULL", "NULL", "NULL");;
                                    }

                                } else if (eventType == XmlPullParser.TEXT) {
                                }
                                eventType = xpp.next();
                            }
                            Log.d("", "End document");

                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Request Failed", Toast.LENGTH_LONG).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(request);

        // Setup Floating Action Button for refreshing news items
        FloatingActionButton fab = findViewById(R.id.refreshButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsItems.clear();
                queue.add(request);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position).getHeadline() + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent displayNewsDetail = new Intent(getApplicationContext(), DetailActivity.class);
        displayNewsDetail.putExtra("headline", adapter.getItem(position).getHeadline());
        displayNewsDetail.putExtra("url", adapter.getItem(position).getUrl());
        displayNewsDetail.putExtra("text", adapter.getItem(position).getText());
        startActivity(displayNewsDetail);
    }

    public void refreshData() {

    }
}
