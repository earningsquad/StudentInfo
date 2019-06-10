package com.dev.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class NotifyFormat {
    private int id;
    private String notifyName;
    private String beNotifyName;
    private String detail;
    private String type;
    private Date date;

    public NotifyFormat(){

    }

    public NotifyFormat(Notify notify){
        if(notify.getNotifier().getRole().equals("1")){
            this.notifyName = notify.getNotifier().getStudentInfo().getName();
        }else{
            this.notifyName = notify.getNotifier().getTeacherInfo().getName();
        }

        if(notify.getBeNotifier().getRole().equals("1")){
            this.beNotifyName = notify.getBeNotifier().getStudentInfo().getName();
        }else{
            this.beNotifyName = notify.getBeNotifier().getTeacherInfo().getName();
        }
        this.id = notify.getId();
        this.detail = notify.getDetail();
        this.type = notify.getType();
        this.date = notify.getCreateTime();
    }

}
