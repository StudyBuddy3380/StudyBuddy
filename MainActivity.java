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


    String returnName, returnDate, returnPriority, returnType, returnLocation;
    ListView listView;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    /**
     * Default method for creating the structures needed for the application logic, 
     * and displaying those structures on the application
     * This particular onCreate uses the basic activity with floating action button structure
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Assigns the xml id for list to a ListView
        listView = (ListView) findViewById(R.id.list); 

        //ArrayAdapter to convert the Event object.toString() into a readable list on screen
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        
        //Default constructors for the basic activity with floating action button structure
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Add_Event.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    /**
     * onActivityResult allows the MainActivity to call the Add_Event activity for results,
     * this pauses the MainActivity, then Add_Event returns the user entered data as a bundle, which
     * is extracted thruogh the default onActivityResult method
     * @param requestCode: requestCodes must match from startActivityForResult for the correct data to be
     *                   extracted
     * @param resultCode: resultCode is received from the child activity, to confirm successful execution
     * @param data: the intent used to call startActivityForResult
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1  && resultCode == Add_Event.RESULT_OK) {
            Bundle bundle = data.getExtras();
            returnName = bundle.getString("EventName");
            returnDate = bundle.getString("EventDate");
            returnPriority = data.getStringExtra("EventPriority");
            returnType = data.getStringExtra("EventType");
            returnLocation = data.getStringExtra("EventLocation");

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

/**
 * Event class is used as the main objects for handling events provided by the user
 * @eventName: Name of the event, provided by user
 * @eventDate: Date of the event, in format MM/DD/YYYY
 * @dateYear: integer value for the year of event
 * @dateMonth: integer value for the month of event
 * @dateDayOfMonth: integer value for the day of event
 */
class Event extends AppCompatActivity{
    String eventName;
    String eventDate;
    int dateYear;
    int dateMonth;
    int dateDayOfMonth;

    /**
     * Constructs a new event object if the date is already in a String format
     * @param eventName: Name of the event
     * @param eventDate: Date of the event, in format MM/DD/YYYY
     */
    public Event(String eventName, String eventDate){
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    /**
     * Constructs a new Event object if the date is in separate integers for month, day and year
     * @param eventName: Name of the event
     * @param dateYear: Numerical year of the event
     * @param dateMonth: Numerical month of the event 
     * @param dateDayOfMonth: Numerical day of the event
     */
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
    }

    public String getEventName(){return this.eventName;}
    public int getDateYear(){return this.dateYear;}
    public int getDateMonth(){return this.dateMonth;}
    public int getDateDayOfMonth(){return this.dateDayOfMonth;}

    /**
     * Builds a string for displaying the event on the List view, in format (EventName), Due: (EventDate)
     * @return: Returns a String object of the event
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(eventName);
        sb.append(", Due: ");
        sb.append(eventDate);

        return sb.toString();
    }

}
