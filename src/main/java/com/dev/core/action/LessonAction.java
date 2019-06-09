package com.dev.core.action;

import com.alibaba.fastjson.JSONObject;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.Lesson;
import com.dev.core.model.LessonFormat;
import com.dev.core.service.LessonService;
import com.dev.core.service.TeacherInfoService;
import com.dev.core.utils.ResponseResult;
import lombok.Getter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class LessonAction extends BasicAction{

    @Autowired
    LessonService lessonService;
    @Autowired
    TeacherInfoService teacherInfoService;

    @Getter
    ResponseResult results = new ResponseResult();

    //添加课程
    @Action(value = "addLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    //@RoleRequired(value = "teacher")
    public String addLesson(){
        Lesson lesson = null;
        try {
            lesson = JSONObject.parseObject(getRequestPostData(),Lesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lessonService.addLesson(lesson);
        results.success();
        return SUCCESS;
    }

    //查询课程
    @Action(value = "findLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String findLesson(){
        List<LessonFormat> lessonFormatList = lessonService.findLesson();
        results.success(lessonFormatList,lessonFormatList.size());
        return SUCCESS;
    }

    @Action(value = "deleteLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String deleteLesson(){
        Lesson lesson = null;
        try {
            lesson = JSONObject.parseObject(getRequestPostData(),Lesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lessonService.deleteLesson(lesson);
        return SUCCESS;
    }

    @Action(value = "updateLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String updateLesson(){
        Lesson lesson = null;
        try {
            lesson = JSONObject.parseObject(getRequestPostData(),Lesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lessonService.updateLesson(lesson);
        results.success();
        return SUCCESS;
    }

    @Action(value = "getTeacherInfoList" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String getTeacherInfoList(){
        results.success(teacherInfoService.getTeacherInfoList());
        return SUCCESS;
    }

}
