package com.dev.core.utils;

import lombok.Getter;
import lombok.Setter;

public class JsonResult {

    @Getter@Setter
    private Boolean isSuccess;
    @Getter@Setter
    private String message;
    @Getter@Setter
    private Object bean;

    //成功
    public static JsonResult successX(Object object){
        JsonResult jsonResult =new JsonResult();
        jsonResult.setIsSuccess(true);
        jsonResult.setMessage("success");
        jsonResult.setBean(object);
        return jsonResult;
    }
    //失败
    public static JsonResult failX(String errorMsg){
        JsonResult jsonResult =new JsonResult();
        jsonResult.setIsSuccess(false);
        jsonResult.setMessage(errorMsg);
        return jsonResult;
    }
    //成功
    public  void success(Object object){

        this.setIsSuccess(true);
        this.setMessage("success");
        this.setBean(object);

    }
    //失败
    public void fail(String errorMsg){
        this.setIsSuccess(false);
        this.setMessage(errorMsg);
    }

}
