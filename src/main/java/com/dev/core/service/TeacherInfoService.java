package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherInfoService {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<TeacherInfo> dao;

    public List<TeacherInfo> getTeacherInfo(){
        return dao.find("from TeacherInfo");
    }
}
