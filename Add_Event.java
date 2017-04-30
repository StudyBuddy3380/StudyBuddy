package com.example.jay.mutablelist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class Add_Event extends AppCompatActivity {


    Button date_Select, finishButton;
    TextView date_Display;
    EditText event_Name;
    EditText event_Location;
    Spinner spinner;

    RadioButton priorityLow, priorityMed, priorityHigh;
    /* Standard onCreate function called when the activity is created within the Activity Lifecycle.
    *  @param savedInstanceState - Bundle containing and state information that needed to be passed to the onCreate
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event);



        /* Creates a spinner adapter which can feed a list of items into the spinner 
        *  so that it can display them in a drop-down fashion
        */
        spinner = (Spinner)findViewById(R.id.event_Type); // maps the java variable to the XML element
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.events_Array,android.R.layout.simple_spinner_item); // initializes the adapter from a defined element in strings.xml
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spinner.setAdapter(adapter);// applies the adapter to the spinner


        priorityLow = (RadioButton) findViewById(R.id.radio_Prio_Low); // maps the java variable to the XML element
        priorityMed = (RadioButton) findViewById(R.id.radio_Prio_Medium); // maps the java variable to the XML element
        priorityHigh = (RadioButton) findViewById(R.id.radio_Prio_High); // maps the java variable to the XML element

        // Calls some functions that add the functionality to the XML elements
        showDialongOnButtonClick();
        textListeners();
        finishButton();
    }
    // called whenever a radio button is clicked
    public void onPrioButtonClick(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
    }
    /* This adds the functionality to the select date button, by adding an on click listener that will
    *  pull up a dialog fragment that will allow the user to input a date with a friendly user interface.
    */
    public void showDialongOnButtonClick(){ //
        date_Select = (Button) findViewById(R.id.date_Select_Button); // maps the xml button to the java variable
        date_Display = (TextView) findViewById(R.id.date_DisplayText);// maps the xml text field to the java variable

        date_Select.setOnClickListener(new View.OnClickListener() { // sets the listener to the date selector button which creates a popup to select the date
            @Override
            public void onClick(View v) {
                DialogFragment dateFrag = new datePickerFragment(date_Display);
                dateFrag.show(getSupportFragmentManager(), "Date picker"); // opens the window for the date-picker fragment



            }
        });
    }
    /* Adds the fuctionality to the finish button, which involves placing relevant data into a Bundle so that 
    *  the main page can receive the user input
    */
    public void finishButton(){
        finishButton = (Button) findViewById(R.id.finish_Button); // maps the java variable to the XML element
        finishButton.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View v) {
                Intent data = new Intent(); // creates an intent that will contain the information to be passed back
                data.putExtra("EventName", event_Name.getText().toString());
                data.putExtra("EventDate",date_Display.getText().toString());
                if (priorityLow.isChecked()) {
                    data.putExtra("EventPriority", "Low");
                }
                else if (priorityMed.isChecked()){
                    data.putExtra("EventPriority", "Medium");
                }
                else{
                    data.putExtra("EventPriority", "High");
                }
                data.putExtra("EventType",spinner.getSelectedItem().toString());
                data.putExtra("EventLocation",event_Location.getText().toString());
                setResult(RESULT_OK,data); // sets the result so that the main program knows the 2nd part finished properly, and sends the Intent with data back
                finish();

            }
        });

    }
    /* Sets some listeners to the text fields so they are cleared when focused on
    */
    public void textListeners(){
        event_Name = (EditText) findViewById(R.id.event_Name_Text);
        event_Location = (EditText) findViewById(R.id.location_Text);
        event_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    event_Name.setText("");
                }
            }
        });

        event_Location.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    event_Location.setText("");
                }
            }
        });
    }

}
