package com.dev.core.utils;

import lombok.Data;

@Data
public class HonourResult {
    private String status;
    private int totals;
    private Object data;

    public void success(){
        this.status = "success";
        this.totals = 0;
    }

    public void success(Object data,int totals){
        this.status = "success";
        this.totals = totals;
        this.data = data;
    }

    public void fail(){
        this.status = "fail";
        this.totals = 0;
    }

}
