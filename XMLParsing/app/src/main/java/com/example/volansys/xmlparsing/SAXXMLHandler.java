package com.example.volansys.xmlparsing;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXXMLHandler extends DefaultHandler {
    private ArrayList<Country> countries;
    private String tempvalue;
    Country country;
    public SAXXMLHandler() {
        countries=new ArrayList<>();
    }

    public ArrayList<Country> getCountries() {
        Log.d("count","countsax:"+countries.get(0).getCapital());
        Log.d("count","countsax:"+countries.get(1).getCapital());
        Log.d("count","countsax:"+countries.get(2).getCapital());
        return countries;
    }


    /**
     * Receive notification of the start of an element.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#startElement
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tempvalue="";
        if(qName.equals("country")){
            country=new Country();
        }
    }

    /**
     * Receive notification of character data inside an element.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#characters
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tempvalue=new String(ch,start,length).trim();
        Log.d("count","string:"+tempvalue);
    }

    /**
     * Receive notification of the end of an element.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#endElement
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("country")){
            countries.add(country);
            Log.d("count","countljla:"+country.getCapital());
            Log.d("count","countljla:"+country.getName());
        }else if(qName.equals("name")){
            country.setName(tempvalue);
            Log.d("count","countljla:"+country.getName());
        }else if(qName.equals("capital")){
            country.setCapital(tempvalue);
            Log.d("count","countljla:"+country.getCapital());

        }
    }
}
