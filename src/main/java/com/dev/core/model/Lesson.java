package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

//课程表
@Entity
@Table(name = "LESSON")
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //课程名称
    @Column(name = "LE_NAME")
    private String leName;

    //教师ID
    @JoinColumn(name = "TEACHER_ID")
    @OneToOne
    private TeacherInfo teacherInfo;

    //课程详情
    @Column(name = "DETAIL")
    private String detail;

    //课程进度
    @Column(name = "PROGRESS")
    private String progress;

    //排课
    @Column(name = "ARRANGE")
    private String arrange;

    //变动信息
    @Column(name = "CHANGE")
    private String change;

    //课程容量
    @Column(name = "TOTAL")
    private int total;


}
