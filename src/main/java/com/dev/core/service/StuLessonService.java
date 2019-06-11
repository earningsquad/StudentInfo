package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class StuLessonService  {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<Object> dao;

  public List<StuLesson> allScore(){
      List<StuLesson> scores=new ArrayList<>();
      List<Object> list=dao.find("From StuLesson ");
      for(Object obj: list)
          scores.add((StuLesson) obj);
          return scores;
  }
    public List<Lesson> allLesson(){
        List<Lesson> scores=new ArrayList<>();
        List<Object> list=dao.find("From Lesson ");
        for(Object obj: list)
            scores.add((Lesson) obj);
        return scores;
    }

    public  Boolean deleteStu(int id){
        StuLesson s=(StuLesson)dao.getByHql("FROM StuLesson where id="+id);
        dao.delete(s);
        return true;
    }

    public Boolean updateScore(int id,int score){
      try {
          StuLesson s=(StuLesson)dao.getByHql("From StuLesson where id="+id);
          s.setScore(score);
          dao.update(s);
          return true;
      }catch (Exception e){

      }
        return false;
    }

    public Boolean addStuLess( int[] ids){
      try {
        Lesson le=(Lesson)dao.getByHql("From Lesson where id="+ ids[0]);
        StudentInfo st=(StudentInfo)dao.getByHql("From StudentInfo where id="+ ids[1]);
        StuLesson sl=new StuLesson();
        sl.setScore(ids[2]);
        sl.setSupplementary(ids[3]);
        sl.setLesson(le);
        sl.setStudentInfo(st);
        sl.setLeCheck(0);
        dao.save(sl);
      return true;
      }catch (Exception e){

      }
    return false;
    }

    public Boolean TeaAddLess( int[] ids){
        try {
            Lesson le=(Lesson)dao.getByHql("From Lesson where id="+ ids[0]);
            StudentInfo st=(StudentInfo)dao.getByHql("From StudentInfo where id="+ ids[1]);
            StuLesson sl=new StuLesson();
            sl.setScore(ids[2]);
            sl.setLeCheck(ids[3]);
            sl.setLesson(le);
            sl.setStudentInfo(st);
            sl.setSupplementary(0);
            dao.save(sl);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    public List<Lesson> findClassLesson(User u){
        List<Lesson> cLesson=new ArrayList<>();
        StudentInfo s=(StudentInfo)dao.getByHql("FROM StudentInfo where id="+u.getId());
        String hql=" FROM StudentInfo where classNumber="+s.getClassNumber();
        List<Object> obs = dao.find(hql);
        List<Integer> sids=new ArrayList<>();
       for(Object obj:obs){
            StudentInfo ss=(StudentInfo) obj;
            sids.add(ss.getId());
       }
          cLesson= new ArrayList<Lesson>(new HashSet<>(cLesson));
       for(int i=0;i<sids.size();i++){
           StuLesson sle=(StuLesson) dao.getByHql("From StuLesson where STUDENT_ID="+sids.get(i));
           cLesson.add(sle.getLesson());
       }

      return cLesson;
    }

}
