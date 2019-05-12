package com.example.amaurybadillo.exampleroom.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.amaurybadillo.exampleroom.R;
import com.example.amaurybadillo.exampleroom.databinding.ActivityAddNewNoteBinding;
import com.example.amaurybadillo.exampleroom.db.entity.NoteEntity;

public class AddNewNoteActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = AddNewNoteActivity.class.getName();
    public static final String EXTRA_TITLE_NOTE = "EXTRA_TITLE_NOTE";
    public static final String EXTRA_NOTE = "EXTRA_NOTE";
    private ActivityAddNewNoteBinding mBinding;
    private NoteEntity noteEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_note);
        setSupportActionBar(mBinding.toolbar);

        mBinding.saveNoteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_note_btn:
                Intent intent = new Intent();
                intent.putExtra(EXTRA_TITLE_NOTE, mBinding.titleEditText.getText().toString());
                intent.putExtra(EXTRA_NOTE, mBinding.noteEditText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
