package com.dev.core.model;

import lombok.Data;

import java.util.Date;

//监考中间类
@Data
public class TeaInvigilate {

    private int id;
    private String leName;
    private Date time;
    private String location;
    private int amounts;
    private String invigilator;
    private String teacher;


    public TeaInvigilate(Monitor m) {
        this.id = m.getId();
        this.leName = m.getLesson().getLeName();
        this.time = m.getTime();
        this.location = m.getLocation();
        this.amounts = m.getLesson().getStudents().size();
        this.invigilator = m.getTeacherInfo().getName();
        this.teacher = m.getLesson().getTeacherInfo().getName();
    }
}
