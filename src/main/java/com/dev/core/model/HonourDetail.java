package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

//学生荣耀表
@Entity
@Table(name = "HONOUR_DETAIL")
@Data
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

    //申请理由
    @Column(name = "APPLY_REASON")
    private String applyReason;

    //文件ID
    @OneToOne
    @JoinColumn(name = "FILE_ID")
    private FileInfo file;

}
