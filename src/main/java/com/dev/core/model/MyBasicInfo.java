package com.dev.core.bean;

import com.dev.core.model.StudentInfoEntity;
import com.dev.core.model.User;
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

    public StudentInfoEntity getStudentInfoE(){
        StudentInfoEntity studentInfoEntity=new StudentInfoEntity();
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
