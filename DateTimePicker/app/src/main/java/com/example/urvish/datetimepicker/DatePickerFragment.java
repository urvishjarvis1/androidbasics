package com.example.urvish.datetimepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    public DatePickerFragment() {
        // Required empty public constructor
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), this, year, month, day);
    }





    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
            MainActivity activity=(MainActivity) getActivity();
            activity.processDatePicker(year,month,day);
    }


}
