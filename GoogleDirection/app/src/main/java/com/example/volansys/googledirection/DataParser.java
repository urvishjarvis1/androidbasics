package com.example.volansys.googledirection;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {
    public List<List<HashMap<String,String>>> parse(JSONObject jsonObject){
        List<List<HashMap<String,String>>> routes=new ArrayList<>();
        JSONArray jRouts;
        JSONArray jLegs;
        JSONArray jSteps;

        try {
            jRouts=jsonObject.getJSONArray("routes");
            for(int i=0;i<jRouts.length();i++){
                jLegs=((JSONObject)jRouts.get(i)).getJSONArray("legs");
                List path=new ArrayList<>();
                for(int j=0;j<jLegs.length();j++){
                    jSteps=((JSONObject)jLegs.get(j)).getJSONArray("steps");
                    for(int k=0;k<jSteps.length();k++){
                        String ployLine="";
                        ployLine=(String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                        List<LatLng> list=decodePloy(ployLine);
                        for (int l=0;l<list.size();l++){
                            HashMap<String,String> hm=new HashMap<>();
                            hm.put("lat",Double.toString(list.get(l).latitude));
                            hm.put("lng",Double.toString(list.get(l).longitude));
                            path.add(hm);
                        }
                    }
                    routes.add(path);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return routes;
    }

    private List<LatLng> decodePloy(String ployLine) {
        List<LatLng> poly=new ArrayList<>();
        int index=0;
        int len=ployLine.length();
        int lat=0,lng=0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = ployLine.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = ployLine.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}
