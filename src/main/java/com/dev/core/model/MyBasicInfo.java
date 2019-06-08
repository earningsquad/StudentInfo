package com.dev.core.model;

import lombok.Data;

@Data
public class MyBasicInfo {

    private int studentId;
    private int userId;
    private String name;
    private String userName;
    private String password;
    private String fileLocation;
    private String adress;
    private String detial;

    public StudentInfo getStudentInfoE(StudentInfo studentInfoEntity){
      //  StudentInfo studentInfoEntity=new StudentInfo();
        studentInfoEntity.setId(studentId);
        studentInfoEntity.setName(name);
        studentInfoEntity.setMyImg(fileLocation);
        studentInfoEntity.setLocation1(adress);
        studentInfoEntity.setDetial(detial);
        User user=new User();
        user.setId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        studentInfoEntity.setUser(user);
        return studentInfoEntity;
    }




}
