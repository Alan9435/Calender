package com.example.owner.calendar;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.owner.calendar.add_note.FROM_MF;

public class permanent_fragment extends Fragment {
    EditText edit_txt;
    Button btn_updata;
    SharedPreferences sharedPreferences;
    FloatingActionButton delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permanent, container, false);

        edit_txt = view.findViewById(R.id.edit_permanent);
        btn_updata = view.findViewById(R.id.btn_updata);
        delete = view.findViewById(R.id.button_delete_edit);

        //點擊片段時
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(getActivity());
                return true;
            }
        });

        //sh資料庫定義
        sharedPreferences = getActivity().getSharedPreferences("database", Context.MODE_PRIVATE);
        edit_txt.setText(sharedPreferences.getString("Name", "Not Value"));

        btn_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("Name", edit_txt.getText().toString()).apply();
                edit_txt.setText(sharedPreferences.getString("Name", "Not Value"));

                Toast toast = Toast.makeText(getActivity(), "儲存完畢", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDialog();
            }
        });

        return view;
    }
    //隱藏小鍵盤
    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void DeleteDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_view, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_sure = dialogView.findViewById(R.id.btn_sure);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_txt.setText("");
                sharedPreferences.edit().putString("Name", edit_txt.getText().toString()).apply();
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }
}
