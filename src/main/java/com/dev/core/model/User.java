package com.dev.core.model;
import javax.persistence.*;
import java.io.Serializable;

//用户表
@Entity
@Table(name = "USER", catalog ="studentinfo" )
public class User implements Serializable {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
