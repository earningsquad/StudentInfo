package com.dev.core.utils;

import lombok.Data;

@Data
public class ResponseResult {

    //错误码 成功：0 / 失败：1
    private int code;
    private String msg;
    private int count;
    private Object data;

    public void success(){
        this.code = 0;
        this.msg = "success";
        this.count = 0;
    }

    public void success(Object data){
        this.code = 0;
        this.msg = "success";
        this.count = 0;
        this.data = data;
    }

    public void success(Object data,int count){
        this.code = 0;
        this.msg = "success";
        this.count = count;
        this.data = data;
    }

    public void fail(){
        this.code = 1;
        this.msg = "fail";
        this.count = 0;
    }

}
