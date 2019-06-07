package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.StuLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuLessonService {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<StuLesson> dao;

    public void selectLesson(StuLesson stuLesson){
        dao.save(stuLesson);
    }

    public void retireLesson(StuLesson stuLesson){
        dao.delete(stuLesson);
    }

    public List<StuLesson> getLesson(){
        String hql = "from StuLesson";
        List<StuLesson> stuLessons = dao.find(hql);
        return stuLessons;
    }

}
