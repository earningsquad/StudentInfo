package com.dev.core.model;

import lombok.Data;

import javax.persistence.*;

//文件表
@Entity
@Table(name = "FILE")
@Data
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //文件名称
    @Column(name = "FILE_NAME")
    private String fileName;

    //上传者ID
    @JoinColumn(name = "FILE_UPLOAD")
    @OneToOne
    private User fileUploader;

    //文件类型
    @Column(name = "FILE_TYPE")
    private String fileType;

    //文件存储位置
    @Column(name = "FILE_LOCATION")
    private String fileLocation;

 }
