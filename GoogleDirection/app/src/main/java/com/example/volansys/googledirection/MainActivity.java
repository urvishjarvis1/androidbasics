package com.example.volansys.googledirection;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.Destroyable;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int SOURCE_ADD_REQUEST_CODE = 1;
    private static final int DEST_ADD_REQUEST_CODE = 2;
    private TextView mTxtviewSourcAdd, mTxtViewDestAdd;
    private LatLng sourceLatLag, destLatLag;
    private GoogleMap mMap;
    private MapView mapView;
    private Button mBtnGetDirection;
    private String sourceAdd = null, destAdd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtviewSourcAdd = findViewById(R.id.sourceAdd);
        mTxtViewDestAdd = findViewById(R.id.destAdd);
        mBtnGetDirection = findViewById(R.id.btnDirection);
        mapView = findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        mBtnGetDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sourceAdd!=null && destAdd!=null){
                if (!sourceAdd.isEmpty() && !destAdd.isEmpty()) {

                    String url = getUrl(sourceLatLag, destLatLag);
                    FetchUrl fetchUrl = new FetchUrl();
                    fetchUrl.execute(url);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sourceLatLag, 11));
                } else {
                    if (destAdd.isEmpty())
                        mTxtViewDestAdd.setError("enter Address");
                    if (sourceAdd.isEmpty())
                        mTxtviewSourcAdd.setError("enter Address");
                }
                }else{
                    if (destAdd==null)
                        mTxtViewDestAdd.setError("enter Address");
                    if (sourceAdd==null)
                        mTxtviewSourcAdd.setError("enter Address");
                }
            }
        });
        mTxtviewSourcAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(MainActivity.this);
                    startActivityForResult(intent, SOURCE_ADD_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        mTxtViewDestAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(MainActivity.this);
                    startActivityForResult(intent, DEST_ADD_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getUrl(LatLng sourceLatLag, LatLng destLatLag) {
        String str_origin = "origin=" + sourceLatLag.latitude + "," + sourceLatLag.longitude;

        // Destination of route
        String str_dest = "destination=" + destLatLag.latitude + "," + destLatLag.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SOURCE_ADD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                sourceAdd = place.getAddress().toString();
                sourceLatLag = place.getLatLng();
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(sourceLatLag);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMap.addMarker(markerOptions);
                mTxtviewSourcAdd.setText(place.getAddress());
            }
        } else if (requestCode == DEST_ADD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                destAdd = place.getAddress().toString();
                destLatLag = place.getLatLng();
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(destLatLag);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                mMap.addMarker(markerOptions);
                mTxtViewDestAdd.setText(place.getAddress());
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        UiSettings uiSettings=mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
    }

    public class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DataParser parser = new DataParser();
                routes = parser.parse(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            ArrayList<LatLng> points;
            PolylineOptions polylineOptions = null;
            for (int i = 0; i < lists.size(); i++) {
                points = new ArrayList<>();
                polylineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = lists.get(i);
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);
                    Double lat = Double.parseDouble(point.get("lat"));
                    Double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }
                polylineOptions.addAll(points);
                polylineOptions.width(11);
                polylineOptions.color(Color.CYAN);
            }
            if (polylineOptions != null) {
                mMap.addPolyline(polylineOptions);
            } else {
                Log.d("onPostExecute", "without Polylines drawn");
            }
        }
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public class FetchUrl extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            String data="";
            try{
                data=downloadFromUrl(strings[0]);
                Log.d("Data","Data:"+data);
            }catch (Exception e){
                e.printStackTrace();
            }
            return data;
        }

        private String downloadFromUrl(String string) {
            String data="";
            InputStream is=null;

            HttpURLConnection httpURLConnection=null;
            try {
                URL url=new URL(string);
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                is=httpURLConnection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                StringBuffer bf=new StringBuffer();
                String line="";
                while ((line=br.readLine())!=null){
                    bf.append(line);
                }
                data=bf.toString();
                Log.d("data","data after download:"+data);
                br.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ParserTask parserTask=new ParserTask();
            parserTask.execute(s);
        }
    }
}
