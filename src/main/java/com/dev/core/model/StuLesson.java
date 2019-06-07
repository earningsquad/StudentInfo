package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

//学生课程信息表
@Entity
@Table(name = "STU_LESSON")
@Data
public class  StuLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //学生ID
    @JoinColumn(name = "STUDENT_ID")
    @OneToOne
    private StudentInfo studentInfo;

    //课程ID
    @JoinColumn(name = "LESSON_ID")
    @OneToOne
    private Lesson lesson;

    //学生分数
    @Column(name = "SCORE")
    private int score;

    //复核
    @Column(name = "LE_CHECK")
    private int leCheck;

    //补考状态
    @Column(name = "SUPPLEMENTARY")
    private  int supplementary;
   }
