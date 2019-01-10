package com.example.urvish.javafeatures.inheritancepolymorphism;

/**
 * Created by urvish on 18/1/18.
 */
class Person {

    protected String mPersonName;
    protected int mPersonAge;

    public Person() {
        this.mPersonName = "";
        this.mPersonAge = 0;
    }

    public Person(String mPersonName, int mPersonAge) {
        this.mPersonName = mPersonName;
        this.mPersonAge = mPersonAge;
    }
    void setData(String mPersonName, int mPersonAge) {
        this.mPersonName = mPersonName;
        this.mPersonAge = mPersonAge;
    }

    @Override
    public String toString() {
        return "Person Name:" + mPersonName
                + "\nPerson Age:" + mPersonAge + "\n";
    }

}
