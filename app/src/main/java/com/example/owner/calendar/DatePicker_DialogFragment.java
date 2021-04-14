package com.example.owner.calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class DatePicker_DialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getTargetFragment(), year, month, day);
    }

}
