package com.example.justin.doggie.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.justin.doggie.R;
import com.example.justin.doggie.activity.MainActivity;

/**
 * Created by Angelo Amadora on 4/14/2016.
 */
public class adoptionDialogFragment extends DialogFragment {

    View v;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        v = LayoutInflater.from(getActivity())
                .inflate(R.layout.adoption_input, null);

        AlertDialog.Builder dialogBuilder
                = new AlertDialog.Builder(getActivity())
                .setTitle("Post")
                .setView(v)
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setNegativeButton("Upload", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //post details here
                        dismiss();

                        EditText etDogName = (EditText) v.findViewById(R.id.et_dogName);
                        ((MainActivity) getActivity()).onAdoptPress(etDogName.getText().toString());
                    }
                });

        return dialogBuilder.create();
    }

    @Override
    public void onStart() {
        super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button negativeButton = (Button) d.getButton(Dialog.BUTTON_NEGATIVE);
            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vi) {
                    Boolean wantToCloseDialog = false;
                    //Do stuff, possibly set wantToCloseDialog to true then...
                        TextView iu = (TextView) v.findViewById(R.id.tv_adoptionImageUploaded);

                    //insert image uploading code here

                        iu.setVisibility(View.VISIBLE);

                    if (wantToCloseDialog)
                        dismiss();
                    //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                }
            });
        }
    }
}
