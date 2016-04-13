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
 * Created by Angelo Amadora on 4/13/2016.
 */
public class postDialogFragment extends DialogFragment {
    View v;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        v = LayoutInflater.from(getActivity())
                .inflate(R.layout.post_input, null);

        AlertDialog.Builder dialogBuilder
                = new AlertDialog.Builder(getActivity())
                .setTitle("Stop")
                .setMessage("Are you sure you want to do this?")
                .setView(v)
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();

                        EditText etName = (EditText) v.findViewById(R.id.et_name);

                        ((MainActivity) getActivity()).onYesSelected(etName.getText().toString());
                    }
                });

        return dialogBuilder.create();
    }
}
