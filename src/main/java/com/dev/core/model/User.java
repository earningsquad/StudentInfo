package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

//用户表
@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //用户名
    @Column(name = "USERNAME")
    private String userName;

    //密码
    @Column(name = "PASSWORD")
    private String password;

    //角色
    @Column(name = "ROLE")
    private String role;

    //基本照片
    @Column(name = "USERIMAGE")
    private String image;

    //用户ID
    @OneToOne(mappedBy = "user")
    private StudentInfo studentInfo;

    @OneToOne(mappedBy = "user")
    private TeacherInfo teacherInfo;

}
