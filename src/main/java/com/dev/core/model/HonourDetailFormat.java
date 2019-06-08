package com.dev.core.model;

import lombok.Data;

@Data
public class HonourDetailFormat {
    private int id;
    private int studentId;
    private String name;
    private int type;
    private String detail;
    private String applyReason;
    private int state;

    public HonourDetailFormat(HonourDetail honourDetail){
        this.id = honourDetail.getId();
        this.studentId = honourDetail.getStudentInfo().getId();
        this.name = honourDetail.getHonour().getName();
        this.type = honourDetail.getHonour().getType();
        this.detail = honourDetail.getHonour().getDetail();
        this.applyReason = honourDetail.getApplyReason();
        this.state = honourDetail.getState();
    }

}
