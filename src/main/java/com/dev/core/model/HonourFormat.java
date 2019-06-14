package com.dev.core.model;

import lombok.Data;

@Data
public class HonourFormat {
    private int id;
    private String name;
    private String type;
    private int state;
    private String detail;

    public HonourFormat(Honour honour) {
        this.id = honour.getId();
        this.name = honour.getName();
        if(honour.getType() == 0){
            this.type = "荣誉申请";
        }
        if(honour.getType() == 1){
            this.type = "奖学金申请";
        }
        if(honour.getType() == 2){
            this.type = "评优申请";
        }
        this.state = honour.getState();
        this.detail = honour.getDetail();
    }
}
