package com.dev.core.service;


import com.dev.core.dao.IBaseDao;
import com.dev.core.model.FileInfo;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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


     public List<FileInfo> findFile(int uid){
         List<FileInfo> files=new ArrayList<>();
         files=dao.find("from  FileInfo where fileUploader="+uid);

         return files;
     }
    public boolean recoveryData(String sqlname){

        try {
            File newfile = new File(sqlname);
            Resource resource = new FileSystemResource(newfile);
            EncodedResource resourceDelegate = new EncodedResource(resource);
            dao.getSessionFactory().openSession().doWork(
                    new Work() {
                        public void execute(Connection connection) {
                            ScriptUtils.executeSqlScript(connection, resource);
                        }
                    }
            );
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return  true;
    }

}
