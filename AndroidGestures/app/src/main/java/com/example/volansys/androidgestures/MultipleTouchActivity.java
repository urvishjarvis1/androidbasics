package com.example.volansys.androidgestures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultipleTouchActivity extends AppCompatActivity {
    private Button mbtnDragAndScaleActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse_tracking);
        mbtnDragAndScaleActivity=findViewById(R.id.btnscalanddragactivity);
        mbtnDragAndScaleActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DragandScaleActivity.class);
                startActivity(i);
            }
        });
    }

}
