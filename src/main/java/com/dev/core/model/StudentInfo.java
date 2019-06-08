package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

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

    //用户ID
    @OneToOne
    @JoinColumn(name = "UID")
    private User user;

    @Column(name = "ENGLISH_NAME")
    private String englishName;

    @Column(name = "GENDER")
    private int gender;

    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "LOCATION")
    private String location;




    private int politicsStatus;

}
