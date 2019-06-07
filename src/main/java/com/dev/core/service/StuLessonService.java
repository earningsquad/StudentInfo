package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.StuLesson;
import com.dev.core.model.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StuLessonService  {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<StuLesson> dao;

  public List<StuLesson> allScore(){
      List<StuLesson> scores=new ArrayList<>();
      scores=dao.find("From StuLesson ");
      return scores;
  }

    public  Boolean deleteStu(int id){
        StuLesson s=dao.getByHql("FROM StuLesson where id="+id);
        dao.delete(s);
        return true;
    }


}
