package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

//学生荣耀表
@Entity
@Table(name = "HONOUR_DETAIL")
@Data
public class HonourDetail implements Serializable {

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
