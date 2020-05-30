package com.example.projectapp;

public class CustomerInfo {

    private String firstname;
    private String lastname;
    private String mobile;
    private String age;
    private String uid;

    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getMobile(){
        return mobile;
    }
    public String getAge(){
        return age;
    }
    public String getUid(){
        return uid;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public void setAge(String age){
        this.age = age;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public CustomerInfo(){}
    public CustomerInfo(String firstname, String lastname, String mobile, String age, String uid){
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.age = age;
        this.uid = uid;
    }
}
