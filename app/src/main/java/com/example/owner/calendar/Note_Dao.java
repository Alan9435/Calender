package com.example.owner.calendar;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Note_Dao {
    @Insert
    void insert(Note note);

    @Update
    void updata(Note note);

    @Delete
    void delete(Note note);


    @Query("SELECT * FROM note_table WHERE time like :timedata")
    LiveData<List<Note>> findNoteTime(String timedata);

    @Query("SELECT * FROM note_table ORDER BY id DESC")    //DESC 資料庫語法 由高至低
    LiveData<List<Note>> getAllNotes();

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table WHERE decription IN (:a)")
    LiveData<List<Note>> finddecription(String a);
}
