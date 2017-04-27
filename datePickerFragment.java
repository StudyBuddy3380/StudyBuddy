package studybuddy.csc3380.studybuddy;

        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.icu.util.Calendar;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v4.app.DialogFragment;
        import android.app.DatePickerDialog;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TextView;

/**
 * Created by Danny on 4/17/2017.
 */

public class datePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    String date;
    //int y,d,m;
    TextView Text;
   public datePickerFragment(TextView textBox){
       Text = textBox;
   }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { // code is run when the user presses 'ok' on the window
        month++;
        date = month + "-" + dayOfMonth + "-" + year;
        Text.setText(date);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance(); // instantiates a calendar to the current one
        int year = c.get(java.util.Calendar.YEAR); // sets current year
        int month = c.get(java.util.Calendar.MONTH); // sets current month
        int day = c.get(java.util.Calendar.DAY_OF_MONTH); // sets current day

        Dialog date_Dialog  = new DatePickerDialog(getActivity(), this, year, month, day);


        return date_Dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);


    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        data.putExtra("date", date);
    }
*/

}