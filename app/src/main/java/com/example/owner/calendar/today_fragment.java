package com.example.owner.calendar;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class today_fragment extends Fragment {
    TextView txt ;
    // 獲取當日
    final int year = Calendar.getInstance().get(Calendar.YEAR);
    int month = Calendar.getInstance().get(Calendar.MONTH);
    final int day = Calendar.getInstance().get(Calendar.DATE);
    String timedata ;
    private NoteViewModel  noteViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today,container,false);

        txt = (TextView)view.findViewById(R.id.txt);
        month++;
        timedata = year + "/" + month + "/" + day;
        TimeData.setTime(timedata);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getTimeData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                try {
                    if (notes.isEmpty()) {
                        txt.setText("尚 未 新 增 日 程 \n 請 至 添 加 日 程 進 行 新 增");
                    } else {
                       txt.setText(notes.get(0).getDecription());
                    }
                } catch (Exception e) {
                    Log.v("***", "*** error :  " + e);
                }
            }
        });
        return view;
    }
}
