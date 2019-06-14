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
    private String state;

    public HonourDetailFormat(HonourDetail honourDetail){
        this.id = honourDetail.getId();
        this.studentId = honourDetail.getStudentInfo().getId();
        this.name = honourDetail.getHonour().getName();
        this.type = honourDetail.getHonour().getType();
        this.detail = honourDetail.getHonour().getDetail();
        this.applyReason = honourDetail.getApplyReason();
        if(honourDetail.getState() == 0){
            this.state = "审核中";
        }else if(honourDetail.getState() == 1){
            this.state = "审核通过";
        }else if(honourDetail.getState() == 2){
            this.state = "审核不通过";
        }
    }

}
