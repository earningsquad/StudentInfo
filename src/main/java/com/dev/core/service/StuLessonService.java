package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.StuLesson;
import com.dev.core.model.StuLessonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //查询申请补考
    public List<StuLessonFormat> getSupplementary(){
        String hql = "from StuLesson where supplementary = 2";
        List<StuLesson> stuLessons = dao.find(hql);
        List<StuLessonFormat> stuLessonFormatList = new ArrayList<>(stuLessons.size());
        for(StuLesson stuLesson:stuLessons){
            StuLessonFormat stuLessonFormat = new StuLessonFormat(stuLesson);
            stuLessonFormatList.add(stuLessonFormat);
        }
        return stuLessonFormatList;
    }

    //审核补考申请
    public void updateSupplementary(StuLesson stuLesson) {
        String hql = "update StuLesson set supplementary = " + stuLesson.getSupplementary() +
                "where id = " + stuLesson.getId();
        dao.executeHql(hql);
    }
}
