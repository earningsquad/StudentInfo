package com.dev.core.action;

import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.Lesson;
import com.dev.core.service.LessonService;
import com.dev.core.utils.JsonResult;
import lombok.Getter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class LessonAction extends BasicAction{

    @Autowired
    LessonService lessonService;

    @Getter
    JsonResult result;

    //添加课程
    @Action(value = "addLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String postLesson(){
        Lesson lesson = null;
        try {
            lesson = JSONObject.parseObject(getRequestPostData(),Lesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lessonService.addLesson(lesson);
        result = result.successX();
        return SUCCESS;
    }

    //查询课程
    @Action(value = "findLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String findLesson(){
        result = result.successX(lessonService.findLesson());
        return SUCCESS;
    }

}
