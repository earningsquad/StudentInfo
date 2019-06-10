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
        String sql = "DELETE FROM stu_lesson WHERE LESSON_ID = "+stuLesson.getLesson().getId()+" and STUDENT_ID = "+stuLesson.getStudentInfo().getId();
        dao.executeSql(sql);
    }

    public List<StuLesson> getLesson(){
        String hql = "from StuLesson";
        List<StuLesson> stuLessons = dao.find(hql);
        return stuLessons;
    }

    //查询已结课的课程
    public List<StuLesson> getLessonEnding(int stuId){
        String hql = "from StuLesson where student_id = "+stuId+"  and  supplementary != 0";
        return dao.find(hql);
    }

}
