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
                        dismiss();

                        EditText etPostMsg = (EditText) v.findViewById(R.id.et_postMessage);

                        ((MainActivity) getActivity()).onYesSelected(etPostMsg.getText().toString());
                    }
                });

        return dialogBuilder.create();
    }
}
