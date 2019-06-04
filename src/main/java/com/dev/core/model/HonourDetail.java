package com.dev.core.model;

import javax.persistence.*;

//学生荣耀表
@Entity
@Table(name = "HONOUR_DETAIL")
public class HonourDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //荣耀ID
    @OneToOne
    @JoinColumn(name = "honour_id")
    private Honour honour;

    //学生ID
    private StudentInfo studentInfo;

    //审核状态
    @Column(name = "STATE")
    private int state;

}
