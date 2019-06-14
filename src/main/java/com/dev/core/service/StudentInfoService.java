package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentInfoService {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<StudentInfo> dao;

    //更新班级
    public void updateClass(StudentInfo studentInfo){
        String hql = "update StudentInfo set classNumber = "+studentInfo.getClassNumber() + "where id = "+studentInfo.getId();
        dao.executeHql(hql);
    }

}
