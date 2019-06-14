package com.dev.core.action;

import com.dev.core.anno.GetUser;
import com.dev.core.model.ClassInfor;
import com.dev.core.model.ClassLessonFormat;
import com.dev.core.model.Lesson;
import com.dev.core.model.User;
import com.dev.core.service.ClassInforService;
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
    @Autowired
    ClassInforService classService;
    @Getter@Setter
    Map<String,Object> cLesson;
    @Setter@Getter
    int id;
    @Getter@Setter
    List<ClassInfor> classActivity;
    @Getter@Setter
    List<Map> classScore;
    @Getter@Setter
    List<ClassInfor> classInf;
    @Setter@Getter
    int cnb;
    @Setter@Getter
    String reason;
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


    @Action(value = "findClassActivity",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "classActivity"}),
            @Result(name = ERROR,type = "json" )
    })
public String findClassActivity(@GetUser User user){
     classActivity=classService.findClassActivity(user);

        return SUCCESS;
}


    @Action(value = "findClassScore",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "classScore"}),
            @Result(name = ERROR,type = "json" )
    })
public String findClassScore(@GetUser User user){
        classScore=classService.finClassScore(user);
        return SUCCESS;
}


 @Action(value = "findClassHonur",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "classInf"}),
            @Result(name = ERROR,type = "json" )
    })
public String findClassHonur(@GetUser User user) {

     classInf = classService.findClassHonour(user);
        return SUCCESS;
    }


 @Action(value = "StuChangeClass",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "classInf"}),
            @Result(name = ERROR,type = "json" )
    })
public String StuChangeClass(@GetUser User user){

   classService.addChangeClass(user,cnb,reason);
     return SUCCESS;
}




    @Action(value = "findChangeClass",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "classInf"}),
            @Result(name = ERROR,type = "json" )
    })
    public String findChangeClass(@GetUser User user){

        classInf= classService.findChangedClass(user);
        return SUCCESS;
    }





}
