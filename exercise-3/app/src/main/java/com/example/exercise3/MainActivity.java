package com.example.exercise3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<AdapterData> gamesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetXmlAsyncTask runner = new GetXmlAsyncTask();
        runner.execute();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new Adapter(gamesList);
        recyclerView.setAdapter(mAdapter);
    }

    public void parseXml(XmlPullParser parser) throws IOException, XmlPullParserException {
        int eventType = -1;
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                String gameValue = parser.getName();
                if (gameValue.equals("game")) {
                    String home = parser.getAttributeValue(null, "home");
                    String homescore = parser.getAttributeValue(null, "homescore");
                    String guest = parser.getAttributeValue(null, "guest");
                    String guestscore = parser.getAttributeValue(null, "guestscore");
                    addGame (home, homescore, guest, guestscore);
                }
            }
            eventType = parser.next();
        }
    }

    private void addGame(String home, String homescore, String guest, String guestscore) {
        gamesList.add(new AdapterData(home, homescore, guest, guestscore));
    }

    private class GetXmlAsyncTask extends android.os.AsyncTask<Object, Object, Object> {
        InputStream text;
        private XmlPullParserFactory xmlFactoryObject;
        private XmlPullParser myParser;

        @Override
        protected Object doInBackground(Object[] objects){
            URL url;
            try {
                url = new URL("http://10.0.2.2/games.xml");
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                InputStream is = con.getInputStream();
                xmlFactoryObject = XmlPullParserFactory.newInstance();
                myParser = xmlFactoryObject.newPullParser();
                myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                myParser.setInput(is, null);
                text = is;
            }catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            try {
                parseXml(myParser);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
