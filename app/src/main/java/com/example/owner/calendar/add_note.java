package com.example.owner.calendar;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Database;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class add_note extends AppCompatActivity {
    public static final String FROM_MF = "com.example.owner.calendar";
    String Title;
    String decription;
    EditText edit_description;
    int ch_jump;
    boolean Updata_ch;
    private int id;
    private FloatingActionButton delete_note;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);    //設定取消按鈕

        edit_description = findViewById(R.id.edit_description);

        delete_note = findViewById(R.id.button_delete_note);                  //刪除按鈕
        delete_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDialog();
            }
        });

        //代表從memorandum_fragment來的
        Intent intent = getIntent();
        if (intent.hasExtra(FROM_MF)) {
            ch_jump = 2;
        }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getTimeData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                try {
                    Log.v("***", "***" + notes.isEmpty());
                    if (notes.isEmpty()) {
                        //新增
                        delete_note.setClickable(false);
                        Updata_ch = false;
                        Title = "Add";
                    } else {
                        //搜尋 或 更新
                        delete_note.setClickable(true);
                        Updata_ch = true;
                        Title = "Search and Updata";
                        id = notes.get(0).getId();
                        edit_description.setText(notes.get(0).getDecription());
                        Log.v("***", "*** 搜尋結果:  " + notes.get(0).getDecription());
                    }
                    setTitle(TimeData.getTime() + " " + Title);
                } catch (Exception e) {
                    Log.v("***", "*** error :  " + e);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveNote() {
        try {
            decription = edit_description.getText().toString();
            if (Updata_ch) {
                //updata
                Note note = new Note(TimeData.getTime(), decription);
                note.setId(id);
                noteViewModel.updata(note);
                Toast.makeText(this, "Updata : " + note.getDecription(), Toast.LENGTH_SHORT).show();
            } else {
                //insert
                Note note = new Note(TimeData.getTime(), decription);
                noteViewModel.insert(note);
                Toast.makeText(this, "insert : " + note.getDecription(), Toast.LENGTH_SHORT).show();
            }
            if (ch_jump == 2) {
                Intent intent = new Intent(add_note.this, MainActivity.class);
                intent.putExtra("id", 2);                //帶參數跳轉 跳轉頁判斷數值在跳至Fragment
                startActivity(intent);
            } else {
                Intent intent = new Intent(add_note.this, MainActivity.class);
                intent.putExtra("id", 1);                //帶參數跳轉 跳轉頁判斷數值在跳至Fragment
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.v("***", "*** error : " + e);
        }
    }

    private void DeleteDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
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
                Note note = new Note(TimeData.getTime(), edit_description.getText().toString());
                note.setId(id);
                noteViewModel.delete(note);
                Toast.makeText(add_note.this, "已刪除資料", Toast.LENGTH_SHORT).show();
                if (ch_jump == 2) {
                    Intent intent = new Intent(add_note.this, MainActivity.class);
                    intent.putExtra("id", 2);                //帶參數跳轉 跳轉頁判斷數值在跳至Fragment
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(add_note.this, MainActivity.class);
                    intent.putExtra("id", 1);                //帶參數跳轉 跳轉頁判斷數值在跳至Fragment
                    startActivity(intent);
                }
            }
        });
        alertDialog.show();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        closekeyboard();
        return true;
    }
    //收鍵盤
    private  void closekeyboard(){
        View view =this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
