package com.example.urvish.uicomponant.data;

import com.example.urvish.uicomponant.R;

/**
 * Created by urvish on 20/1/18.
 * Data for listview
 * Image ,title, Description
 */

public class DataAssets {
    protected static String[] nations = {"America",
            "Argentina",
            "Australia",
            "Brazil",
            "Canada",
            "Chile",
            "China",
            "Czech Republic",
            "Denmark",
            "England",
            "France",
            "Germany",
            "India",
            "Itly",
            "Mexico",
            "Netherlands",
            "Norway",
            "Poland",
            "Portugal",
            "Russia",
            "Spain",
            "Sweden",
            "Turkey",
            "Ukraine"
    };
    protected static String[] moreIn = {"America",
            "Argentina",
            "Australia",
            "Brazil",
            "Canada",
            "Chile",
            "China",
            "Czech Republic",
            "Denmark",
            "England",
            "France",
            "Germany",
            "India",
            "Itly",
            "Mexico",
            "Netherlands",
            "Norway",
            "Poland",
            "Portugal",
            "Russia",
            "Spain",
            "Sweden",
            "Turkey",
            "Ukraine"
    };

    private static  int[] imgIds = {
            R.drawable.america,
            R.drawable.argentina,
            R.drawable.australia,
            R.drawable.brazil,
            R.drawable.canada,
            R.drawable.chile,
            R.drawable.china,
            R.drawable.czechrepublic,
            R.drawable.denmark,
            R.drawable.england,
            R.drawable.france,
            R.drawable.germany,
            R.drawable.greece,
            R.drawable.india,
            R.drawable.itly,
            R.drawable.mexico,
            R.drawable.netherlands,
            R.drawable.norway,
            R.drawable.poland,
            R.drawable.portugal,
            R.drawable.russia,
            R.drawable.spain,
            R.drawable.sweden,
            R.drawable.turkey,
            R.drawable.ukraine};


public static String[] getTitle(){
    return nations;
}
    public static String[] getMoreIn(){
        return nations;
    }
    public static int[] getImg(){
        return imgIds;
    }
    public static String getTitle(int pos){
        return nations[pos];
    }
    public static int getImg(int pos){return imgIds[pos];}


}

