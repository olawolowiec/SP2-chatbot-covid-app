package com.example.vmac.WatBot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Statistics extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Stats> statsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        initData();
        initRecyclerActivity();
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

    private void initRecyclerActivity(){
        recyclerView = findViewById(R.id.statistics_recycler);
        StatisticsAdapter activityAdapter = new StatisticsAdapter(statsList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(activityAdapter);
    }

    private void initData(){
        statsList = new ArrayList<>();
        statsList.add(new Stats("12.12.2020",
                "14 123",
                "542",
                "421"));
        statsList.add(new Stats("11.12.2020",
                "14 123",
                "542",
                "421"));
        statsList.add(new Stats("10.12.2020",
                "14 123",
                "542",
                "421"));
        statsList.add(new Stats("09.12.2020",
                "14 123",
                "542",
                "421"));
        statsList.add(new Stats("08.12.2020",
                "14 123",
                "542",
                "421"));
        statsList.add(new Stats("07.12.2020",
                "14 123",
                "542",
                "421"));
        statsList.add(new Stats("06.12.2020",
                "14 123",
                "542",
                "421"));
    }
}