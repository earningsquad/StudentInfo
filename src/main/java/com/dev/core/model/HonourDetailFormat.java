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
    private String studentName;
    private String fileName;
    private String fileLocation;

    public HonourDetailFormat(HonourDetail honourDetail){
        this.id = honourDetail.getId();
        this.studentId = honourDetail.getStudentInfo().getId();
        this.name = honourDetail.getHonour().getName();
        this.type = honourDetail.getHonour().getType();
        this.detail = honourDetail.getHonour().getDetail();
        this.applyReason = honourDetail.getApplyReason();
        this.fileLocation = honourDetail.getFile().getFileLocation();
        this.fileName = honourDetail.getFile().getFileName();
        if(honourDetail.getState() == 0){
            this.state = "未审核";
        }else if(honourDetail.getState() == 1){
            this.state = "审核通过";
        }else {
            this.state = "审核不通过";
        }

        this.studentName = honourDetail.getStudentInfo().getName();
    }

}
