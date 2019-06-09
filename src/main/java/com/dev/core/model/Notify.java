package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//通知表
@Data
@Entity
@Table(name = "NOTIFY")
public class Notify implements Serializable {

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //通知人ID
    @OneToOne
    @JoinColumn(name = "NOTIFIER")
    private User notifier;

    //被通知人ID
    @OneToOne
    @JoinColumn(name = "BE_NOTIFIER")
    private User beNotifier;

    //通知时间
    @Column(name = "CREATE_TIME")
    private Date createTime;

    //通知内容
    @Column(name = "DETAIL")
    private String detail;


    //通知类型 1：缺课提醒
    @Column(name = "TYPE")
    private String type;

}
