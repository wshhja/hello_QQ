package com.example.hhj.hello.Model;

public class User {
    private int mUserId;
    private String mUserName;
    private String mUserPs;

    public User(String name,String ps){
        mUserName=name;
        mUserPs=ps;
    }
    public User(){

    }
    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserPs() {
        return mUserPs;
    }

    public void setUserPs(String userPs) {
        mUserPs = userPs;
    }
}
