package com.dev.core.action;

import com.dev.core.service.TimetableService;
import com.dev.core.utils.ResponseResult;
import lombok.Getter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class TimetableAction extends BasicAction{

    @Autowired
    TimetableService timetableService;

    @Getter
    ResponseResult results;

    //查询通告
    @Action(value = "getLessonByTid" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String getLessonByTid(){
        results = new ResponseResult();
        List<List<String>> resultList = timetableService.getTimetableByTid(1);
        results.success(resultList,resultList.size());
        return SUCCESS;
    }

}
