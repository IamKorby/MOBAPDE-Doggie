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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.justin.doggie.R;
import com.example.justin.doggie.activity.MainActivity;

/**
 * Created by Angelo Amadora on 4/13/2016.
 */
public class postDialogFragment extends DialogFragment {
    View v;
    boolean isLocated = false;
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
//                        TextView iu = (TextView) v.findViewById(R.id.tv_postImageUploaded);
//                        iu.setVisibility(View.VISIBLE);
                    }
                })
                .setPositiveButton("Post", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();

                        EditText etPostMsg = (EditText) v.findViewById(R.id.et_postMessage);

                        ((MainActivity) getActivity()).onPostPress(etPostMsg.getText().toString());
                    }
                });

        final ImageView locationBtn = (ImageView) v.findViewById(R.id.btn_Location);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //geoLocation shit

                if(!isLocated) {
                    locationBtn.setImageResource(R.drawable.ic_location_on_black_24dp);
                    isLocated = true;
                }else{
                    locationBtn.setImageResource(R.drawable.ic_location_off_black_24dp);
                    isLocated = false;
                }
            }
        });

        return dialogBuilder.create();
    }

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
                    TextView iu = (TextView) v.findViewById(R.id.tv_postImageUploaded);

                    //insert image uploading here

                    iu.setVisibility(View.VISIBLE);



                    if (wantToCloseDialog)
                        dismiss();
                    //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                }
            });
        }
    }
}
