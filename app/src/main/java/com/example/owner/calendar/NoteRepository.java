package com.example.owner.calendar;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class NoteRepository {
    private Note_Dao note_dao ;
    private LiveData<List<Note>> TimeData;
    private LiveData<List<Note>> allNotes ;
    private LiveData<List<Note>> dc;
    private TimeData timeData;
    public NoteRepository(Application application){
        Note_Database database =Note_Database.getInstance(application);
        note_dao = database.note_dao();
        TimeData = note_dao.findNoteTime(timeData.getTime());
        Log.v("***","++++ : " + timeData.getTime());
        allNotes = note_dao.getAllNotes();
        dc = note_dao.finddecription(timeData.getdc());
    }

    public void insert(Note note){
        new InsertNoteAsyncTask(note_dao).execute(note);
    }

    public void  updata(Note note){
        new UpdataNoteAsyncTask(note_dao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(note_dao).execute(note);
    }
    public void deleteAllNotes(){
        new  DeleteAllNoteAsyncTask(note_dao).execute();
    }
    public LiveData<List<Note>> getTimeData(){
        return TimeData;
    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
    public LiveData<List<Note>> getdc(){
        return dc;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private Note_Dao note_dao;

        private InsertNoteAsyncTask(Note_Dao note_dao){
            this.note_dao = note_dao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            note_dao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdataNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private Note_Dao note_dao;

        private UpdataNoteAsyncTask(Note_Dao note_dao){
            this.note_dao = note_dao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            note_dao.updata(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private Note_Dao note_dao;

        private DeleteNoteAsyncTask(Note_Dao note_dao){
            this.note_dao = note_dao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            note_dao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void>{
        private Note_Dao note_dao;

        private DeleteAllNoteAsyncTask(Note_Dao note_dao){
            this.note_dao = note_dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            note_dao.deleteAllNotes();
            return null;
        }
    }
}
