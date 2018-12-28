package com.example.urvish.implicitintentreceiver;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i= getIntent();
        Uri uri=i.getData();
        ShareCompat.IntentReader intentReader = ShareCompat.IntentReader.from(this);
        if (intentReader.isShareIntent()) {
            String msg = intentReader.getText().toString();
            TextView textView = (TextView) findViewById(R.id.text_rec);
            textView.setText(msg);
        }
        if(uri!=null){
            String uri_string = "URI: " + uri.toString();
            TextView textView1 = (TextView) findViewById(R.id.text_rec);
            textView1.setText(uri_string);

        }

    }
}
