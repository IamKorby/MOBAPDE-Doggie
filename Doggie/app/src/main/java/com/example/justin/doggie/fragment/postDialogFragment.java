package com.example.justin.doggie.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justin.doggie.R;
import com.example.justin.doggie.activity.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Angelo Amadora on 4/13/2016.
 */
public class postDialogFragment extends DialogFragment {
    View v;
    boolean isLocated = false;
    private GoogleApiClient googleApiClient;
    double postLat;
    double postLong;

    GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(Bundle bundle) {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(5000);

            if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    googleApiClient,
                    locationRequest,
                    locationListener
            );
        }

        @Override
        public void onConnectionSuspended(int i) {

        }
    };

    com.google.android.gms.location.LocationListener locationListener = new com.google.android.gms.location.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            location.getLatitude();

            //
            Toast.makeText(getActivity().getBaseContext(), "Location succeeded!", Toast.LENGTH_LONG).show();
            postLat= location.getLatitude();
            postLong = location.getLongitude();

        }
    };

    GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {

        }
    };
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
                Toast.makeText(getActivity().getBaseContext(), "starting geo location", Toast.LENGTH_LONG).show();
                googleApiClient = new GoogleApiClient.Builder(getActivity().getBaseContext())
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(connectionCallbacks)
                        .addOnConnectionFailedListener(connectionFailedListener)
                        .build();

                googleApiClient.connect();


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
