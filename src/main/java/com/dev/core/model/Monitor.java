package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//监考表
@Entity
@Table(name = "Monitor")
@Data
public class Monitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //课程ID

    @ManyToOne
    @JoinColumn(name="LESSON_ID")
    private Lesson lesson;
    //教师ID
    @OneToOne
    @JoinColumn(name="TEACHER_ID")
    private TeacherInfo teacherInfo;

    //时间
    @Column(name = "TIME")
    private Date time;

    //地点
    @Column(name = "LOCATION")
    private String location;


}
