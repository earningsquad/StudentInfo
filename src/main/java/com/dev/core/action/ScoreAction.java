package com.dev.core.action;


import com.dev.core.model.Lesson;
import com.dev.core.model.StuLesson;
import com.dev.core.model.User;
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
    @Setter
    int score;
    @Setter@Getter
    int[] add;
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
            temp.put("lecheck","否");
            temp.put("supply","否");
           if(sl.getLeCheck()>0){
               temp.put("lecheck","是");
           }
            if(sl.getSupplementary()>0){
                temp.put("supply","是");
            }
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

    @Action(value = "updateScore")
   public String updateScore(){
      service.updateScore(id,score);
        return ISUCCESS;
   }

    @Action(value = "addStuScore")
    public String addStuScore(){
    service.addStuLess(add);
      return ISUCCESS;
    }
    @Action(value = "searchLesson",results = {
            @Result(name = SUCCESS, type = "json", params = {"root", "tresult"})
    })
    public String searchLesson(){
        List<Lesson> les=new ArrayList<>();
        tresult=new HashMap<>();
        les=service.allLesson();
        int i=0;
        List<Map<String,String>> templ=new ArrayList<>();
        for(Lesson l:les){
            Map<String,String> temp=new HashMap<>();
            temp.put("id",l.getId()+"");
            temp.put("name",l.getLeName());
          templ.add(temp)    ;
        }
        tresult.put("lesson",templ);
        return SUCCESS;
    }





}
