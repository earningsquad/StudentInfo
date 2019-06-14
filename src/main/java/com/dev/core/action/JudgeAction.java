package com.dev.core.action;

import com.dev.core.anno.GetUser;
import com.dev.core.model.Judge;
import com.dev.core.model.JudgeFormat;
import com.dev.core.model.TeacherInfo;
import com.dev.core.model.User;
import com.dev.core.service.JudgeService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
public class JudgeAction extends BasicAction {
    @Autowired
    JudgeService service;
     @Getter@Setter
     Judge judge;
    @Getter@Setter
     TeacherInfo teacher;
    @Getter@Setter
    int tresult;
    @Getter@Setter
    int id;
    @Setter@Getter
    List<JudgeFormat> judgeFormats;
    @Action(value = "StudentJudge",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "classInf"}),
            @Result(name = ERROR,type = "json" )
    })
    public String StudentJudge(@GetUser User user){


        return SUCCESS;
    }

    @Action(value = "findJudgeTeacher",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "teacher"}),
            @Result(name = ERROR,type = "json" )
    })
    public String findJudge(@GetUser User user){
        teacher=service.findJudgeTeacher(user);
        return SUCCESS;
    }

    @Action(value = "JudgeResult")
    public String JudgeResult(){
          service.updateJudge(id,tresult);
        return ISUCCESS;
    }
    @Action(value = "getStuJudge",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "judgeFormats"}),
            @Result(name = ERROR,type = "json" )
    })
    public String getStuJudge(@GetUser User user){
        judgeFormats=service.getStuJudge(user);

        return SUCCESS;
    }

}
