package com.example.amaurybadillo.exampleroom.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.amaurybadillo.exampleroom.R;

public class ConfirmAccionDialog extends DialogFragment{
    public static final String TAG = ConfirmAccionDialog.class.getName();
    public static final String BUNDLE_CONFIRM_ACCION = "BUNDLE_CONFIRM_ACCION";
    private IDialogListener mIDialogListener;
    private String mMessage;
    private Bundle mBundle;

    public ConfirmAccionDialog() {
    }

    public static ConfirmAccionDialog newInstance(String mMessage){
        ConfirmAccionDialog confirmAccionDialog = new ConfirmAccionDialog();
        Bundle mBundle = new Bundle();
        mBundle.putString(BUNDLE_CONFIRM_ACCION, mMessage);
        confirmAccionDialog.setArguments(mBundle);
        return confirmAccionDialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mIDialogListener = (IDialogListener)context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        loadBundle();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(mMessage)
                .setPositiveButton(R.string.btn_positive_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        mIDialogListener.actionDialog(id);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.btn_negative_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        mIDialogListener.actionDialog(id);
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void loadBundle() {
        if(getArguments() != null){
            mBundle = getArguments();
            mMessage = mBundle.getString(BUNDLE_CONFIRM_ACCION);
        }
    }

    public interface IDialogListener{
        void actionDialog(int actionDialog);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mIDialogListener = null;
    }
}
