package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

//荣耀表
@Entity
@Table(name = "HONOUR")
@Data
public class Honour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //荣耀类型
    @Column(name = "TYPE")
    private int type;

    //荣耀名称
    @Column(name = "NAME")
    private String name;

    //状态
    @Column(name = "STATE")
    private int state;

    //荣耀信息
    @Column(name = "DETAIL")
    private String detail;

    public Honour() {
    }

    public Honour(int type, String name, int state, String detail) {
        this.type = type;
        this.name = name;
        this.state = state;
        this.detail = detail;
    }

    public Honour(int type, String name, String detail) {
        this.type = type;
        this.name = name;
        this.detail = detail;
    }



}
