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

}
