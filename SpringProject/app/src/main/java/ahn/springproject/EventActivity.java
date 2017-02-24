package ahn.springproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    ListView eventList;
    ArrayAdapter<String> adapter;
    List<String> events;
    Button updateButton;
    EditText editText;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        events = new ArrayList<>();

        intent = getIntent();
        String day = intent.getStringExtra("day");

        switch (day) {
            case "Monday":
                events.add("10-11AM Class");
                events.add("12-2PM Contemplating life");
                events.add("3-5PM Class");
                events.add("6-7PM Attempting to do homework");
                events.add("7-9PM Social Media");
                events.add("9-12AM Actually doing homework but not really");
                break;
            case "Tuesday":
                // TIK TOK LYRICS lmao
                events.add("8-9AM Wake up feeling like P-Diddy");
                events.add("9-11AM Grab glasses and go out the door ready to hit the city");
                events.add("11AM-12PM Go back to brush teeth");
                events.add("12PM-12AM Never come back for the night");
                break;
            case "Wednesday":
                events.add("10-11AM Class");
                events.add("12-2PM Contemplating life");
                events.add("3-5PM Class");
                events.add("6-7PM Attempting to do homework");
                events.add("7-9PM Social Media");
                events.add("9-12AM Actually doing homework but not really");
                break;
            case "Thursday":
                // TIK TOK LYRICS lmao
                events.add("8-9AM Wake up feeling like P-Diddy");
                events.add("9-11AM Grab glasses and go out the door ready to hit the city");
                events.add("11AM-12PM Go back to brush teeth");
                events.add("12PM-12AM Never come back for the night");
                break;
            case "Friday":
                events.add("10-11AM Class");
                events.add("12-2PM Contemplating life");
                events.add("3-5PM Class");
                events.add("6-7PM Attempting to do homework");
                events.add("7-9PM Social Media");
                events.add("9-12AM Actually doing homework but not really");
                break;
        }

        eventList = (ListView) findViewById(R.id.event_list);
        updateButton = (Button) findViewById(R.id.update_button);
        editText = (EditText) findViewById(R.id.edit_text);

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.app_list_item, R.id.app_name, events);

        eventList.setAdapter(adapter);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                if(!input.equals(""))
                    events.add(input);

                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
    }
}
