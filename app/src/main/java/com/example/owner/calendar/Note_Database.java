package com.example.owner.calendar;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

@Database(entities = {Note.class},version = 1)
public abstract class Note_Database extends RoomDatabase{
    private static Note_Database instance;

    public abstract Note_Dao note_dao();

    public static synchronized Note_Database getInstance(Context context){
        if(instance == null){
            Log.v("***","*** gettime :  " + "1" );
                instance = Room.databaseBuilder(context.getApplicationContext(),Note_Database.class,"note_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
        }
        return  instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private  Note_Dao note_dao ;

        private  PopulateDbAsyncTask(Note_Database db){
            note_dao = db.note_dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {//            note_dao.insert(new Note("2020/9/20","20"));
            note_dao.insert(new Note("2020/9/21","21"));
            note_dao.insert(new Note("2020/9/22","22"));

            Log.v("***", "*** : + 入 ");
            return null;
        }
    }
}




















































//import android.arch.persistence.db.SupportSQLiteDatabase;
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//@Database(entities = {Note.class},version = 1)
//public abstract class Note_Database extends RoomDatabase {
//    private static Note_Database instance ;
//
//    public abstract  Note_Dao noteDao();     //Room的子類別 繼承 抽象類
//
//    public static  synchronized  Note_Database getInstance(Context context){
//        if(instance == null){
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    Note_Database.class,"note_database")
//                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
//                    .build();
//        }
//        return instance ;
//    }
//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDBAsyncTask(instance).execute();
//        }
//    };
//
//    private static class  PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {
//        private Note_Dao note_Dao;
//
//        private  PopulateDBAsyncTask(Note_Database db){
//            note_Dao =db.noteDao();
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            note_Dao.insert(new Note("2020/08/01","decription 1"));
//            return null;
//        }
//    }
//}
