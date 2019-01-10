package com.example.urvish.javafeatures.inheritancepolymorphism;

/**
 * Created by urvish on 18/1/18.
 */
class EmployeeInfo extends Person {
    private int mEmployeeId;
    public EmployeeInfo(){}
    public EmployeeInfo(int mEmployeeId) {
        this.mEmployeeId = mEmployeeId;
    }

    public EmployeeInfo(String mPersonName, int mPersonAge, int mEmployeeId) {
        super(mPersonName, mPersonAge);
        this.mEmployeeId = mEmployeeId;
    }
    void setData(int mEmployeeId) {
        this.mEmployeeId = mEmployeeId;
    }
    void setData(String mPersonName, int mPersonAge, int mEmployeeId) {
        super.setData(mPersonName, mPersonAge);
        this.mEmployeeId = mEmployeeId;
    }
    @Override
    public String toString() {
        return "Person Name:" + mPersonName +
                "\nPerson Age:" + mPersonAge +
                "\nEmployee Id:" + mEmployeeId;
    }


}
