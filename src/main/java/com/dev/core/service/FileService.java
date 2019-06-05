package com.dev.core.service;


import com.dev.core.dao.IBaseDao;
import com.dev.core.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<FileInfo> dao;

    public boolean uploadFile(FileInfo fileInfo){
        try {
            dao.save(fileInfo);
        }catch (Exception e){
            return false;
        }
       return  true;

    }

}
