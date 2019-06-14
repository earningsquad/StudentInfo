package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Judge;
import com.dev.core.model.JudgeFormat;
import com.dev.core.model.TeacherInfo;
import com.dev.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JudgeService {
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<Judge> dao;
    @Autowired
    @Qualifier("baseDao")
    IBaseDao<TeacherInfo> tdao;

public TeacherInfo findJudgeTeacher(User  u){

    Judge j=dao.getByHql("From Judge where JUDGER="+u.getId());
   TeacherInfo t=tdao.getByHql("From TeacherInfo where UID="+j.getBeJudeged().getId());
    return t;
}



    public Boolean updateJudge(int id, int rs){

        Judge j=dao.getByHql("From Judge where id="+id);
        j.setResult(rs);
        j.setType(1);
      dao.update(j);
        return true;
    }

    public List<JudgeFormat> getStuJudge(User u){

      List<Judge> js=dao.findByHqL("From Judge where type=1 and JUDGER="+u.getId());
      List<JudgeFormat> ts=new ArrayList<>();
      for(Judge j:js){
         TeacherInfo t=tdao.getByHql("From TeacherInfo where UID="+j.getBeJudeged().getId());
          ts.add(new JudgeFormat(t.getName(),j.getResult()));

     }
     return ts;
    }




}
