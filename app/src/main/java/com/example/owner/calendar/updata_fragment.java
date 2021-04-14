package com.example.owner.calendar;

import android.app.ActionBar;
import android.app.Application;
import android.app.DatePickerDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class updata_fragment extends Fragment implements DatePickerDialog.OnDateSetListener,ViewModelStoreOwner {
    public static final int ADD_NOTE_REQEST = 1 ;
    public static final int UPDATA_NOTE_REQEST = 2;
    NoteRepository noteRepository;
    private LiveData<List<Note>> Time_data;
    Button open_datepicker;
    String dates,months,years,content;
    EditText show_calendeer ;
    Note_Dao note_dao;
    FloatingActionButton  button_add_note ;
    String tenp;
    private  NoteViewModel noteViewModel ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_updata,container,false);
        //應該要用activty顯示查詢

        button_add_note = (FloatingActionButton)view.findViewById(R.id.button_add_note);
        button_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(open_datepicker.getText().equals("--選擇日期--")){
                    Toast.makeText(getActivity(), "請選擇日期", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getActivity(),add_note.class);
                    startActivity(intent);
                }
            }
        });
        //點開日歷
        open_datepicker = (Button) view.findViewById(R.id.open_datepicker);
        open_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePicker_DialogFragment();
                datePicker.setTargetFragment(updata_fragment.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
            }
        });
        return view;

    }
    // 選擇日期
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        dates = Integer.toString(day);
        months = Integer.toString(++month);
        years = Integer.toString(year);
        tenp =  years + "/" + months + "/" + dates;
        TimeData.setTime(tenp);
        open_datepicker.setText(TimeData.getTime());
    }
}



//noteViewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);
//        noteViewModel.getTimeData().observe(getActivity(), new Observer<List<Note>>() {
//@Override
//public void onChanged(@Nullable List<Note> notes) {
//        try {
////                    adapter.submitList(notes);
//        show_calendeer.setText( notes.get(0).getDecription());
//        Log.v("***","*** 搜尋結果:  " + notes.get(0).getDecription());
//        }catch (Exception e){
//        Log.v("***","*** error :  " +  e);
//        }
//        }
//        });