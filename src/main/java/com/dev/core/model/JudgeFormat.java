package com.dev.core.model;

import lombok.Data;

@Data
public class JudgeFormat {
    private int id;
    private String teaName;
    private int score;

    public JudgeFormat(String teaName, int score,int id) {
        this.teaName = teaName;
        this.score = score;
        this.id=id;
    }
}
