package com.dev.core.model;

import lombok.Data;

@Data
public class JudgeFormat {
    private String teaName;
    private int score;

    public JudgeFormat(String teaName, int score) {
        this.teaName = teaName;
        this.score = score;
    }
}
