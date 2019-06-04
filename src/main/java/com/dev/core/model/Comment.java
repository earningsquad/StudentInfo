package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

//留言表
@Entity
@Table(name = "COMMENT")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //用户id
    @JoinColumn(name = "USER_ID")
    @OneToOne
    private User user;

    //留言详情
    @Column(name = "DETAIL")
    private String detail;

    //父留言ID
    @JoinColumn(name = "PID")
    @OneToOne
    private Comment parentCommit;

    //留言类型
    @Column(name = "TYPE")
    private int type;


}
