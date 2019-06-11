package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

//学生信息表
@Entity
@Table(name = "STUDENT_INFO")
@Data
public class StudentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //学生名
    @Column(name = "NAME")
    private String name;

    //学生班级
    @Column(name = "CLASS_NUMBER")
    private int classNumber;

    //学业预紧
    @Column(name = "WARN")
    private String warn;
    private String sex;
    private String patry;
    private String cate;
    private String location1;
    private String location2;
    private String emegContect;
    private String emegPhone;
    private String phone;
    private String from;
    private String detial;
    private String myImg;
    private String birthday;
    private String engName;
    @Column(name = "ENG_NAME")
    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    @Column(name = "BIRTHDAY")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    @ManyToMany(targetEntity = StudentInfo.class,fetch=FetchType.EAGER)
    @JoinTable(name = "HONOUR_DETAIL",joinColumns = @JoinColumn(name="HONOUR_ID"),inverseJoinColumns =@JoinColumn(name="STUDENT_ID"))
    private List students;

    @Transient
    public MyBasicInfo getMyBasicInfo(){
        MyBasicInfo myBasicInfo=new MyBasicInfo();
        myBasicInfo.setAdress(getLocation1());
        myBasicInfo.setDetial(getDetial());
        myBasicInfo.setFileLocation(getMyImg());
        myBasicInfo.setName(getName());
        myBasicInfo.setStudentId(getId());
        myBasicInfo.setUserId(getUser().getId());
        myBasicInfo.setUserName(getUser().getUserName());
        //myBasicInfo.setPassword(getUser().getPassword().substring(0,3)+"****"+getUser().getPassword().substring(getUser().getPassword().length()-3,getUser().getPassword().length()-1));
        String passwdFirst=getUser().getPassword().substring(0,1);
        String passwdLAst=getUser().getPassword().substring(getUser().getPassword().length()-2,getUser().getPassword().length()-1);
        myBasicInfo.setPassword(passwdFirst+"****"+passwdLAst);


        return myBasicInfo;
    }


    //用户ID
    @OneToOne
    @JoinColumn(name = "UID")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentInfo that = (StudentInfo) o;
        return id == that.id &&
                Objects.equals(classNumber, that.classNumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(warn, that.warn) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(patry, that.patry) &&
                Objects.equals(cate, that.cate) &&
                Objects.equals(location1, that.location1) &&
                Objects.equals(location2, that.location2) &&
                Objects.equals(emegContect, that.emegContect) &&
                Objects.equals(emegPhone, that.emegPhone) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(from, that.from) &&
                Objects.equals(detial, that.detial) &&
                Objects.equals(myImg, that.myImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classNumber, name, warn, sex, patry, cate, location1, location2, emegContect, emegPhone, phone, from, detial, myImg);
    }
}
