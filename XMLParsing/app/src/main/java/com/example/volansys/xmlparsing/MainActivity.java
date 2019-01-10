package com.example.volansys.xmlparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Uses: ListView,PullParser,SAXParser,DOMParser
 */
public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private Button mBtnDom,mBtnSax,mBtnPull;
    ArrayList<Country> countries;
    InputStream ios;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.listview);
        mBtnSax=findViewById(R.id.saxparser);
        mBtnDom=findViewById(R.id.domparser);
        mBtnPull=findViewById(R.id.pull);
        adapter = new ListAdapter(getApplicationContext(), R.layout.list_item, countries);
        btnClicks();


    }

    /**
     * handling Button clicks
     */
    void btnClicks(){
        mBtnPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    XmlPullParserFactory xmlPullParserFactory;
                    XmlPullParser xmlPullParser=null;
                    try {
                        xmlPullParserFactory = XmlPullParserFactory.newInstance();
                        xmlPullParser = xmlPullParserFactory.newPullParser();
                        ios = getApplication().getAssets().open("coutries.xml");
                        Log.d("sdlc","ios"+ios.read());
                        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                        xmlPullParser.setInput(ios, null);



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    countries=parseXML(xmlPullParser);

                    adapter=new ListAdapter(getApplicationContext(),R.layout.list_item,countries);
                    mListView.setAdapter(adapter);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        });
        mBtnDom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               countries=parseXXMLDom();
                Log.d("count","count:"+countries.size());
                Log.d("count","count:"+countries.get(0).getCapital());
                Log.d("count","count:"+countries.get(1).getCapital());
                Log.d("count","count:"+countries.get(2).getCapital());
               adapter=new ListAdapter(getApplicationContext(),R.layout.list_item,countries);
               mListView.setAdapter(adapter);

            }
        });
        mBtnSax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countries=parseXMLSAX();
                Log.d("count","count:"+countries.size());
                Log.d("count","count:"+countries.get(0).getCapital());
                Log.d("count","count:"+countries.get(1).getCapital());
                Log.d("count","count:"+countries.get(2).getCapital());

                adapter=new ListAdapter(getApplicationContext(),R.layout.list_item,countries);
                mListView.setAdapter(adapter);



            }
        });

    }

    /**
     * parsing using PullParser
     * @param parser=XmlPullParser
     * @return Arraylist of Countries
     * @throws IOException
     * @throws XmlPullParserException
     */
    ArrayList<Country> parseXML(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<Country> countries = null;
        int evenId = parser.getEventType();
        Country country = null;



        while (evenId != XmlPullParser.END_DOCUMENT) {
            String name;
            switch (evenId) {
                case XmlPullParser.START_DOCUMENT:
                    countries = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("country")) {
                        country = new Country();
                    } else if (country != null) {
                        if (name.equals("name"))
                            country.setName(parser.nextText());
                        else if (name.equals("capital"))
                            country.setCapital(parser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("country") && country != null) {
                        countries.add(country);
                    }
                    break;

            }
            evenId=parser.next();

        }
        return countries;
    }

    /**
     * parsing XML using SAX method
     * @return Arraylist of Countries
     */
    public ArrayList<Country> parseXMLSAX(){
        ArrayList<Country> countriesTemp=null;
        try{
            SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
            SAXParser saxParser=saxParserFactory.newSAXParser();
            XMLReader xmlReader=saxParser.getXMLReader();
            SAXXMLHandler saxxmlHandler=new SAXXMLHandler();
            xmlReader.setContentHandler(saxxmlHandler);
            ios = getApplication().getAssets().open("country.xml");
           xmlReader.parse(new InputSource(ios));
           countriesTemp=saxxmlHandler.getCountries();
            Log.d("count","count:"+countriesTemp.get(0).getCapital());
            Log.d("count","count:"+countriesTemp.get(1).getCapital());
            Log.d("count","count:"+countriesTemp.get(2).getCapital());
        }catch (Exception e){
            e.printStackTrace();
        }
        return countriesTemp;
    }

    /**
     * parsing XML document using DOM method
     * @return Arraylist of Countries
     */
    ArrayList<Country> parseXXMLDom(){
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document document;
        ArrayList<Country> countriesTemp=new ArrayList<>();
        Country country=null;
        try {
            documentBuilder=documentBuilderFactory.newDocumentBuilder();
            ios=getAssets().open("country.xml");
            document=documentBuilder.parse(ios);
            Element element=document.getDocumentElement();
            element.normalize();
            NodeList nodeList=document.getElementsByTagName("country");
            for(int i=0;i<nodeList.getLength();i++){
                Node node=nodeList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element1=(Element) node;
                    country=new Country();
                    country.setName(getValuefromXML("name",element1));
                    country.setCapital(getValuefromXML("capital",element1));
                    countriesTemp.add(country);
                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        for(int i=0;i<countriesTemp.size();i++){
            Log.d("actvalue",""+countriesTemp.get(i).getName());
            Log.d("actvalue",""+countriesTemp.get(i).getCapital());

        }
        return countriesTemp;
    }

    /**
     * get value from DOM tree
     * @param tag=tag name
     * @param element=element
     * @return string
     */
    private static String getValuefromXML(String tag,Element element){
        NodeList nodeList=element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node=(Node)nodeList.item(0);
        Log.d("nodevalue",node.getNodeValue());
        return node.getNodeValue();
    }

}

