package com.dev.core.model;

import lombok.Data;

@Data
public class ScoreFormat {
    private int id;
    private int classNumber;
    private int userId;
    private int lessonId;
    private String studentName;
    private String lessonName;
    private int score;
    private int supplementary;
    private int leCheck;

    public ScoreFormat() {
    }

    public ScoreFormat(StuLesson stuLesson) {
        this.id = stuLesson.getId();
        this.classNumber = stuLesson.getStudentInfo().getClassNumber();
        this.userId = stuLesson.getStudentInfo().getUser().getId();
        this.studentName = stuLesson.getStudentInfo().getName();
        this.lessonName = stuLesson.getLesson().getLeName();
        this.score = stuLesson.getScore();
        this.supplementary = stuLesson.getSupplementary();
        this.leCheck = stuLesson.getLeCheck();
        this.lessonId = stuLesson.getLesson().getId();
    }
}
