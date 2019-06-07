package com.dev.core.action;


import com.dev.core.model.StuLesson;
import com.dev.core.service.StuLessonService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreAction extends BasicAction {
    @Autowired
    StuLessonService service;
    @Getter@Setter
    List<StuLesson> scores;
    @Getter@Setter
    Map<String,Object> tresult;
    @Getter@Setter
    int id;
    @Action(value = "searchScore",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "tresult"}),
            @Result(name = ERROR,type = "json" )
    })
    public String searchAllScore(){
        tresult=new HashMap<>();
        scores=service.allScore();
        tresult.put("code",0);
        tresult.put("msg","");
        tresult.put("count",10);
        List<Map<String,String>> score=new ArrayList<>();
        for(int i=0;i<scores.size();i++){
           Map<String,String> temp=new HashMap<>();
           StuLesson sl=scores.get(i);
           temp.put("id",sl.getId()+"");
           temp.put("leName",sl.getLesson().getLeName());
           temp.put("stuId",sl.getStudentInfo().getId()+"");
           temp.put("stuName",sl.getStudentInfo().getName());
           temp.put("score",sl.getScore()+"");
           score.add(temp);
        }
        tresult.put("data",score);

        return SUCCESS;
    }
    @Action(value = "deleteScore")
  public String deleteScore(){
        service.deleteStu(id);
       return ISUCCESS;

  }



}
