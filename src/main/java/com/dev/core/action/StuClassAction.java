package com.dev.core.action;

import com.dev.core.anno.GetUser;
import com.dev.core.model.ClassLessonFormat;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
public class StuClassAction extends BasicAction {
    @Autowired
    StuLessonService service;
    @Getter@Setter
    Map<String,Object> cLesson;
    @Setter@Getter
    int id;
    @Action(value = "findClassLesson",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "cLesson"}),
            @Result(name = ERROR,type = "json" )
    })
    public String findClassLesson(@GetUser User user){
        List<Lesson> cl=service.findClassLesson(user);
        List<ClassLessonFormat>  ccLesson=new ArrayList<>();
        for(Lesson l:cl){
            ccLesson.add(new ClassLessonFormat(l));
        }
        cLesson=new HashMap<>();
        cLesson.put("code",0);
        cLesson.put("msg","");
        cLesson.put("count",10);
        cLesson.put("data",ccLesson);

        return SUCCESS;


    }


}
