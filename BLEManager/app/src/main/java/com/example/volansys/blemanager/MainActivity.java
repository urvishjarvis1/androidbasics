package com.example.volansys.blemanager;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mbtnBLEOn,mbtnBLEOff,mbtnBLEDiscover;
    private Spinner mspinnerAvailableDev;
    private BluetoothAdapter mBluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        mbtnBLEOn=findViewById(R.id.btnbleon);
        mbtnBLEOff=findViewById(R.id.btnbleoff);
        mbtnBLEDiscover=findViewById(R.id.btnbledis);
        mspinnerAvailableDev=findViewById(R.id.spinner);
        mbtnBLEOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mBluetoothAdapter.isEnabled()){
                    Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(i,0);
                    Toast.makeText(MainActivity.this, "Bluetooth turned on!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth already on!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mbtnBLEOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBluetoothAdapter.disable();
                Toast.makeText(MainActivity.this, "Bluetooth turned off!", Toast.LENGTH_SHORT).show();
            }
        });
        mbtnBLEDiscover.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(i,1);

                ArrayList<String> devices=new ArrayList<>();
                for(BluetoothDevice btDevice:mBluetoothAdapter.getBondedDevices()){
                    devices.add(btDevice.getName());

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,devices);
                mspinnerAvailableDev.setAdapter(adapter);
            }
        });

    }
}
