package com.example.justin.doggie.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();
                    }
                })
                .setNegativeButton("Upload", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();
                    }
                })
                .setPositiveButton("Post", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        //post details here
                        dismiss();

                        EditText etDogName = (EditText) v.findViewById(R.id.et_dogName);

                        ((MainActivity) getActivity()).onAdoptPress(etDogName.getText().toString());
                    }
                });

        return dialogBuilder.create();
    }
}
