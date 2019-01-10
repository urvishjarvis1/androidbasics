package com.example.volansys.sensormanager;

import android.content.Context;
import android.hardware.Sensor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter {


    private Context mContext;
    private List<Sensor> mSensors;
    public ListAdapter(@NonNull Context context, int resource, List<Sensor> mSensors) {
        super(context, resource,mSensors);
        mContext=context;
        this.mSensors = mSensors;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=layoutInflater.inflate(R.layout.list_item,null,false);
        TextView title=v.findViewById(R.id.title);
        title.setText(mSensors.get(position).getName());
        TextView sensorId=v.findViewById(R.id.sensordata);
        sensorId.setText(mSensors.get(position).getVendor());
        Log.d("sdlc",mSensors.get(position).toString());

        Log.d("sdlc","adapter");
        return v;


    }
}
