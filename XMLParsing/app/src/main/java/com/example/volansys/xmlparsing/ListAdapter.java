package com.example.volansys.xmlparsing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter{
    private List<Country> country;
    private Context context;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Country> objects) {
        super(context, resource, objects);
        this.country=objects;
        this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listitem=inflater.inflate(R.layout.list_item,null,true);
        TextView countryName,countryCapital;
        countryName=listitem.findViewById(R.id.textView);
        countryCapital=listitem.findViewById(R.id.textView2);
        countryName.setText(country.get(position).getName());
        countryCapital.setText(country.get(position).getCapital());
        return listitem;
    }
}
