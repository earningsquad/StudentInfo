package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne
    @JoinColumn(name = "tid")
    TeacherInfo teacherInfo;
    @Column(name = "DATE")
    Date date;
    @Column(name = "TASKD_ETIAL")
    String taskDetial;
}
