package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.StudentInfo;
import com.dev.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuInfService {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<StudentInfo> dao;

    public List<StudentInfo> getStudentsInfo(){

        String hql=" FROM StudentInfo";
        List<StudentInfo> students = dao.find(hql);
        return students;
    }
    public List<StudentInfo> getClassStudentsInfo(User u){
       StudentInfo s=dao.getByHql("FROM StudentInfo where id="+u.getId());
        String hql=" FROM StudentInfo where classNumber="+s.getClassNumber();
        List<StudentInfo> students = dao.find(hql);
        return students;
    }

    public  Boolean deleteStu(int id){
        StudentInfo s=dao.getByHql("FROM StudentInfo where id="+id);
        dao.delete(s);
        return true;
    }
    public  Boolean addStu(StudentInfo s){
        dao.save(s);
        return true;
    }



    public StudentInfo findtheStu(int id){
        StudentInfo s=dao.getByHql("FROM StudentInfo where id="+id);
        return s;
    }
    public Boolean  updateStu(StudentInfo s){
        dao.update(s);
        return true;
    }




}
