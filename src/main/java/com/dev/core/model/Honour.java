package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

//荣耀表
@Entity
@Table(name = "HONOUR")
@Data
public class Honour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //荣耀类型
    @Column(name = "TYPE")
    private int type;

    //状态
    @Column(name = "STATE")
    private int state;

    //荣耀信息
    @Column(name = "DETAIL")
    private String detial;

    @ManyToMany(targetEntity = StudentInfo.class,fetch=FetchType.EAGER)
    @JoinTable(name = "HONOUR_DETAIL",joinColumns = @JoinColumn(name="HONOUR_ID"),inverseJoinColumns =@JoinColumn(name="STUDENT_ID"))
    private List students;
}
