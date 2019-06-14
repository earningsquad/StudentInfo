package com.dev.core.model;

import lombok.Data;

@Data
public class ClassLessonFormat {
    int id;
    String leName;
    String teacher;
    String progress;
    int total;
    String leChange;
    String arrage;
    String detial;

    public ClassLessonFormat(Lesson l){
        id=l.getId();
        leName=l.getLeName();
        teacher=l.getTeacherInfo().getName();
        progress=l.getProgress();
        total=l.getTotal();
        leChange=l.getLeChange();
        arrage=l.getArrange();
        detial=l.getDetail();

    }



}
