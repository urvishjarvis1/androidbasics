package com.example.urvish.javafeatures.classinterface;

import com.example.urvish.javafeatures.classinterface.Employee;

/**
 * Created by urvish on 18/1/18.
 */
class HumanResource extends Employee implements SalaryCounterInterface{
    public float getmEmployeeSalary() {
        return mEmployeeSalary;
    }

    public void setmEmployeeSalary(float mEmployeeSalary) {
        this.mEmployeeSalary = mEmployeeSalary;
    }

    private float mEmployeeSalary;
    public HumanResource(int mEmployeeId, String mEmployeeName,float mEmployeeSalary) {
        super(mEmployeeId,mEmployeeName);
        this.mEmployeeSalary = mEmployeeSalary;
    }
    public String toString() {

        return "Employee Id:" + mEmployeeId +
                "\nEmployee Name:" + mEmployeeName +
                "\nEmployee Salary:" + mEmployeeSalary+"\n";
    }
    @Override
    public void salCounter(float increment,float tax){
        float empSal = this.getmEmployeeSalary();
        float mIncrement = increment;
        float mIncreasedSal = empSal + (empSal * mIncrement)-(tax*mIncrement);
        this.setmEmployeeSalary(mIncreasedSal);

    }
   
}
