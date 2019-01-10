    package com.example.volansys.sensormanager;

    import android.hardware.Sensor;
    import android.hardware.SensorEvent;
    import android.hardware.SensorEventListener;
    import android.hardware.SensorManager;

    import android.support.v7.app.AlertDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import java.util.EventListener;
    import java.util.List;

    public class MainActivity extends AppCompatActivity implements SensorEventListener {
        private SensorManager mSensorManager;
        private Sensor mSensor;
        private String data;
        List<Sensor> mSensorList;
        private AlertDialog.Builder mAlertDialog;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mAlertDialog= new AlertDialog.Builder(MainActivity.this);
            mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
            mSensorList=mSensorManager.getSensorList(Sensor.TYPE_ALL);
            Log.d("sdlc","count:"+mSensorList.size());
             Log.d("sdlc","mainactivity");
            ListView listView=findViewById(R.id.list);
            ListAdapter listAdapter=new ListAdapter(this,R.layout.list_item,mSensorList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mSensor=mSensorManager.getDefaultSensor(mSensorList.get(position).getType());
                    mAlertDialog.setTitle(mSensorList.get(position).getName());
                    mAlertDialog.create().show();

                }
            });
        }


        @Override
        protected void onResume() {
            super.onResume();
            for(Sensor s:mSensorList)
            mSensorManager.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        protected void onPause() {
            super.onPause();
            for(Sensor s:mSensorList)
                mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            data="";
            int i=0;
            while (i<event.values.length){
                data=data+"\n"+event.values[i];
                i++;
            }
            mAlertDialog.setMessage(data);
            Log.d("sdlc",data);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
