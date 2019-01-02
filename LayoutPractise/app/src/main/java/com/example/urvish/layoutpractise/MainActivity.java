package com.example.urvish.layoutpractise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void linerLayout(View view) {
        Intent intent = new Intent(this, Linear.class);
        startActivity(intent);
    }


    public void tabLayout(View view) {
        Intent intent=new Intent(this,Tab.class);
        startActivity(intent);
    }

    public void relativeLayout(View view) {
        Intent intent = new Intent(this, Relative.class);
        startActivity(intent);
    }

    public void gridLayout(View view) {
        Intent intent = new Intent(this, Grid.class);
        startActivity(intent);
    }

    public void frameLayout(View view) {
        Intent intent=new Intent(this,Frame.class);
        startActivity(intent);
    }
    public void absLayout(View view) {
        Intent intent = new Intent(this, Absolute.class);
        startActivity(intent);
    }

    public void coLayout(View view) {
        Intent intent = new Intent(this, Constraint.class);
        startActivity(intent);
    }
}
