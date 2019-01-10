package com.example.volansys.singleton;

public class StudentContract {
    private static StudentContract sStudentContract;
    private StudentContract(){
        if(sStudentContract!=null){
            throw new RuntimeException("use getInstance Method");
        }
    }
    public static StudentContract getInstance(){
        if(sStudentContract==null){
            sStudentContract=new StudentContract();
        }
        return sStudentContract;
    }
    public StudentData getStudentInstance(){
        return new StudentData();
    }
}
