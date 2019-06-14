package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.ClassInfor;
import com.dev.core.model.StudentInfo;
import com.dev.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ClassInforService {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<ClassInfor> dao;
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<StudentInfo> sdao;
public List<ClassInfor> findClassActivity(User u) {

    StudentInfo s = sdao.getByHql("From StudentInfo where UID=" + u.getId());
    List<ClassInfor> cActivity = dao.findByHqL("From ClassInfor c where c.type=1 and c.classnumber=" + s.getClassNumber());
            return cActivity;
}

public List<ClassInfor> findClassHonour(User u){
    StudentInfo s = sdao.getByHql("From StudentInfo where UID=" + u.getId());
    List<ClassInfor> cHonour = dao.findByHqL("From ClassInfor c where c.type=2 and c.classnumber=" + s.getClassNumber());
    return cHonour;
}
    public Boolean addChangeClass(User u,int cnb,String reason){
        StudentInfo s = sdao.getByHql("From StudentInfo where UID=" + u.getId());
        ClassInfor c=new ClassInfor();
        c.setType(4);
        c.setAmount(s.getClassNumber());
        c.setClassnumber(cnb);
        c.setIntroduction(reason);
        dao.save(c);
        return true;
    }
    public List<ClassInfor> findChangedClass(User u){
        StudentInfo s = sdao.getByHql("From StudentInfo where UID=" + u.getId());
        List<ClassInfor> changedClass = dao.findByHqL("From ClassInfor c where c.type>2 and c.amount=" + s.getId());
        return changedClass;
    }

public List<Map> finClassScore(User u){
    StudentInfo s = sdao.getByHql("From StudentInfo where UID=" + u.getId());
    List<Map> lists=new ArrayList<>();
    lists=dao.findBySql("SELECT SI.CLASS_NUMBER,lesson.LE_NAME,AVG(SL.SCORE) as avgs FROM student_info SI \n" +
            "LEFT JOIN stu_lesson SL ON SI.ID = SL.STUDENT_ID \n" +
            "LEFT JOIN lesson on lesson.id = LESSON_ID\n" +
            "GROUP BY SI.CLASS_NUMBER,SL.LESSON_ID\n" +
            "HAVING SI.CLASS_NUMBER="+s.getClassNumber());
              return lists;
}



}



