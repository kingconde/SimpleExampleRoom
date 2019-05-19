package com.example.amaurybadillo.exampleroom.ui.activities;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.amaurybadillo.exampleroom.R;
import com.example.amaurybadillo.exampleroom.databinding.ActivityMainBinding;
import com.example.amaurybadillo.exampleroom.db.entity.NoteEntity;
import com.example.amaurybadillo.exampleroom.ui.adapter.NoteListAdapter;
import com.example.amaurybadillo.exampleroom.ui.dialogs.ConfirmAccionDialog;
import com.example.amaurybadillo.exampleroom.viewmodel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ConfirmAccionDialog.IDialogListener {
    public static final String TAG = MainActivity.class.getName();
    public static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 100;
    private ActivityMainBinding mBinding;
    private NoteViewModel mNoteViewModel;
    private ItemTouchHelper mItemTouchHelper;
    private NoteListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
        mAdapter = new NoteListAdapter(this);
        conectToDatabase();
        startItemTocuh();
        mBinding.fab.setOnClickListener(this);
        mBinding.container.recyclerview.setAdapter(mAdapter);
        mBinding.container.recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }


    private void conectToDatabase() {
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                mAdapter.setNoteList(noteEntities);
            }
        });
    }

    private void startItemTocuh() {
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                NoteEntity noteEntity = mAdapter.getNoteAtPosition(position);
                mNoteViewModel.deleteNote(noteEntity);
            }
        });
        mItemTouchHelper.attachToRecyclerView(mBinding.container.recyclerview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:

                break;
            case R.id.action_delete_all:
                showConfirmationDelete();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showConfirmationDelete() {
        ConfirmAccionDialog.newInstance("Estas seguro de querer borrar todos las notas").show(getFragmentManager(), TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Intent intent = new Intent(this, AddNewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            NoteEntity noteEntity = new NoteEntity(data.getStringExtra(AddNewNoteActivity.EXTRA_TITLE_NOTE),
                    data.getStringExtra(AddNewNoteActivity.EXTRA_NOTE));
            mNoteViewModel.insertNote(noteEntity);
        }
    }

    @Override
    public void actionDialog(int actionDialog) {
        switch (actionDialog){
            case Activity.RESULT_OK:
                mNoteViewModel.deleteAllNote();
                break;
            case Activity.RESULT_CANCELED:

                break;
        }
    }
}
