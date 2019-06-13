package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Lesson;
import com.dev.core.model.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonProgressService {
    @Autowired
    IBaseDao baseDao;

    public  TeacherInfo  getTeacherInfo(int uid){
        TeacherInfo teacherInfo=null;
        List<TeacherInfo> list= baseDao.find("from TeacherInfo t where t.user.id="+uid);
        if (list!=null&&list.size()>0){
            teacherInfo= (TeacherInfo) list.get(0);
        }
        return teacherInfo;
    }

    public List getAllLesson(){
        Lesson myLesson=null;
        List list=baseDao.find("from Lesson");
        return list;
    }
    public Lesson getMyLesson(int tid){
        Lesson myLesson=null;
        List list=baseDao.find("from Lesson s where " +
                "s.teacherInfo.id ="+tid);
        if (list!=null&&list.size()>0){
            myLesson= (Lesson) list.get(0);
        }
        return myLesson;
    }
    public void updateMyProgress(Lesson lesson){
        baseDao.update(lesson);
    }

}
