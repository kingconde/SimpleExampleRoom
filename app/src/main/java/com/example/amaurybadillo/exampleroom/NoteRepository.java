package com.example.amaurybadillo.exampleroom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.amaurybadillo.exampleroom.db.NoteRoomDatabase;
import com.example.amaurybadillo.exampleroom.db.dao.NoteDao;
import com.example.amaurybadillo.exampleroom.db.entity.NoteEntity;

import java.util.List;

public class NoteRepository {
    private NoteDao mNoteDao;
    private LiveData<List<NoteEntity>> mAllNotes;

    public NoteRepository(Application application) {
        NoteRoomDatabase database = NoteRoomDatabase.getDatabase(application);
        mNoteDao = database.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<NoteEntity>> getAllNotes(){
        return mAllNotes;
    }

    public void insertNote(NoteEntity note){
        new insertAsyncTask(mNoteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<NoteEntity, Void, Void>{
        private NoteDao mAsyncTaskDao;

        public insertAsyncTask(NoteDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(NoteEntity... notes) {
            mAsyncTaskDao.insertNote(notes[0]);
            return null;
        }
    }
}
