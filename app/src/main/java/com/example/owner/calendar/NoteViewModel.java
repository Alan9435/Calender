package com.example.owner.calendar;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private  NoteRepository repository ;
    private  LiveData<List<Note>> TimeData ;
    private LiveData<List<Note>> allNotes;
    private LiveData<List<Note>> dc;



    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        TimeData = repository.getTimeData();
        dc = repository.getdc();
        allNotes =  repository.getAllNotes();

    }

    public void insert(Note note){
        repository.insert(note);
    }
    public void updata(Note note){
        repository.updata(note);
    }
    public void delete(Note note){
        repository.delete(note);
    }
    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }
    public LiveData<List<Note>> getTimeData(){
        return  TimeData;
    }
    public LiveData<List<Note>> getdc(){
        return  dc;
    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
