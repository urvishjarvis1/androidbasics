package com.example.urvish.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mWeb, mLoc, mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeb = (EditText) findViewById(R.id.web);
        mLoc = (EditText) findViewById(R.id.location);
        mText = (EditText) findViewById(R.id.text);
    }

    public void openWebsite(View v) {
        String url = mWeb.getText().toString();
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        } else {
            Toast t = Toast.makeText(this, "Web URI passing not working", Toast.LENGTH_SHORT);
            t.show();



        }
    }

    public void openMap(View v) {

        String loc = mLoc.getText().toString();
        Uri addUri = Uri.parse("geo:0,0?q=" + loc);
        Intent i1 = new Intent(Intent.ACTION_VIEW, addUri);
        if (i1.resolveActivity(getPackageManager()) != null) {
            startActivity(i1);
        } else {
            Toast t = Toast.makeText(this, "location URI passing not working", Toast.LENGTH_SHORT);
            t.show();
        }
    }
    public void share(View v){
        String msg = mText.getText().toString();

        String mimeType="text/plain";
        Intent shareIntent =   ShareCompat.IntentBuilder.from(this)
                .setChooserTitle("share this message with")
                .setType(mimeType)
                .setText(msg)
                .getIntent();
        if (shareIntent.resolveActivity(getPackageManager()) != null){
            startActivity(shareIntent);
        }else{
            Toast.makeText(getApplicationContext(),"textSharing not workin",Toast.LENGTH_SHORT);
    }


    }
}
