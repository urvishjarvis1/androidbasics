package com.example.volansys.xmlparsing;

public class Country {
    private String name,capital;

    public Country() {
    }

    public Country(String name, String capital) {

        this.name = name;
        this.capital = capital;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

}
