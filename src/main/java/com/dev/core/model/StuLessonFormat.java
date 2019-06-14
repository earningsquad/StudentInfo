package com.dev.core.model;

import lombok.Data;

@Data
public class StuLessonFormat {
    private int id;
    private String lessonName;
    private int score;
    private String studentName;
    private String supplementary;

    public StuLessonFormat(){

    }

    public StuLessonFormat(StuLesson stuLesson){
        this.id = stuLesson.getId();
        this.lessonName = stuLesson.getLesson().getLeName();
        this.score = stuLesson.getScore();
        this.studentName = stuLesson.getStudentInfo().getName();
        this.supplementary = "补考申请";
    }

}
