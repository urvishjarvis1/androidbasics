package com.example.volansys.fusedlocationapi;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private Location mLocation;
    private TextView tv;
    private GoogleApiClient mGoogleApiClient;
    private static final int PLAY_SERVICE_RESOLUTION_REQ=9000;

    private LocationRequest mLocationRequest;
    private long UPDATE_INTERVAL=15000;
    private long FASTEST_INTERVAL=5000;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionRejected=new ArrayList();
    private ArrayList<String> permissions=new ArrayList();
    private final static int ALL_PERMISSIONS_RESULT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tv=findViewById(R.id.location);
        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);
        permissionsToRequest=findUnAskedPermission(permissions);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(permissionsToRequest.size()>0){
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
            }

        }
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        }



    @Override
    protected void onStart() {
        super.onStart();
        if(mGoogleApiClient!=null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!checkPlayService()){
            Toast.makeText(this, "Install Google Play service", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPlayService() {
        GoogleApiAvailability apiAvailability=GoogleApiAvailability.getInstance();
        int resultcode=apiAvailability.isGooglePlayServicesAvailable(this);
        if(resultcode!=ConnectionResult.SUCCESS){
            if(apiAvailability.isUserResolvableError(resultcode)){
                apiAvailability.getErrorDialog(this,resultcode,PLAY_SERVICE_RESOLUTION_REQ).show();
            }
            else
                finish();

            return false;


            }
        return true;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocation=LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLocation!=null){
            Toast.makeText(this, "CurrentLocation"+mLocation.getLatitude()+":"+mLocation.getLongitude(), Toast.LENGTH_SHORT).show();
            tv.setText("CurrentLocation"+mLocation.getLatitude()+":"+mLocation.getLongitude());
        }
        startLocationUpdates();

    }

    @SuppressLint("RestrictedApi")
    private void startLocationUpdates() {
     mLocationRequest=new LocationRequest();
     mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
     mLocationRequest.setInterval(UPDATE_INTERVAL);
     mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Enable Permissions", Toast.LENGTH_LONG).show();
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new com.google.android.gms.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mLocation=location;
                if(mLocation!=null){
                    tv.setText("CurrentLocation"+mLocation.getLatitude()+":"+mLocation.getLongitude());
                    Toast.makeText(MainActivity.this, "CurrentLocation"+mLocation.getLatitude()+":"+mLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }
    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionRejected.add(perms);
                    }
                }

                if (permissionRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionRejected.toArray(new String[permissionRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }
    public void stopLocationUpdates()
    {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi
                    .removeLocationUpdates(mGoogleApiClient, new com.google.android.gms.location.LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            mLocation=location;
                            if(mLocation!=null){
                                tv.setText("CurrentLocation"+mLocation.getLatitude()+":"+mLocation.getLongitude());
                                Toast.makeText(MainActivity.this, "CurrentLocation"+mLocation.getLatitude()+":"+mLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            mGoogleApiClient.disconnect();
        }
    }
    private ArrayList<String> findUnAskedPermission(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
