package com.dev.core.model;

import lombok.Data;

@Data
public class LessonFormat {
    private int id;
    private String leName;
    private String teacher;
    private String detail;
    private String progress;
    private String arrange;
    private String leChange;
    private int total;

    public LessonFormat(Lesson lesson) {
        this.id = lesson.getId();
        this.leName = lesson.getLeName();
        this.teacher = lesson.getTeacherInfo().getName();
        this.detail = lesson.getDetail();
        this.progress = lesson.getProgress();
        this.arrange = lesson.getArrange();
        this.leChange = lesson.getLeChange();
        this.total = lesson.getTotal();
    }
}
