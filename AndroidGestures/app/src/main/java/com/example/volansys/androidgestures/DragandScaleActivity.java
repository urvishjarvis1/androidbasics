package com.example.volansys.androidgestures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DragandScaleActivity extends AppCompatActivity {
    private Button mbtnTouchInViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragand_scale);
        mbtnTouchInViewGroup=findViewById(R.id.btntouchinviewgroup);
        mbtnTouchInViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),TouchInViewGropu.class);
                startActivity(i);
            }
        });
    }
}
