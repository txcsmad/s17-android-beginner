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

public class ScheduleActivity extends AppCompatActivity {
    ListView dayList;
    ArrayAdapter<String> adapter;
    List<String> days;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        days = new ArrayList<>();
        dayList = (ListView) findViewById(R.id.day_list);

        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.app_list_item, R.id.app_name, days);
        dayList.setAdapter(adapter);

        dayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String day = dayList.getItemAtPosition(i).toString();
                intent = new Intent(getApplicationContext(), EventActivity.class);
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });
    }
}
