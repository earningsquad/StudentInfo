package com.dev.core.action;


import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.Lesson;
import com.dev.core.model.ScoreFormat;
import com.dev.core.model.StuLesson;
import com.dev.core.model.User;
import com.dev.core.service.StuLessonService;
import com.dev.core.utils.ResponseResult;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreAction extends BasicAction {
    @Getter@Setter
    ResponseResult responseResult;
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

    //我的复核申请
    @Action(value = "searchSelfScore",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "responseResult"}),
            @Result(name = ERROR,type = "json" )
    })
    public String searchSelfScore(){
        User me= (User) ActionContext.getContext().getSession().get("user");
        responseResult = new ResponseResult();
        List<ScoreFormat> list = service.searchSelfScore(me.getStudentInfo().getId());
        responseResult.success(list,list.size());
        return SUCCESS;
    }

    //我的复核申请
    @Action(value = "searchSupplementary",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "responseResult"}),
            @Result(name = ERROR,type = "json" )
    })
    public String searchSupplementary(){
        User me= (User) ActionContext.getContext().getSession().get("user");
        responseResult = new ResponseResult();
        List<ScoreFormat> list = service.searchSupplementary(1);
        responseResult.success(list,list.size());
        return SUCCESS;
    }

    @Action(value = "searchSelfLesson",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "responseResult"}),
            @Result(name = ERROR,type = "json" )
    })
    public String searchSelfLesson(){
        User me= (User) ActionContext.getContext().getSession().get("user");
        responseResult = new ResponseResult();
        List<ScoreFormat> list = service.searchSelfLesson(me.getStudentInfo().getId());
        responseResult.success(list,list.size());
        return SUCCESS;
    }

    @Action(value = "getStudentName",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "responseResult"}),
            @Result(name = ERROR,type = "json" )
    })
    public String getStudentName(){
        responseResult = new ResponseResult();
        User me= (User) ActionContext.getContext().getSession().get("user");
        responseResult.success(me.getStudentInfo().getName());
        return SUCCESS;
    }


    @Action(value = "getScoreByLesson",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "responseResult"}),
            @Result(name = ERROR,type = "json" )
    })
    public String getScoreByLesson(){
        responseResult = new ResponseResult();
        StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StuLesson stuLesson1 = service.getScoreByLesson(stuLesson.getId());
        responseResult.success(stuLesson1);
        return SUCCESS;
    }

    @Action(value = "applyLeCheck",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "responseResult"}),
            @Result(name = ERROR,type = "json" )
    })
    public String applyLeCheck(){
        responseResult = new ResponseResult();
        StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.applyLeCheck(stuLesson.getId());
        responseResult.success();
        return SUCCESS;
    }

    @Action(value = "applySupplementary",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "responseResult"}),
            @Result(name = ERROR,type = "json" )
    })
    public String applySupplementary(){
        responseResult = new ResponseResult();
        StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.applySupplementary(stuLesson.getId());
        responseResult.success();
        return SUCCESS;
    }



}
