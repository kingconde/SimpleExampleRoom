package com.example.amaurybadillo.exampleroom.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amaurybadillo.exampleroom.R;
import com.example.amaurybadillo.exampleroom.db.entity.NoteEntity;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    private LayoutInflater mInflater;
    private List<NoteEntity> mNoteList;

    public NoteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if(mNoteList != null){
            NoteEntity noteEntity = mNoteList.get(position);
            holder.titleNote.setText(noteEntity.getTitle());
            holder.note.setText(noteEntity.getNote());
        }else {
            holder.titleNote.setText("Empty");
            holder.note.setText("Empty");
        }
    }

    @Override
    public int getItemCount() {
        if(mNoteList != null)
            return mNoteList.size();
        else return 0;
    }

    public void setNoteList(List<NoteEntity> noteList){
        mNoteList = noteList;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView titleNote;
        private TextView note;
        public NoteViewHolder(View itemView) {
            super(itemView);
            titleNote = itemView.findViewById(R.id.title_note_textview);
            note = itemView.findViewById(R.id.note_textview);
        }
    }
}
