package com.dev.core.model;

import lombok.ToString;

import javax.persistence.*;

//学生荣耀表
@Entity
@ToString
@Table(name = "HONOUR_DETAIL")
public class HonourDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //荣耀ID
    @OneToOne
    @JoinColumn(name = "HONOUR_ID")
    private Honour honour;

    //学生ID
    @OneToOne
    @JoinColumn(name = "STUDENT_ID")
    private StudentInfo studentInfo;

    //审核状态
    @Column(name = "STATE")
    private int state;

}
