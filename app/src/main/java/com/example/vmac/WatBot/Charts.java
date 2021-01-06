package com.example.vmac.WatBot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Charts extends AppCompatActivity {


    int y0,y1,y2,y3,y4,y5,y6,z0,z1,z2,z3,z4,z5,z6;
    private List<Stats> statsList;
    GraphView graph, graph2;
    private String TAG = Statistics.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        statsList = new ArrayList<>();
        new GetData().execute();
        graph = (GraphView) findViewById(R.id.graph);
        graph2 = (GraphView) findViewById(R.id.graph2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chatbot:
                if (this.getClass().getSimpleName().equals("MainActivity")){
                    Toast.makeText(this, "Jesteś już w chatbocie!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else {
                    Intent launchNewIntent = new Intent(this, MainActivity.class);
                    startActivityForResult(launchNewIntent, 0);
                    finish();
                    return true;
                }
            case R.id.statistics:
                if (this.getClass().getSimpleName().equals("Statistics")){
                    Toast.makeText(this, "Jesteś już w statystykach!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else {
                    Intent launchNewIntent = new Intent(this, Statistics.class);
                    startActivityForResult(launchNewIntent, 0);
                    finish();
                    return true;
                }
            case R.id.charts:
                if (this.getClass().getSimpleName().equals("Charts")){
                    Toast.makeText(this, "Jesteś już w wizualizacji!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else {
                    Intent launchNewIntent = new Intent(this, Charts.class);
                    startActivityForResult(launchNewIntent, 0);
                    finish();
                    return true;
                }
            case R.id.refresh:
                finish();
                startActivity(getIntent());
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GetData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Charts.this,"Pobieram dane z bazy :)",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://covid-19-chatbot-server.herokuapp.com/stats";
            String jsonStr = sh.makeServiceCall(url);
            initNewsData(jsonStr);
            return  null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            y0 = statsList.get(0).getCases();
            y1 = statsList.get(1).getCases();
            y2 = statsList.get(2).getCases();
            y3 = statsList.get(3).getCases();
            y4 = statsList.get(4).getCases();
            y5 = statsList.get(5).getCases();
            y6 = statsList.get(6).getCases();
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, y0),
                    new DataPoint(1, y1),
                    new DataPoint(2, y2),
                    new DataPoint(3, y3),
                    new DataPoint(4, y4),
                    new DataPoint(5, y5),
                    new DataPoint(6, y6)

            });
            graph.addSeries(series);

            z0 = statsList.get(0).getDeaths();
            z1 = statsList.get(1).getDeaths();
            z2 = statsList.get(2).getDeaths();
            z3 = statsList.get(3).getDeaths();
            z4 = statsList.get(4).getDeaths();
            z5 = statsList.get(5).getDeaths();
            z6 = statsList.get(6).getDeaths();
            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, z0),
                    new DataPoint(1, z1),
                    new DataPoint(2, z2),
                    new DataPoint(3, z3),
                    new DataPoint(4, z4),
                    new DataPoint(5, z5),
                    new DataPoint(6, z6)

            });
            graph2.addSeries(series2);
            series.setTitle("Przypadki koronawirusa w ciągu ostatniego tygodnia");
            series2.setTitle("Smiertelność w ciągu ostatniego tygodnia");
        }

        protected void initNewsData(String jsonStr){
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray jsonArray = jsonObj.getJSONArray("stats");

                    // looping through All Contacts
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);
                        Stats stat = new Stats(
                                c.getString("date"),
                                c.getInt("cases"),
                                c.getInt("deaths"),
                                c.getInt("cumulative_nr")

                        );
                        statsList.add(stat);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}