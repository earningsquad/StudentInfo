package com.dev.core.action;


import com.alibaba.fastjson.JSONObject;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.StuLesson;
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

    @Getter
    ResponseResult result = new ResponseResult();

    //选课
    @Action(value = "selectLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String selectLesson(){
        StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stuLessonService.selectLesson(stuLesson);
        result.success();
        return SUCCESS;
    }


    //退课
    @Action(value = "retireLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String retireLesson(){
        StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stuLessonService.retireLesson(stuLesson);
        result.success();
        return SUCCESS;
    }

    //查询已选课程
    @Action(value = "getLesson" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    public String getLesson(){
        /*StuLesson stuLesson = null;
        try {
            stuLesson = JSONObject.parseObject(getRequestPostData(),StuLesson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        List<StuLesson> stuLessonList = stuLessonService.getLesson();
        result.success(stuLessonList,stuLessonList.size());
        return SUCCESS;
    }


}
