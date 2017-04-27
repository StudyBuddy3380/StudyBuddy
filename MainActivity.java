package com.example.jay.mutablelist;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    String eName, eDate;
    ListView listView;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Add_Event.class);
                startActivityForResult(intent, 1);
                //onActivityResult(1, 1, intent);



               //listItems.add((new Event("Final", 2017, 5, 1)).toString());
                //adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1  && resultCode == Add_Event.RESULT_OK) {
            String returnName = data.getStringExtra("EventName");
            String returnDate = data.getStringExtra("EventDate");
            String returnPriority = data.getStringExtra("EventPriority");
            String returnType = data.getStringExtra("EventType");
            String returnLocation = data.getStringExtra("EventLocation");


            listItems.add((new Event(returnName, returnDate)).toString());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class Event extends AppCompatActivity{
    String eventName;
    String eventDate;
    int dateYear;
    int dateMonth;
    int dateDayOfMonth;

    public Event(String eventName, String eventDate){
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public Event(String eventName, int dateYear, int dateMonth, int dateDayOfMonth){
        this.eventName = eventName;
        this.dateYear = dateYear;
        this.dateMonth = dateMonth;
        this.dateDayOfMonth = dateDayOfMonth;
        this.eventDate = dateMonth + "/" + dateDayOfMonth + "/" + dateYear;

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(dateYear, dateMonth, dateDayOfMonth);
        Calendar endTime = Calendar.getInstance();
        endTime.set(dateYear, dateMonth, dateDayOfMonth);
        /*Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, eventName);
        startActivity(intent);*/



    }

    public String getEventName(){return this.eventName;}
    public int getDateYear(){return this.dateYear;}
    public int getDateMonth(){return this.dateMonth;}
    public int getDateDayOfMonth(){return this.dateDayOfMonth;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(eventName);
        sb.append(", Due: ");
        sb.append(eventDate);

        return sb.toString();
    }

}
