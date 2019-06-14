package com.dev.core.action;

import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.TeacherInfo;
import com.dev.core.service.TeacherInfoService;
import com.dev.core.service.TimetableService;
import com.dev.core.utils.ResponseResult;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class TimetableAction extends BasicAction{

    @Autowired
    TimetableService timetableService;
    @Autowired
    TeacherInfoService teacherInfoService;

    @Setter@Getter
    int id;

    @Getter
    ResponseResult results;

    //查询通告
    @Action(value = "getLessonByTid" , results = {
            @Result(name = SUCCESS , type = "json" , params={"root", "results"}),
            @Result(name = ERROR , type = "json")
    })
    public String getLessonByTid(){
        results = new ResponseResult();
        List<List<String>> resultList = timetableService.getTimetableByTid(getId());
        results.success(resultList,resultList.size());
        return SUCCESS;
    }

    //查询老师
    //查询通告
        @Action(value = "getTeacherInfo" , results = {
            @Result(name = SUCCESS , type = "json" , params={"root", "results"} ),
            @Result(name = ERROR , type = "json")
    })
    public String getTeacherInfo(){
        results = new ResponseResult();
        List<TeacherInfo> resultList = teacherInfoService.getTeacherInfo();
        results.success(resultList,resultList.size());
        return SUCCESS;
    }

}
