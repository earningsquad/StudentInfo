package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CLASS_INFORMATION")
@Data
public class ClassInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //信息类型
    @Column(name = "TYPE")
    private int type;
    @Column(name = "CLASS_NUMBER")
    private int classnumber;
    //信息名称
    @Column(name = "NAME")
    private String name;

    //信息名称
    @Column(name = "INTRODUCTION")
    private String introduction;

    @Column(name = "AMOUNT")
    private int amount;

}
