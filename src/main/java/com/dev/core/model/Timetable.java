package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

//课表
@Data
@Table(name = "TIMETABLE")
@Entity
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //课程
    @JoinColumn(name = "LESSON_ID")
    @OneToOne
    private Lesson lesson;

    //星期
    @Column(name = "WEEK")
    private int week;

    //上课地点
    @Column(name = "ADDRESS")
    private String address;

    //节次 周一第几节 ：
    @Column(name = "NUM")
    private int num;

}
