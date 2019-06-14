package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Monitor;
import com.dev.core.model.StuLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorService {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<Monitor> dao;

    public List<Monitor> allMonitor(){
           List<Monitor> list=new ArrayList<>();
         try {
         list=dao.find("From Monitor ");

         }catch (Exception e){

         }


        return list;
    }

    public List<Monitor> myTeacher(int id){
        List<Monitor> list=new ArrayList<>();
        try {
            list=dao.find("From Monitor where TEACHER_ID="+id);

        }catch (Exception e){

        }


        return list;
    }
    public List<Monitor> myInvigilate(int id){
        List<Monitor> list=new ArrayList<>();
        try {
            list=dao.find("From Monitor m where m.lesson.teacherInfo.id="+id);

        }catch (Exception e){

        }


        return list;
    }



}
