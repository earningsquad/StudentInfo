package com.dev.core.action;


import com.alibaba.fastjson.JSONObject;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.StuLesson;

import com.dev.core.model.StudentInfo;
import com.dev.core.service.LessonService;
import com.dev.core.service.StuLessonService;
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
public class StuLessonAction extends BasicAction{
    @Autowired
    StuLessonService stuLessonService;
    @Autowired
    LessonService lessonService;

    @Getter
    ResponseResult results;

    //选课
    @Action(value = "selectLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String selectLesson(){
         results = new ResponseResult();
        StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stuLessonService.selectLesson(stuLesson);
        lessonService.updateLessonTotal(stuLesson.getLesson().getId());
        results.success();
        return SUCCESS;
    }


    //退课
    @Action(value = "retireLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String retireLesson(){
         results = new ResponseResult();
        StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(1);
        stuLesson.setStudentInfo(studentInfo);
        stuLessonService.retireLesson(stuLesson);
        results.success();
        return SUCCESS;
    }

    //查询已选课程
    @Action(value = "getLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String getLesson(){
         results = new ResponseResult();
        /*StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        List<StuLesson> stuLessonList = stuLessonService.getLesson();
        results.success(stuLessonList,stuLessonList.size());
        return SUCCESS;
    }


}
