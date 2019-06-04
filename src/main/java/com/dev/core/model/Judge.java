package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

//评教表
@Entity
@Table(name = "JUDGE")
@Data
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //评价人ID
    @OneToOne
    @JoinColumn(name = "judger")
    private User judger;

    //被评价人ID
    @OneToOne
    @JoinColumn(name = "beJudeged")
    private User beJudeged;

    //评教类型
    @Column(name = "TYPE")
    private int type;

}
