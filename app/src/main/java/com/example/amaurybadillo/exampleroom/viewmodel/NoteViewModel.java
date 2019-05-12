package com.example.amaurybadillo.exampleroom.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.amaurybadillo.exampleroom.NoteRepository;
import com.example.amaurybadillo.exampleroom.db.entity.NoteEntity;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository mRepository;
    private LiveData<List<NoteEntity>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return mAllNotes;
    }

    public void insertNote(NoteEntity noteEntity){
        mRepository.insertNote(noteEntity);
    }
}
