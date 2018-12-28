package com.example.volansys.geocodinganddecoding;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int PLACE_AUTO_COMPLETE_REQUEST_CODE = 1;
    CardView cardView;
    private TextView mTxtViewMapAdd;
    private MapView mMapView;
    private GoogleMap mMap;
    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtViewMapAdd = findViewById(R.id.textLocationAdd);
        mTxtViewMapAdd.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTxtViewMapAdd.setSingleLine(true);
        mTxtViewMapAdd.setMarqueeRepeatLimit(-1);
        mTxtViewMapAdd.setSelected(true);
        cardView = findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(MainActivity.this);
                    startActivityForResult(intent, PLACE_AUTO_COMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        mMapView = findViewById(R.id.mapFragment);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.setMinZoomPreference(1);
                mMap.getUiSettings().setScrollGesturesEnabled(true);
                LatLng latLng = new LatLng(20.5937, 78.9629);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                initCamera();

            }
        });

    }

    private void initCamera() {
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                mLatLng = mMap.getCameraPosition().target;
                getAddressFormLocation(mLatLng.latitude, mLatLng.longitude);
            }
        });
    }

    private void getAddressFormLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address fecthcedAdd = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();
                for (int i = 0; i <= fecthcedAdd.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fecthcedAdd.getAddressLine(i)).append(" ");
                }
                mTxtViewMapAdd.setText(strAddress.toString());
            }else{
                mTxtViewMapAdd.setText(R.string.searching);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PLACE_AUTO_COMPLETE_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Place place=PlaceAutocomplete.getPlace(this,data);
                if(!place.getAddress().toString().contains(place.getName())){
                    mTxtViewMapAdd.setText(place.getName()+","+place.getAddress());
                }else{
                    mTxtViewMapAdd.setText(place.getAddress());
                }
                CameraUpdate cameraUpdate=CameraUpdateFactory.newLatLngZoom(place.getLatLng(),16);
                mMap.animateCamera(cameraUpdate);
            } else if (resultCode ==PlaceAutocomplete.RESULT_ERROR) {
                Toast.makeText(this, "Could't find address", Toast.LENGTH_SHORT).show();


            }
        }
    }
    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
