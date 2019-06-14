package com.dev.core.model;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@ToString
@Table(name = "student_info", schema = "studentinfo", catalog = "")
public class StudentInfo {
    private int id;
    private Integer classNumber;
    private String name;
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


    private User user;
    //用户ID
    @OneToOne
    @JoinColumn(name = "UID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CLASS_NUMBER")
    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "WARN")
    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    @Basic
    @Column(name = "SEX")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "PATRY")
    public String getPatry() {
        return patry;
    }

    public void setPatry(String patry) {
        this.patry = patry;
    }

    @Basic
    @Column(name = "CATE")
    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    @Basic
    @Column(name = "LOCATION1")
    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    @Basic
    @Column(name = "LOCATION2")
    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    @Basic
    @Column(name = "EMEG_CONTECT")
    public String getEmegContect() {
        return emegContect;
    }

    public void setEmegContect(String emegContect) {
        this.emegContect = emegContect;
    }

    @Basic
    @Column(name = "EMEG_PHONE")
    public String getEmegPhone() {
        return emegPhone;
    }

    public void setEmegPhone(String emegPhone) {
        this.emegPhone = emegPhone;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "STU_FROM")
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Basic
    @Column(name = "DETIAL")
    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    @Basic
    @Column(name = "MY_IMG")
    public String getMyImg() {
        return myImg;
    }

    public void setMyImg(String myImg) {
        this.myImg = myImg;
    }

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