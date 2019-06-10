package com.dev.core.action;

import com.dev.core.model.StuLesson;
import com.dev.core.model.StudentInfo;
import com.dev.core.service.StuLessonService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TeaScoreAction extends BasicAction  {
    @Autowired
    StuLessonService service;
    @Setter @Getter
    int[] add;
    @Getter
    List<ArrayList<String>> scores;
    @Setter
    int id;
    @Setter
    int flag;
    @Action(value = "TeaAddScore")
    public String addStuScore(){
        service.TeaAddLess(add);
        return ISUCCESS;
    }
    @Action(value = "updateStuLessCheck")
    public String updateStuLessCheck(){
        service.updateLessCheck(id,flag);
        return ISUCCESS;
    }

    @Action(value = "TeaSearchAllScore",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "scores"}),
            @Result(name = ERROR,type = "json" )
    })
    public String searchAllScore(){
    scores=new ArrayList<>();
    List<StuLesson> stu=service.allScore();
    for(StuLesson s:stu){
        ArrayList<String> ssore=new ArrayList<>();
        ssore.add(s.getId()+"");
        ssore.add(s.getStudentInfo().getName()+"");
        ssore.add(s.getLesson().getLeName()+"");
        ssore.add(s.getScore()+"");
        ssore.add(s.getLesson().getTeacherInfo().getName()+"");
        ssore.add(s.getLesson().getMonitor().getTime()+"");
        ssore.add(s.getLeCheck()+"");
       scores.add(ssore);
    }
    return SUCCESS;
    }
}
