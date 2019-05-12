package com.example.amaurybadillo.exampleroom.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "note_table")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_note")
    private int idNote;
    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    @NonNull
    @ColumnInfo(name = "note")
    private String note;

    public NoteEntity(@NonNull String title, @NonNull String note) {
        this.title = title;
        this.note = note;
    }

    @NonNull
    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(@NonNull int idNote) {
        this.idNote = idNote;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getNote() {
        return note;
    }

    public void setNote(@NonNull String note) {
        this.note = note;
    }
}
