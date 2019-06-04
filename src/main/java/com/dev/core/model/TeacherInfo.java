package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TEACHER_INFO")
@Data
public class TeacherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "UID")
    private User user;


}
