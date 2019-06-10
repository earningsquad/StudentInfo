package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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

    @ManyToMany(targetEntity = StudentInfo.class,fetch=FetchType.EAGER)
    @JoinTable(name = "STU_LESSON",joinColumns = @JoinColumn(name="LESSON_ID"),inverseJoinColumns =@JoinColumn(name="STU_ID"))
    private List<StudentInfo> students;


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
    @Column(name = "LE_CHANGE")
    private String leChange;

    //课程容量
    @Column(name = "TOTAL")
    private int total;

    @JoinColumn(name = "MONITOR_ID")
    @OneToOne
    private Monitor monitor;
}
