package ahn.springproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ListView listView;
    List<String> appList;
    ArrayAdapter<String> adapter;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        appList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.app_list);

        appList.add("Scarne's Dice");
        appList.add("Schedule App");
        appList.add("Infinite Kittens");

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.app_list_item, R.id.app_name, appList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        intent = new Intent(getApplicationContext(), ScarnesDiceActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), InfiniteKittens.class);
                }
                startActivity(intent);
            }
        });
    }
}
