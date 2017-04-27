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

    RadioButton package com.example.jay.mutablelist;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event);




        spinner = (Spinner)findViewById(R.id.event_Type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.events_Array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        priorityLow = (RadioButton) findViewById(R.id.radio_Prio_Low);
        priorityMed = (RadioButton) findViewById(R.id.radio_Prio_Medium);
        priorityHigh = (RadioButton) findViewById(R.id.radio_Prio_High);

        showDialongOnButtonClick();
        textListeners();
        finishButton();
    }
    public void onPrioButtonClick(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
    }

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
    public void finishButton(){
        finishButton = (Button) findViewById(R.id.finish_Button);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("EventName", event_Name.getText().toString());
                data.putExtra("EventDate",date_Display.getText().toString);
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
                setResult(RESULT_OK,data);
                finish();

            }
        });

    }
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
        /*event_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    event_Name.setText("");
            }
        });*/
        /*event_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event_Location.setText("");
            }
        });*/
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        data.putExtra("EventName", event_Name.getText());
        data.putExtra("EventDate",date_Display.getText());
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
        data.putExtra("EventLocation",event_Location.getText());
        setResult();
    }
 */
}
