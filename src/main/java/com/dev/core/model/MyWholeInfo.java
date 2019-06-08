package com.dev.core.model;

//学生信息表

public class MyWholeInfo extends StudentInfo {

    public MyBasicInfo getMyBasicInfo(){
        MyBasicInfo myBasicInfo=new MyBasicInfo();
        myBasicInfo.setAdress(getLocation1());
        myBasicInfo.setDetial(getDetial());
        myBasicInfo.setFileLocation(getMyImg());
        myBasicInfo.setName(getName());
        myBasicInfo.setStudentId(getId());
        myBasicInfo.setUserId(getUser().getId());
        myBasicInfo.setUserName(getUser().getUserName());
        myBasicInfo.setPassword(getUser().getPassword().substring(0,3)+"****"+getUser().getPassword().substring(getUser().getPassword().length()-3,getUser().getPassword().length()-1));
        return myBasicInfo;
    }

}
