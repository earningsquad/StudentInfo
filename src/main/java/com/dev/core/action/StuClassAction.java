package com.dev.core.action;

import com.dev.core.model.Lesson;
import com.dev.core.model.User;
import com.dev.core.service.StuLessonService;
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
public class StuClassAction extends BasicAction {
    @Autowired
    StuLessonService service;
    @Getter@Setter
    List<Lesson> cLesson;

    @Action(value = "findClassLesson",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "studentInfo"}),
            @Result(name = ERROR,type = "json" )
    })
    public String findClassLesson(){
        cLesson=service.findClassLesson((User)result.getBean());

    return SUCCESS;
    }


}
