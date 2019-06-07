package com.dev.core.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

//学生信息表
@Entity
@Table(name = "STUDENT_INFO")
@Data
public class StudentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //学生名
    @Column(name = "NAME")
    private String name;

    //学生班级
    @Column(name = "CLASS_NUMBER")
    private int classNumber;

    //学业预紧
    @Column(name = "WARN")
    private String warn;

    //用户ID
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UID")
    private User user;

}
